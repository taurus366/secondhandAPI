package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.dto.PictureDTO;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.ClothEntity;
import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.entity.enums.UserSexEnum;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.repository.ClothRepository;
import com.secondhand.secondhand.repository.GuestTokenRepository;
import com.secondhand.secondhand.repository.RoleRepository;
import com.secondhand.secondhand.repository.UserRepository;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final SecondHandUserServiceImpl secondHandUserService;
    private final GuestTokenRepository guestTokenRepository;
    private final ClothRepository clothRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, SecondHandUserServiceImpl secondHandUserService, GuestTokenRepository guestTokenRepository, ClothRepository clothRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.secondHandUserService = secondHandUserService;
        this.guestTokenRepository = guestTokenRepository;
        this.clothRepository = clothRepository;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserInformationDTO registerNewUserAndLogin(UserRegistrationServiceModel registerServiceModel) {

        RoleEntity userRoleFromDB = roleRepository.findByRole(RoleEnum.USER);

        UserEntity newUser = new UserEntity();
        newUser
                .setFirstName(registerServiceModel.getFirstName())
                .setLastName(registerServiceModel.getLastName())
                .setEmail(registerServiceModel.getEmail())
                .setPassword(passwordEncoder.encode(registerServiceModel.getPassword()))
                .setActive(true)
                .setSex(UserSexEnum.valueOf(registerServiceModel.getSex().name()))
                .getRoles().add(userRoleFromDB);
        newUser
                .setCreated(Instant.now())
                .setModified(Instant.now());

        UserEntity savedUser = userRepository
                .save(newUser);


        UserDetails userDetails = secondHandUserService.loadUserByUsername(newUser.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                newUser.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

//        if (savedUser != null){
//
////            UserDetails userDetails =
//
//        }

        return modelMapper.map(savedUser, UserInformationDTO.class);

    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {


        return Optional.empty();
    }

    @Override
    public UserInformationDTO findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmailGraph(email).orElseThrow(() -> new UsernameNotFoundException("The user couldn't found"));

        return modelMapper.map(userEntity, UserInformationDTO.class);
    }

    @Override
    public boolean isAdmin(String userEmail) {

        return this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail + "doesn't exists in database"))
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(roleEnum -> roleEnum.equals(RoleEnum.ADMINISTRATOR));
    }

