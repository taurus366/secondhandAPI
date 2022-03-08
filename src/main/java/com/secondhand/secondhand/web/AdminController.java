package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.binding.ClothCreateBindingModel;
import com.secondhand.secondhand.service.ClothService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final ClothService clothService;

    public AdminController(ModelMapper modelMapper, ClothService clothService) {
        this.modelMapper = modelMapper;
        this.clothService = clothService;
    }

//    @PreAuthorize("isAdmin()")
    @PostMapping(value = "/cloth/create")
    public ResponseEntity<Object> addNewCloth(@Valid @ModelAttribute ClothCreateBindingModel clothCreateBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        System.out.println(clothCreateBindingModel.getCoverPicture() == null);
        System.out.println(clothCreateBindingModel.getFrontPicture() == null);
        System.out.println(clothCreateBindingModel.getThirdPicture() == null);
        System.out.println(clothCreateBindingModel.getFourthPicture() == null);


        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("clothCreateBindingModel",clothCreateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.clothCreateBindingModel",bindingResult);

            System.out.println(bindingResult.getAllErrors().size());
            System.out.println(bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

//        ClothCreateServiceModel newCloth = clothService.createNewCloth(modelMapper.map(clothCreateBindingModel, ClothCreateServiceModel.class));

        return ResponseEntity.status(HttpStatus.CREATED).body("Successful created cloth!");
    }
}
