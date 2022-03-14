package com.secondhand.secondhand.web;


import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.service.ClothService;
import org.apache.http.protocol.HTTP;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clothes")
public class ClothController {

    private final ClothService clothService;

    public ClothController(ClothService clothService) {
        this.clothService = clothService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<Object> getAllClothesOrFilter(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(name = "brand", defaultValue = "null", required = false) String brand,
            @RequestParam(name = "size", defaultValue = "null", required = false) String size,
            @RequestParam(name = "discount", defaultValue = "-1", required = false) Long discount,
            @RequestParam(name = "color", defaultValue = "null", required = false) String color,
            @RequestParam(name = "priceLow", defaultValue = "-1", required = false) Long priceLow,
            @RequestParam(name = "priceHigh", defaultValue = "-1", required = false) Long priceHigh,
            @RequestParam(name = "sortBy", defaultValue = "desc", required = false) String sortBy,
            @RequestParam(name = "sex", defaultValue = "null", required = false) String sex,
            @RequestParam(name = "type", defaultValue = "null", required = false) List<String> type
    ) {




        Page<ClothDTO> allClothes = clothService
                .getAllClothes(pageNo,
                        pageSize,
                        brand,
                        size,
                        discount,
                        color,
                        priceLow,
                        priceHigh,
                        sex,
                        type,
                        sortBy);



        return ResponseEntity.ok(allClothes);
    }
}
