package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.service.ClothBrandService;
import com.secondhand.secondhand.service.ClothCompositionService;
import com.secondhand.secondhand.service.ClothTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fields")
public class FieldController {

    private final ClothTypeService clothTypeService;
    private final ClothBrandService clothBrandService;
    private final ClothCompositionService clothCompositionService;

    public FieldController(ClothTypeService clothTypeService, ClothBrandService clothBrandService, ClothCompositionService clothCompositionService) {
        this.clothTypeService = clothTypeService;
        this.clothBrandService = clothBrandService;
        this.clothCompositionService = clothCompositionService;
    }

    @GetMapping(value = "")
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
//                    mapFields.get("clothType").add(clothTypeDTO.getName());
                    mapFields.get("clothType").add(clothTypeDTO.getName()+"="+clothTypeDTO.getGender());
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
