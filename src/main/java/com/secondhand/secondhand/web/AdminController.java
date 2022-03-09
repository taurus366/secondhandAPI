package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.binding.ClothCreateBindingModel;
import com.secondhand.secondhand.model.dto.ClothCreateDTO;
import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import com.secondhand.secondhand.service.ClothBrandService;
import com.secondhand.secondhand.service.ClothCompositionService;
import com.secondhand.secondhand.service.ClothService;
import com.secondhand.secondhand.service.ClothTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final ClothService clothService;
    private final ClothTypeService clothTypeService;
    private final ClothBrandService clothBrandService;
    private final ClothCompositionService clothCompositionService;

    public AdminController(ModelMapper modelMapper, ClothService clothService, ClothTypeService clothTypeService, ClothBrandService clothBrandService, ClothCompositionService clothCompositionService) {
        this.modelMapper = modelMapper;
        this.clothService = clothService;
        this.clothTypeService = clothTypeService;
        this.clothBrandService = clothBrandService;
        this.clothCompositionService = clothCompositionService;
    }

//    @PreAuthorize("isAdmin()")
    @PostMapping(value = "/cloth/create")
    public ResponseEntity<Object> addNewCloth(@Valid @ModelAttribute ClothCreateBindingModel clothCreateBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {




        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("clothCreateBindingModel",clothCreateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.clothCreateBindingModel",bindingResult);

            System.out.println(bindingResult.getAllErrors().size());
            System.out.println(bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

            try {

                ClothCreateServiceModel clothServiceModel = modelMapper.map(clothCreateBindingModel, ClothCreateServiceModel.class);

//                clothServiceModel
//                        .setSidePictures(List.of(clothCreateBindingModel.getThirdPicture(),clothCreateBindingModel.getThirdPicture()));
//                clothServiceModel
//                        .getSidePictures().add(clothCreateBindingModel.getThirdPicture());
//                clothServiceModel.getSidePictures().add(clothCreateBindingModel.getFourthPicture());

                ClothCreateDTO addedCloth = modelMapper.map(clothService
                        .createNewCloth(clothServiceModel), ClothCreateDTO.class);


            }catch (Exception e) {
                System.out.println("Ex"+" " + e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("isAdmin()")
    @GetMapping(value = "/cloth/create/fields")
    public ResponseEntity<Object> getRequiredFields(){

        List<Object> list = new ArrayList<>();

        List<ClothColorEnum> clothColorEnums = Arrays.stream(ClothColorEnum.values()).toList();
        List<ClothSeasonEnum> clothSeasonEnums = Arrays.stream(ClothSeasonEnum.values()).toList();
        List<ClothSexEnum> clothSexEnums = Arrays.stream(ClothSexEnum.values()).toList();
        List<ClothSizeEnum> clothSizeEnums = Arrays.stream(ClothSizeEnum.values()).toList();


        list.add(clothColorEnums);
        list.add(clothSeasonEnums);
        list.add(clothSexEnums);
        list.add(clothSizeEnums);


        Map<String, List<String>> mapFields = new HashMap<>();

//       [ClothColorEnum,ClothSizeEnum, etc..]  ENUMS -> List<String> -> Map<K,List<String>>
        list
                .forEach(o -> {
                    if (o == clothColorEnums){

                            if (!mapFields.containsKey("clothColor")) {
                                mapFields.put("clothColor",new ArrayList<>());
                            }
                        for (ClothColorEnum colorEnum : (List<ClothColorEnum>) o) {

                            mapFields.get("clothColor").add(colorEnum.name());
                        }

                    } else if (o == clothSeasonEnums) {

                           if (!mapFields.containsKey("clothSeason")) {
                               mapFields.put("clothSeason",new ArrayList<>());
                           }
                       for (ClothSeasonEnum seasonEnum : (List<ClothSeasonEnum>) o) {

                           mapFields.get("clothSeason").add(seasonEnum.name());
                       }

                    } else if (o == clothSexEnums) {

                            if (!mapFields.containsKey("clothSex")) {
                                mapFields.put("clothSex",new ArrayList<>());
                            }

                        for (ClothSexEnum sexEnum : (List<ClothSexEnum>)o) {

                            mapFields.get("clothSex").add(sexEnum.name());

                        }

                    } else if (o == clothSizeEnums) {

                        if (!mapFields.containsKey("clothSize")){
                            mapFields.put("clothSize",new ArrayList<>());
                        }

                        for (ClothSizeEnum sizeEnum : (List<ClothSizeEnum>)o) {

                            mapFields.get("clothSize").add(sizeEnum.name());
                        }


                    }
                });


//    BRAND , TYPE , COMPOSITION
        this.clothTypeService
                .getAllClothType()
                .forEach(clothTypeDTO -> {
                    if (!mapFields.containsKey("clothType")) {
                        mapFields.put("clothType",new ArrayList<>());
                    }
                    mapFields.get("clothType").add(clothTypeDTO.getName());
                });

        this.clothBrandService
                .getAllClothBrand()
                .forEach(clothBrandDTO -> {
                    if (!mapFields.containsKey("clothBrand")){
                        mapFields.put("clothBrand",new ArrayList<>());
                    }
                    mapFields.get("clothBrand").add(clothBrandDTO.getName());
                });

        this.clothCompositionService
                .getAllClothComposition()
                .forEach(clothCompositionDTO -> {
                    if (!mapFields.containsKey("clothComposition")){
                        mapFields.put("clothComposition",new ArrayList<>());
                    }
                    mapFields.get("clothComposition").add(clothCompositionDTO.getName());
                });


        return ResponseEntity.ok().body(mapFields);
    }
}
