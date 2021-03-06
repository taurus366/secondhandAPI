package com.secondhand.secondhand.model.service;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.validator.ClothBrandExists;
import com.secondhand.secondhand.model.validator.ClothCompositionExists;
import com.secondhand.secondhand.model.validator.ClothTypeExists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ClothCreateServiceModel {


    private MultipartFile coverPicture;


    private MultipartFile frontPicture;

    private MultipartFile thirdPicture;
    private MultipartFile fourthPicture;

//    private List<MultipartFile> sidePictures = new ArrayList<>();

//    private MultipartFile thirdPicture;
//
//
//    private MultipartFile fourthPicture;

    private ClothSexEnum sex;

    private String brand;

    private ClothColorEnum color;

    private String type;

    private ClothSizeEnum size;

    private ClothSeasonEnum season;

    private String composition;

    private String description;

    private Long startPrice;

    private Long newPrice;

    public ClothCreateServiceModel() {
    }

    public MultipartFile getCoverPicture() {
        return coverPicture;
    }

    public ClothCreateServiceModel setCoverPicture(MultipartFile coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public MultipartFile getFrontPicture() {
        return frontPicture;
    }

    public ClothCreateServiceModel setFrontPicture(MultipartFile frontPicture) {
        this.frontPicture = frontPicture;
        return this;
    }

    public MultipartFile getThirdPicture() {
        return thirdPicture;
    }

    public ClothCreateServiceModel setThirdPicture(MultipartFile thirdPicture) {
        this.thirdPicture = thirdPicture;
        return this;
    }

    public MultipartFile getFourthPicture() {
        return fourthPicture;
    }

    public ClothCreateServiceModel setFourthPicture(MultipartFile fourthPicture) {
        this.fourthPicture = fourthPicture;
        return this;
    }

    public ClothSexEnum getSex() {
        return sex;
    }

    public ClothCreateServiceModel setSex(ClothSexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothCreateServiceModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ClothColorEnum getColor() {
        return color;
    }

    public ClothCreateServiceModel setColor(ClothColorEnum color) {
        this.color = color;
        return this;
    }

    public String getType() {
        return type;
    }

    public ClothCreateServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public ClothSizeEnum getSize() {
        return size;
    }

    public ClothCreateServiceModel setSize(ClothSizeEnum size) {
        this.size = size;
        return this;
    }

    public ClothSeasonEnum getSeason() {
        return season;
    }

    public ClothCreateServiceModel setSeason(ClothSeasonEnum season) {
        this.season = season;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public ClothCreateServiceModel setComposition(String composition) {
        this.composition = composition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothCreateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public ClothCreateServiceModel setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public ClothCreateServiceModel setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
        return this;
    }
}
