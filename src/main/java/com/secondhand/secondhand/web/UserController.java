package com.secondhand.secondhand.web;

import com.secondhand.secondhand.common.CookieFunction;
import com.secondhand.secondhand.exception.UserAddressesLimitException;
import com.secondhand.secondhand.model.binding.UserAddressBindingModel;
import com.secondhand.secondhand.model.binding.UserChangePasswordBindingModel;
import com.secondhand.secondhand.model.binding.UserChangePersonalDataBindingModel;
import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.dto.addressDTO;
import com.secondhand.secondhand.model.entity.AddressEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.UserChangePasswordServiceModel;
import com.secondhand.secondhand.service.CityService;
import com.secondhand.secondhand.service.GuestTokenService;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public UserController(UserService userService, GuestTokenService guestTokenService, CookieFunction cookieFunction, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CityService cityService) {
        this.userService = userService;
        this.guestTokenService = guestTokenService;
        this.cookieFunction = cookieFunction;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
    }

    //    CHECK IF USER IS LOGGED OR NOT! UPDATE FRONT END USER INTERFACE
    @GetMapping("/validate")
    public ResponseEntity<UserInformationDTO> checkUserIfLogged(@AuthenticationPrincipal SecondHandUser principal, HttpServletRequest request, HttpServletResponse response) {
        UserInformationDTO mappedUser = null;
        try {
            UserEntity userByEmail = userService.findUserByEmail(principal.getUserIdentifierEmail());
            mappedUser = modelMapper.map(userByEmail, UserInformationDTO.class);
        } catch (Exception e) {


//            HERE I CHECK THE GSESSIONID THEN SEND TO CLIENT
//            OR IF THE CLIENTS HAS ALREADY IT
//            THEN I ONLY CHECK IF ITS EXISTS IN DB
//            this.cookieFunction.CheckThenIfNecessaryAddNewCookie(request, response);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mappedUser);
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
                    .findUserByEmail(userIdentifierEmail);

        boolean isCurrentPasswordMatches = false;
        boolean isSameNewPasswords = false;

        if (userChangePasswordBindingModel.getCurrentPassword() != null){
          isCurrentPasswordMatches  = passwordEncoder.matches(userChangePasswordBindingModel.getCurrentPassword(), userByEmail.getPassword());
        }

        if (userChangePasswordBindingModel.getNewPassword() != null && userChangePasswordBindingModel.getConfirmPassword() != null){
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

        UserEntity userEntity = this.userService
                .userChangePassword(serviceModel);

        UserInformationDTO mappedUser = modelMapper.map(userEntity, UserInformationDTO.class);
        return ResponseEntity.ok().body(mappedUser);

    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change/data")
    public ResponseEntity<Object> changeUserPersonalData(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserChangePersonalDataBindingModel userChangePersonalDataBindingModel,BindingResult bindingResult,RedirectAttributes redirectAttributes){

        System.out.println(userChangePersonalDataBindingModel.getPhoneNumber());

        if (userChangePersonalDataBindingModel.getFirstName() != null && userChangePersonalDataBindingModel.getFirstName().length() > 0){
            if (userChangePersonalDataBindingModel.getFirstName().length() < 3 || userChangePersonalDataBindingModel.getFirstName().length() > 12){
                String[] codes = new String[]{"length have to between 3 and 12"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("firstName", "firstName", userChangePersonalDataBindingModel.getFirstName(), false, codes, arguments, "firstname's length must be between 3 and 12"));
            }
        }
        if (userChangePersonalDataBindingModel.getLastName() != null && userChangePersonalDataBindingModel.getLastName().length() > 0) {
            if (userChangePersonalDataBindingModel.getLastName().length() < 3 || userChangePersonalDataBindingModel.getLastName().length() > 12){

                String[] codes = new String[]{"length have to between 3 and 12"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("lastName", "lastName", userChangePersonalDataBindingModel.getLastName(), false, codes, arguments, "lastname's length must be between 3 and 12"));

            }
        }
        if (userChangePersonalDataBindingModel.getPhoneNumber() != null && userChangePersonalDataBindingModel.getPhoneNumber().length() > 0){
            Pattern p = Pattern.compile("^(359|0)[0-9]{9}");
            Matcher m = p.matcher(userChangePersonalDataBindingModel.getPhoneNumber());
            boolean b  = m.matches();
            if (!b) {
                String[] codes = new String[]{"invalid phone number"};
                String[] arguments = new String[]{""};
                bindingResult.addError(new FieldError("phoneNumber", "phoneNumber", userChangePersonalDataBindingModel.getPhoneNumber(), false, codes, arguments, "phone number have to start with each of these (359,0) then following by nine numbers"));

            }
        }

        if (bindingResult.hasErrors()){

            redirectAttributes
                    .addFlashAttribute("userChangePersonalDataBindingModel",userChangePersonalDataBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        if (userChangePersonalDataBindingModel.getLastName() == null && userChangePersonalDataBindingModel.getFirstName() == null && userChangePersonalDataBindingModel.getPhoneNumber() == null){
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
    public ResponseEntity<Object> createNewAddress(@AuthenticationPrincipal SecondHandUser secondHandUser, @Valid @RequestBody UserAddressBindingModel userAddressBindingModel,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        System.out.println(userAddressBindingModel.getCity());
        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("userAddressBindingModel",userAddressBindingModel)
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
        }catch (UserAddressesLimitException ex){

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }


        return ResponseEntity.ok().body(modelMapper.map(addressEntity, addressDTO.class));
    }

//    TODO : return location by [3]length
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/location")
    public ResponseEntity<List<CityDTO>> getLocation(@RequestParam(name = "location", defaultValue = "") String location) {

        if (location.length() > 2){

            List<CityDTO> cityByStringLike = this.cityService
                    .getCityByStringLike(location);


            return ResponseEntity.ok(cityByStringLike);
        }

        return null;
    }
}