//    @Override
//    public List<ClothDTO> getUserCart(String userEmail) {
//
//        UserEntity byEmail = userRepository.findByEmail(userEmail).orElseThrow();
//
//        return byEmail.getClothesList()
//                .stream()
//                .map(this::asClothDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ClothDTO> getGuestCart(String cookie) {
//
//        GuestTokenEntity byToken = this.guestTokenRepository
//                .findByToken(cookie);
//
//        return byToken
//                .getClothesList()
//                .stream()
//                .map(this::asClothDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void changeGuestCartToUserCart(String userEmail, Cookie[] guestCookie) {
//        String cookieValue = "";
//        UserEntity user = null;
//
//        if (guestCookie.length > 0) {
//
//            for (Cookie cookie : guestCookie) {
//                if (cookie.getName().equals("GSESSIONID")) {
//                    cookieValue = cookie.getValue();
//                    break;
//                }
//            }
//
//            if (cookieValue.length() > 0) {
//
//                user = this.userRepository
//                        .findByEmail(userEmail).orElseThrow((() -> new UsernameNotFoundException("Couldn't find user!")));
//
//                GuestTokenEntity guest = this.guestTokenRepository
//                        .findByToken(cookieValue);
//                if (guest.getClothesList().size() > 0) {
//
//                    user.getClothesList().addAll(guest.getClothesList());
//                    guest.setClothesList(new ArrayList<>());
//
//                    userRepository
//                            .save(user);
//
//                    guestTokenRepository
//                            .save(guest);
//                }
//
//            }
//
//        }
//
//    }
//
//    @Override
//    public ClothDTO addItemToUserCart(String userEmail, Long itemId) {
//
//        UserEntity userByEmail = this.userRepository
//                .findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists in DB"));
//
//        ClothEntity clothById = getItemById(itemId);
//
//        if (userByEmail.getClothesList().contains(clothById)){
//
////            IMPLEMENTED OWN EXCEPTION !
//            throw new ItemAlreadyExistsException("");
//
//        }
//
//        userByEmail
//                .getClothesList()
//                .add(clothById);
//        this.userRepository
//                .save(userByEmail);
//
//        return asClothDTO(clothById);
//    }
//
//    @Override
//    public boolean removeItemFromUserCart(String userEmail, Long itemId) {
//        UserEntity user = this.userRepository
//                .findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user !"));
//
//        ClothEntity clothByID = getItemById(itemId);
//
//        int oldUserCart = user.getClothesList().size();
//
//        user
//                .setClothesList(
//                        user.getClothesList()
//                                .stream()
//                                .filter(clothEntity -> !Objects.equals(clothEntity.getId(), clothByID.getId()))
//                                .collect(Collectors.toList()));
//
//
//        this.userRepository
//                .save(user);
//
//        int lastUserCart = user.getClothesList().size();
//
//        return oldUserCart > lastUserCart;
//    }
//
//    @Override
//    public ClothDTO addItemToGuestCart(String guestCookie, Long itemId) {
//
//        GuestTokenEntity guestByCookie = this.guestTokenRepository
//                .findByToken(guestCookie);
//
//        ClothEntity clothById = getItemById(itemId);
//
//       guestByCookie.getClothesList()
//               .forEach(clothEntity -> {
//                   if (Objects.equals(clothEntity.getId(), clothById.getId())){
//                       //            IMPLEMENTED OWN EXCEPTION !
//                       System.out.println("works");
//                       throw new ItemAlreadyExistsException("");
//                   }
//               });
//
//        guestByCookie
//                .getClothesList()
//                .add(clothById);
//        this.guestTokenRepository
//                .save(guestByCookie);
//
//        return asClothDTO(clothById);
//    }
//
//    @Override
//    public boolean removeItemFromGuestCart(String guestCookie, Long itemId) {
////        SHOULD IMPLEMENT IF GUEST DOESN'T EXISTS IN DB
//
//        GuestTokenEntity guest = this.guestTokenRepository
//                .findByToken(guestCookie);
//
//        ClothEntity clothByID = getItemById(itemId);
//
//
//        int oldGuestCart = guest.getClothesList().size();
//
//
//        guest
//                .setClothesList(
//                        guest
//                                .getClothesList()
//                                .stream().filter(clothEntity -> !Objects.equals(clothEntity.getId(), clothByID.getId()))
//                                .collect(Collectors.toList()));
//
//        this.guestTokenRepository
//                .save(guest);
//        int lastGuestCart = guest.getClothesList().size();
//        return oldGuestCart > lastGuestCart;
//    }
//
//
//    private ClothDTO asClothDTO(ClothEntity clothEntity) {
//
//        PictureDTO coverPicture = new PictureDTO();
//        coverPicture.setUrl(clothEntity.getCoverPicture().getUrl())
//                .setPublicId(clothEntity.getCoverPicture().getPublicId());
//
//        PictureDTO frontPicture = new PictureDTO();
//        frontPicture.setUrl(clothEntity.getFrontPicture().getUrl())
//                .setPublicId(clothEntity.getFrontPicture().getPublicId());
//
//        List<PictureDTO> sidePictures = clothEntity.getSidePictures()
//                .stream()
//                .map(pictureEntity -> {
//                    return new PictureDTO()
//                            .setUrl(pictureEntity.getUrl())
//                            .setPublicId(pictureEntity.getPublicId());
//                })
//                .collect(Collectors.toList());
//
//
//        ClothDTO clothDTO = new ClothDTO();
//        clothDTO
//                .setId(clothEntity.getId())
//                .setType(clothEntity.getClothType().getName())
//                .setBrand(clothEntity.getClothBrandEntity().getName())
//                .setSize(clothEntity.getClothSize().name())
//                .setSex(clothEntity.getClothSex().name())
//                .setColor(clothEntity.getClothColor().name())
//                .setSeason(clothEntity.getClothSeason().name())
//                .setComposition(clothEntity.getClothComposition().getName())
//                .setDescription(clothEntity.getDescription())
//                .setStartPrice(clothEntity.getStartPrice())
//                .setNewPrice(clothEntity.getNewPrice())
//                .setLikes(clothEntity.getLikes())
//                .setSidePictures(sidePictures)
//                .setCoverPicture(coverPicture)
//                .setFrontPicture(frontPicture)
//                .setQuantity(clothEntity.getQuantity());
//
//
//        return clothDTO;
//    }
//
//    private ClothEntity getItemById(Long itemId) {
//        return this.clothRepository
//                .findById(itemId).orElseThrow(() -> new NullPointerException("Item doesn't exists in DB"));
//    }

}
