package com.secondhand.secondhand.web;


import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.service.ClothService;
import org.apache.http.protocol.HTTP;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
            @RequestParam(name = "type", defaultValue = "null", required = false) List<String> type,
            @RequestParam(name = "itemType", defaultValue = "null", required = false) String itemType
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
                        itemType,
                        sortBy);



        return ResponseEntity.ok(allClothes);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Object> getClothById(@PathVariable String id){
        ClothDTO clothByID = null;

       try {
           clothByID = this.clothService.getClothByID(Long.parseLong(id));
       }catch (NoSuchElementException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We couldn't found the item");
       } catch (NumberFormatException ex) {
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please bu sure that you write number not string");
       }

        return ResponseEntity.ok(clothByID);
    }
}
