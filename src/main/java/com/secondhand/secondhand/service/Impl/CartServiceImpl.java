package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.common.MapperFunction;
import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.entity.ClothEntity;
import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.repository.ClothRepository;
import com.secondhand.secondhand.repository.GuestTokenRepository;
import com.secondhand.secondhand.repository.RoleRepository;
import com.secondhand.secondhand.repository.UserRepository;
import com.secondhand.secondhand.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final GuestTokenRepository guestTokenRepository;
    private final ClothRepository clothRepository;
    private final MapperFunction toClothDTO;

    public CartServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, SecondHandUserServiceImpl secondHandUserService, GuestTokenRepository guestTokenRepository, ClothRepository clothRepository, MapperFunction toClothDTO) {
        this.userRepository = userRepository;
        this.guestTokenRepository = guestTokenRepository;
        this.clothRepository = clothRepository;
        this.toClothDTO = toClothDTO;
    }


    @Override
    public List<ClothDTO> getUserCart(String userEmail) {

        UserEntity byEmail = userRepository.findByEmail(userEmail).orElseThrow();

        return byEmail.getClothesList()
                .stream()
                .map(this::asClothDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClothDTO> getGuestCart(String cookie) {

        GuestTokenEntity byToken = this.guestTokenRepository
                .findByToken(cookie);

        return byToken
                .getClothesList()
                .stream()
                .map(this::asClothDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void changeGuestCartToUserCart(String userEmail, Cookie[] guestCookie) {
        String cookieValue = "";
        UserEntity user = null;

        if ( guestCookie != null && guestCookie.length > 0) {

            for (Cookie cookie : guestCookie) {
                if (cookie.getName().equals("GSESSIONID")) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }

            if (cookieValue.length() > 0) {

                user = this.userRepository
                        .findByEmail(userEmail).orElseThrow((() -> new UsernameNotFoundException("Couldn't find user!")));

                GuestTokenEntity guest = this.guestTokenRepository
                        .findByToken(cookieValue);
                if (guest.getClothesList().size() > 0) {

                    user.getClothesList().addAll(guest.getClothesList());
                    guest.setClothesList(new ArrayList<>());

                    userRepository
                            .save(user);

                    guestTokenRepository
                            .save(guest);
                }

            }

        }

    }

    @Override
    public ClothDTO addItemToUserCart(String userEmail, Long itemId) {

        UserEntity userByEmail = this.userRepository
                .findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists in DB"));

        ClothEntity clothById = getItemById(itemId);

        if (userByEmail.getClothesList().contains(clothById)) {

//            IMPLEMENTED OWN EXCEPTION !
            throw new ItemAlreadyExistsException("");

        }

        userByEmail
                .getClothesList()
                .add(clothById);
        this.userRepository
                .save(userByEmail);

        return asClothDTO(clothById);
    }

    @Override
    public boolean removeItemFromUserCart(String userEmail, Long itemId) {
        UserEntity user = this.userRepository
                .findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user !"));

        ClothEntity clothByID = getItemById(itemId);

        int oldUserCart = user.getClothesList().size();

        user
                .setClothesList(
                        user.getClothesList()
                                .stream()
                                .filter(clothEntity -> !Objects.equals(clothEntity.getId(), clothByID.getId()))
                                .collect(Collectors.toList()));


        this.userRepository
                .save(user);

        int lastUserCart = user.getClothesList().size();

        return oldUserCart > lastUserCart;
    }

    @Override
    public ClothDTO addItemToGuestCart(String guestCookie, Long itemId) {

        GuestTokenEntity guestByCookie = this.guestTokenRepository
                .findByToken(guestCookie);

        ClothEntity clothById = getItemById(itemId);

        guestByCookie.getClothesList()
                .forEach(clothEntity -> {
                    if (Objects.equals(clothEntity.getId(), clothById.getId())) {

                        throw new ItemAlreadyExistsException("");
                    }
                });

        guestByCookie
                .getClothesList()
                .add(clothById);
        this.guestTokenRepository
                .save(guestByCookie);

        return asClothDTO(clothById);
    }

    @Override
    public boolean removeItemFromGuestCart(String guestCookie, Long itemId) {
//        SHOULD IMPLEMENT IF GUEST DOESN'T EXISTS IN DB

        GuestTokenEntity guest = this.guestTokenRepository
                .findByToken(guestCookie);

        ClothEntity clothByID = getItemById(itemId);


        int oldGuestCart = guest.getClothesList().size();


        guest
                .setClothesList(
                        guest
                                .getClothesList()
                                .stream().filter(clothEntity -> !Objects.equals(clothEntity.getId(), clothByID.getId()))
                                .collect(Collectors.toList()));

        this.guestTokenRepository
                .save(guest);
        int lastGuestCart = guest.getClothesList().size();
        return oldGuestCart > lastGuestCart;
    }


    private ClothDTO asClothDTO(ClothEntity clothEntity) {

        return toClothDTO.asClothDTO(clothEntity);
    }

    private ClothEntity getItemById(Long itemId) {
        return this.clothRepository
                .findById(itemId).orElseThrow(() -> new NullPointerException("Item doesn't exists in DB"));
    }
}
