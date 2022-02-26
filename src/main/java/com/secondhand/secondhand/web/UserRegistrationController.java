package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.binding.UserRegistrationBindingModel;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserRegistrationController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserRegistrationController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserRegistrationBindingModel userRegistrationBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean isSamePasswords = userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword());

        System.out.println(userRegistrationBindingModel.getEmail());
        System.out.println(userRegistrationBindingModel.getSex());


        if (bindingResult.hasErrors() || !isSamePasswords) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel")
                    .addFlashAttribute("isSamePasswords", isSamePasswords);

            System.out.println(bindingResult.getAllErrors().size());
            System.out.println(bindingResult.getAllErrors());
//            System.out.println(bindingResult.getFieldError().);


//            HERE I MUST RETURN THE ERROR!

            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage() + " " + objectError.getCodes()[1].split("\\.")[1] ));

            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getModel().values().toString());
        }

// HERE I CONVERT THE REQUEST FROM FRONT END / from   userRegistrationBindingModel >to> UserRegistrationServiceModel
        UserRegistrationServiceModel serviceModel = modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

//        HERE I SEND THE INFO TO userService THEN REGISTER THE USER AND LOGIN
        UserInformationDTO userInformationDTO = userService
                .registerNewUserAndLogin(serviceModel);

//             HERE SHOULD RETURN INFORMATION TO FROND END TO RENDERING AFTER CLIENT REGISTERED! - IT MEANS LOGIN AFTER REGISTER !
        return ResponseEntity.status(HttpStatus.CREATED).body(userInformationDTO.toString());
    }





}
