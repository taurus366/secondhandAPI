package com.secondhand.secondhand.web;

import com.secondhand.secondhand.common.CookieFunction;
import com.secondhand.secondhand.exception.UserAddressesLimitException;
import com.secondhand.secondhand.model.binding.*;
import com.secondhand.secondhand.model.dto.*;
import com.secondhand.secondhand.model.entity.AddressEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.SpeedyNewAddressServiceModel;
import com.secondhand.secondhand.model.service.UserChangePasswordServiceModel;
import com.secondhand.secondhand.model.service.UserEditAddressServiceModel;
import com.secondhand.secondhand.service.CityService;
import com.secondhand.secondhand.service.GuestTokenService;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.SpeedyCityService;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GuestTokenService guestTokenService;
    private final CookieFunction cookieFunction;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CityService cityService;
    private final SpeedyCityService speedyCityService;

    public UserController(UserService userService, GuestTokenService guestTokenService, CookieFunction cookieFunction, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CityService cityService, SpeedyCityService speedyCityService) {
        this.userService = userService;
        this.guestTokenService = guestTokenService;
        this.cookieFunction = cookieFunction;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
        this.speedyCityService = speedyCityService;
    }

    //    CHECK IF USER IS LOGGED OR NOT! UPDATE FRONT END USER INTERFACE
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/validate")
    public ResponseEntity<Object> checkUserIfLogged(@AuthenticationPrincipal SecondHandUser principal, HttpServletRequest request, HttpServletResponse response) {
//        UserInformationDTO userByEmailValidate = null;
//        try {
////            System.out.println(principal.getUserIdentifierEmail());
////            mappedUser = modelMapper.map(userByEmail, UserInformationDTO.class);
//        } catch (Exception e) {
//
////            HERE I CHECK THE GSESSIONID THEN SEND TO CLIENT
////            OR IF THE CLIENTS HAS ALREADY IT
////            THEN I ONLY CHECK IF ITS EXISTS IN DB
////            this.cookieFunction.CheckThenIfNecessaryAddNewCookie(request, response);
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
            // TODO -> here shouldn't return any information !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! JUST CHECK IF IT IS LOGGED !
//        return ResponseEntity.status(HttpStatus.OK).body(userByEmailValidate);

        UserInformationDTO userInformationDTO = userService
                .validateUserIfLoggedAndRoles(principal.getUserIdentifierEmail());

        return ResponseEntity.status(HttpStatus.OK).body(userInformationDTO);
    }

    @PreAuthorize("isAdmin()")
    @GetMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getCookies()[0].getValue());

        Cookie cookie = new Cookie("test2", "123456");
        cookie.setHttpOnly(true);

        Cookie cookie2 = new Cookie("test23", "1234563");
        cookie2.setHttpOnly(true);

        response.addCookie(cookie);
        response.addCookie(cookie2);
    }

    //    HERE I CHECK IF THE USER IS LOGGED
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change/password")
    public ResponseEntity<Object> changeUserPassword(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserChangePasswordBindingModel userChangePasswordBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        String userIdentifierEmail = secondHandUser.getUserIdentifierEmail();

        UserEntity userByEmail = userService
                .findUserByEmailDefault(userIdentifierEmail);

        boolean isCurrentPasswordMatches = false;
        boolean isSameNewPasswords = false;

        if (userChangePasswordBindingModel.getCurrentPassword() != null) {
            isCurrentPasswordMatches = passwordEncoder.matches(userChangePasswordBindingModel.getCurrentPassword(), userByEmail.getPassword());
        }

        if (userChangePasswordBindingModel.getNewPassword() != null && userChangePasswordBindingModel.getConfirmPassword() != null) {
            isSameNewPasswords = userChangePasswordBindingModel.getNewPassword().equals(userChangePasswordBindingModel.getConfirmPassword());
        }

        if (!isSameNewPasswords) {
            String[] codes = new String[]{"not match"};
            String[] arguments = new String[]{""};
            bindingResult.addError(new FieldError("confirmPassword", "confirmPassword", userChangePasswordBindingModel.getConfirmPassword(), false, codes, arguments, "passwords aren't matched!"));
        }

        if (!isCurrentPasswordMatches) {
            String[] codes = new String[]{"password doesn't match"};
            String[] arguments = new String[]{""};
            bindingResult.addError(new FieldError("currentPassword", "currentPassword", userChangePasswordBindingModel.getCurrentPassword(), false, codes, arguments, "current password aren't valid!"));
        }

        if (bindingResult.hasErrors() || !isCurrentPasswordMatches || !isSameNewPasswords) {

            redirectAttributes
                    .addFlashAttribute("userChangePasswordBindingModel", userChangePasswordBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel")
                    .addFlashAttribute("isCurrentPasswordMatches", isCurrentPasswordMatches)
                    .addFlashAttribute("isSameNewPasswords", isSameNewPasswords);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        UserChangePasswordServiceModel serviceModel = modelMapper.map(userChangePasswordBindingModel, UserChangePasswordServiceModel.class);
        serviceModel.setUserEmail(userIdentifierEmail);

        try {
            this.userService
                    .userChangePassword(serviceModel);
        } catch (UsernameNotFoundException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

//        HERE MUST RETURN ONLY CODE 200 thats mean that request successful completed!
//        UserInformationDTO mappedUser = modelMapper.map(userEntity, UserInformationDTO.class);
        return ResponseEntity.ok().build();

    }

    //   TODO -> CHANGE CODES !
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change/data")
    public ResponseEntity<Object> changeUserPersonalData(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserChangePersonalDataBindingModel userChangePersonalDataBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(userChangePersonalDataBindingModel.getPhoneNumber());

        if (userChangePersonalDataBindingModel.getFirstName() != null && userChangePersonalDataBindingModel.getFirstName().length() > 0) {
            if (userChangePersonalDataBindingModel.getFirstName().length() < 3 || userChangePersonalDataBindingModel.getFirstName().length() > 12) {
                String[] codes = new String[]{"length have to between 3 and 12"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("firstName", "firstName", userChangePersonalDataBindingModel.getFirstName(), false, codes, arguments, "firstname's length must be between 3 and 12"));
            }
        }
        if (userChangePersonalDataBindingModel.getLastName() != null && userChangePersonalDataBindingModel.getLastName().length() > 0) {
            if (userChangePersonalDataBindingModel.getLastName().length() < 3 || userChangePersonalDataBindingModel.getLastName().length() > 12) {

                String[] codes = new String[]{"length have to between 3 and 12"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("lastName", "lastName", userChangePersonalDataBindingModel.getLastName(), false, codes, arguments, "lastname's length must be between 3 and 12"));

            }
        }
//         TODO -> I MUST CHANGE IT LIKE EXCEPTION CLASS
        if (userChangePersonalDataBindingModel.getPhoneNumber() != null && userChangePersonalDataBindingModel.getPhoneNumber().length() > 0) {
            userChangePersonalDataBindingModel.setPhoneNumber(userChangePersonalDataBindingModel.getPhoneNumber().length() == 9 ? "0" + userChangePersonalDataBindingModel.getPhoneNumber() : userChangePersonalDataBindingModel.getPhoneNumber());
            Pattern p = Pattern.compile("^(359|0)[0-9]{9}");
            Matcher m = p.matcher(userChangePersonalDataBindingModel.getPhoneNumber());
            boolean b = m.matches();
            if (!b) {
                String[] codes = new String[]{"invalid phone number"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("phoneNumber", "phoneNumber", userChangePersonalDataBindingModel.getPhoneNumber(), false, codes, arguments, "phone number have to start with each of these (359,0) then following by nine numbers"));

            }
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("userChangePersonalDataBindingModel", userChangePersonalDataBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        if (userChangePersonalDataBindingModel.getLastName() == null && userChangePersonalDataBindingModel.getFirstName() == null && userChangePersonalDataBindingModel.getPhoneNumber() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("FIELDS ARE NULL!");
        }

        userChangePersonalDataBindingModel.setUserEmail(secondHandUser.getUserIdentifierEmail());

        UserEntity userEntity = this.userService
                .userChangeData(userChangePersonalDataBindingModel);

        UserInformationDTO mappedUser = modelMapper
                .map(userEntity, UserInformationDTO.class);


        return ResponseEntity.ok().body(mappedUser);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new/address")
    public ResponseEntity<Object> createNewAddress(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserAddressBindingModel userAddressBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userAddressBindingModel", userAddressBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userAddressBindingModel");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        String userIdentifierEmail = secondHandUser
                .getUserIdentifierEmail();

        userAddressBindingModel
                .setUserEmail(userIdentifierEmail);


        AddressEntity addressEntity;

        try {

            addressEntity = userService
                    .addNewAddress(userAddressBindingModel);

        } catch (UserAddressesLimitException ex) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
        AddressDTO mappedUserAddress = modelMapper.map(addressEntity, AddressDTO.class);
        mappedUserAddress.setId(addressEntity.getId());

        return ResponseEntity.ok().body(mappedUserAddress);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/address")
    public ResponseEntity<Object> editCurrentAddress(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserEditAddressBindingModel userEditAddressBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(secondHandUser.getUserIdentifierEmail() + "" + secondHandUser.getUserId());
        System.out.println(userEditAddressBindingModel.getApartment() + userEditAddressBindingModel.getId());

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("userEditAddressBindingModel", userEditAddressBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userEditAddressBindingModel", bindingResult);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        UserEditAddressServiceModel mapped = modelMapper.map(userEditAddressBindingModel, UserEditAddressServiceModel.class);
        mapped.setUserAuthEmail(secondHandUser.getUserIdentifierEmail());

        AddressEntity addressEntity = this.userService
                .changeExistsAddress(mapped);

        if (addressEntity == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("We couldn't find the address,Please contact with admin");
        }


        return ResponseEntity.ok().body(addressEntity);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete/address")
    public ResponseEntity<Object> deleteAddress(@AuthenticationPrincipal SecondHandUser secondHandUser, @RequestParam(name = "id", defaultValue = "0") Long id) {


        if (secondHandUser != null && id != 0) {

            boolean isDeleted = userService
                    .deleteOneOwnAddress(id, secondHandUser.getUserIdentifierEmail());

            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("We couldn't delete the address , please contact with the support or try again after 1 hour");
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something gone wrong! please contact with support");

    }


    //    TODO : return location by [3]length
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/location")
    public ResponseEntity<Object> getLocation(
            @RequestParam(name = "location", defaultValue = "") String location,
            @RequestParam(name = "speedy", defaultValue = "") String speedy
    ) {

        List<CityDTO> cities = null;

//        BULGARIAN's CITIES
        if (location.length() > 2) {

            cities = this.cityService
                    .getCityByStringLike(location);
        }
//          SPEEDY LOCATION
        else if (speedy.length() > 2) {

            cities = this.speedyCityService
                    .getCityByStringLike(speedy);
        }

        return ResponseEntity.ok(cities);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/address")
    public ResponseEntity<Object> getSpeedyAddress(
            @RequestParam(name = "city", defaultValue = "") String cityId) {

        long cityIdLongValue = 0L;
        List<SpeedyAddressFormDTO> speedyAddressesByCityId = null;

        try {
            cityIdLongValue = Long.parseLong(cityId);

            speedyAddressesByCityId = this.speedyCityService
                    .getSpeedyAddressesByCityId(cityIdLongValue);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Number is allowed!");
        } catch (NullPointerException exx) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("We couldn't find address with that city id" + " " + cityIdLongValue);
        }

        return ResponseEntity.ok(speedyAddressesByCityId);
    }

//   SPEEDY ADDRESS
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new/office/address")
    public ResponseEntity<Object> saveNewSpeedyAddress(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody SpeedyNewAddressBindingModel speedyNewAddressBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        String userEmail = secondHandUser.getUserIdentifierEmail();

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("speedyNewAddressBindingModel", speedyNewAddressBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.speedyNewAddressBindingModel", bindingResult);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        SpeedyNewAddressServiceModel mapped = modelMapper.map(speedyNewAddressBindingModel, SpeedyNewAddressServiceModel.class);

        String lastPhoneNumber = speedyNewAddressBindingModel.getPhoneNumber().length() == 9 ? "0" + speedyNewAddressBindingModel.getPhoneNumber() : speedyNewAddressBindingModel.getPhoneNumber();

        mapped
                .setUserEmail(userEmail)
                .setPhoneNumber(lastPhoneNumber);

        SpeedyAddressDTO speedyAddressDTO = null;

        try {
            speedyAddressDTO = this.userService
                    .addSpeedyOfficeAddressToUser(mapped);
        } catch (UserAddressesLimitException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }


        return ResponseEntity.ok().body(speedyAddressDTO);
    }


//    POPULATE USER INFORMATION LIKE [firstName,lastName,phoneNumber..]
//    NOT USED YET
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info/data")
    public ResponseEntity<Object> getUserPersonalData(@AuthenticationPrincipal SecondHandUser secondHandUser) {
        UserInformationDTO userInformationDTO = this.userService
                .userPersonalData(secondHandUser.getUserIdentifierEmail());
        return ResponseEntity.ok(userInformationDTO);
    }

//    POPULATE USER ADDRESSES LIKE [ownAddress,speedyAddress]
//    NOT USED YET
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info/addresses")
    public ResponseEntity<Object> getUserAddresses(@AuthenticationPrincipal SecondHandUser secondHandUser) {

        UserInformationDTO userInformationDTO = this.userService
                .userAddresses(secondHandUser.getUserIdentifierEmail());

        return ResponseEntity.ok(userInformationDTO);
    }
}
