package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.binding.UserRegistrationBindingModel;
import com.secondhand.secondhand.model.dto.UserRegistrationDTO;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserRegistrationController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserRegistrationDTO> registerNewUser(@Valid @RequestBody UserRegistrationBindingModel userRegistrationBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean isSamePasswords = userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword());

        System.out.println(userRegistrationBindingModel.getEmail());
        System.out.println(userRegistrationBindingModel.getSex());


        if (bindingResult.hasErrors() || !isSamePasswords) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel")
                    .addFlashAttribute("isSamePasswords", isSamePasswords);

            System.out.println(bindingResult.getAllErrors().size());
            System.out.println(bindingResult.getAllErrors().get(0));

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


        UserRegistrationServiceModel serviceModel = modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        UserRegistrationDTO userRegistrationDTO = userService
                .registerNewUser(serviceModel);

//             HERE SHOULD RETURN INFORMATION TO FROND END TO RENDERING AFTER CLIENT REGISTERED! - IT MEANS LOGIN AFTER REGISTER !
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistrationDTO);
    }

    @PostMapping("/register/2")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().build();
    }
}
