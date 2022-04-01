package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.common.MapperFunction;
import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.entity.ClothEntity;
import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.repository.ClothRepository;
import com.secondhand.secondhand.repository.GuestTokenRepository;
import com.secondhand.secondhand.repository.UserRepository;
import com.secondhand.secondhand.service.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final GuestTokenRepository guestTokenRepository;
    private final MapperFunction toClothDTO;
    private final ClothRepository clothRepository;
    private final ModelMapper modelMapper;

    public LikeServiceImpl(UserRepository userRepository, GuestTokenRepository guestTokenRepository, MapperFunction toClothDTO, ClothRepository clothRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.guestTokenRepository = guestTokenRepository;
        this.toClothDTO = toClothDTO;
        this.clothRepository = clothRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ClothDTO giveUserLikeToCloth(String userEmail, Long itemId) {

        UserEntity byEmailGraphLikes = this.userRepository
                .findByEmailGraphLikes(userEmail).orElseThrow(() -> new UsernameNotFoundException("Couldn't find"));

        ClothEntity clothEntity = getItemById(itemId);


        byEmailGraphLikes
                .getLikesList()
                .forEach(clothEntity2 -> {
                    if (Objects.equals(clothEntity2.getId(), itemId)) {

                        throw new ItemAlreadyExistsException("");
                    }
                });

        byEmailGraphLikes
                .getLikesList()
                .add(clothEntity);

        this.userRepository
                .save(byEmailGraphLikes);

        return asClothDTO(clothEntity);
    }

    @Override
    public ClothDTO giveGuestLikeToCloth(String guestCookie, Long itemId) {

        GuestTokenEntity guestToken = this.guestTokenRepository
                .findByTokenGraphLikes(guestCookie).orElseThrow(() -> new UsernameNotFoundException(""));

        ClothEntity clothEntity = getItemById(itemId);

        guestToken
                .getLikeList()
                .forEach(clothEntity1 -> {
                    if (Objects.equals(clothEntity1.getId(), itemId)) {

                        throw new ItemAlreadyExistsException("");
                    }
                });

        guestToken
                .getLikeList()
                .add(clothEntity);

        this.guestTokenRepository
                .save(guestToken);

        return asClothDTO(clothEntity);
    }

    @Override
    public ClothDTO deleteUserLikeFromCloth(String userEmail, Long itemId) {

        UserEntity user = this.userRepository
                .findByEmailGraphLikes(userEmail).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user!"));

        ClothEntity clothEntity = getItemById(itemId);

//        int oldUserLikes = user.getLikesList().size();

        user
                .setLikesList(user
                        .getLikesList()
                        .stream()
                        .filter(clothEntity1 -> !Objects.equals(clothEntity1.getId(), clothEntity.getId()))
                        .collect(Collectors.toList()));
//        int lastUserLikes = user.getLikesList().size();

        this.userRepository
                .save(user);

        return asClothDTO(clothEntity);
//        return oldUserLikes > lastUserLikes;
    }

    @Override
    public ClothDTO deleteGuestLikeFromCloth(String guestCookie, Long itemId) {

        GuestTokenEntity guest = this.guestTokenRepository
                .findByTokenGraphLikes(guestCookie).orElseThrow(() -> new UsernameNotFoundException("Couldn't find guest"));

        ClothEntity clothEntity = getItemById(itemId);

//        int oldGuestLikes = guest.getLikeList().size();

        guest
                .setLikeList(
                        guest
                                .getLikeList()
                                .stream()
                                .filter(clothEntity1 -> !Objects.equals(clothEntity1.getId(), clothEntity.getId()))
                                .collect(Collectors.toList()));

//        int lastGuestLikes = guest.getLikeList().size();

        this.guestTokenRepository
                .save(guest);

        return asClothDTO(clothEntity);
//        return oldGuestLikes > lastGuestLikes;
    }

    @Override
    public List<ClothDTO> getUserLikes(String userEmail) {

        UserEntity userEntity = this.userRepository
                .findByEmailGraphLikes(userEmail).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user !"));

        return userEntity
                .getLikesList()
                .stream()
                .map(this::asClothDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothDTO> getGuestLikes(String guestCookie) {

        GuestTokenEntity byToken = this.guestTokenRepository
                .findByTokenGraphLikes(guestCookie).orElseThrow(() -> new UsernameNotFoundException("Couldn't find the guest!"));

        return byToken
                .getLikeList()
                .stream()
                .map(this::asClothDTO)
                .collect(Collectors.toList());
    }

    private ClothDTO asClothDTO(ClothEntity clothEntity) {

        return toClothDTO.asClothDTO(clothEntity,"cart");

    }

    private ClothEntity getItemById(Long itemId) {
        return this.clothRepository
                .findById(itemId).orElseThrow(() -> new NullPointerException("Item doesn't exists in DB"));
    }
}
