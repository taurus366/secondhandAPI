package com.secondhand.secondhand.model.dto;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ClothCreateDTO {

    private MultipartFile coverPicture;
    private MultipartFile frontPicture;
    private List<MultipartFile> sidePictures = new ArrayList<>();
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

    public ClothCreateDTO() {
    }

    public MultipartFile getCoverPicture() {
        return coverPicture;
    }

    public ClothCreateDTO setCoverPicture(MultipartFile coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public MultipartFile getFrontPicture() {
        return frontPicture;
    }

    public ClothCreateDTO setFrontPicture(MultipartFile frontPicture) {
        this.frontPicture = frontPicture;
        return this;
    }

    public List<MultipartFile> getSidePictures() {
        return sidePictures;
    }

    public ClothCreateDTO setSidePictures(List<MultipartFile> sidePictures) {
        this.sidePictures = sidePictures;
        return this;
    }

    public ClothSexEnum getSex() {
        return sex;
    }

    public ClothCreateDTO setSex(ClothSexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothCreateDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ClothColorEnum getColor() {
        return color;
    }

    public ClothCreateDTO setColor(ClothColorEnum color) {
        this.color = color;
        return this;
    }

    public String getType() {
        return type;
    }

    public ClothCreateDTO setType(String type) {
        this.type = type;
        return this;
    }

    public ClothSizeEnum getSize() {
        return size;
    }

    public ClothCreateDTO setSize(ClothSizeEnum size) {
        this.size = size;
        return this;
    }

    public ClothSeasonEnum getSeason() {
        return season;
    }

    public ClothCreateDTO setSeason(ClothSeasonEnum season) {
        this.season = season;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public ClothCreateDTO setComposition(String composition) {
        this.composition = composition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothCreateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public ClothCreateDTO setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public ClothCreateDTO setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
        return this;
    }
}
