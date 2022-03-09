package com.secondhand.secondhand.model.binding;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.validator.ClothBrandExists;
import com.secondhand.secondhand.model.validator.ClothCompositionExists;
import com.secondhand.secondhand.model.validator.ClothTypeExists;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ClothCreateBindingModel {

//    @Size(min = 4,max = 4,message = "Please upload 4 pictures like [front,back,side,side]")
    @NotNull(message = "Please choose cover picture")
    private MultipartFile coverPicture;

    @NotNull(message = "Please choose front picture")
    private MultipartFile frontPicture;

    @NotNull(message = "Please choose side picture")
    private MultipartFile thirdPicture;

    @NotNull(message = "Please choose side picture")
    private MultipartFile fourthPicture;

    @NotNull(message = "Please right sex of the item")
    private ClothSexEnum sex;

    @NotNull(message = "Please choose brand of the item")
    @ClothBrandExists
    private String brand;

    @NotNull(message = "Please choose color of the item")
    private ClothColorEnum color;

    @NotNull(message = "Please choose type of the item")
    @ClothTypeExists
    private String type;

    @NotNull(message = "Please choose size of the item")
    private ClothSizeEnum size;

    @NotNull(message = "Please choose season of the item")
    private ClothSeasonEnum season;

    @NotNull(message = "Please choose composition of the item")
    @ClothCompositionExists
    private String composition;

    @NotBlank(message = "Please write description of the item")
//    @Length(min = 5,message = "Please write description of the item")
    private String description;


    @NotNull(message = "Please write start price of the item")
    @Positive(message = "Please write positive start price of the item")
    private Long startPrice;

    @NotNull(message = "Please write new price of the item")
    @Positive(message = "Please write positive new price of the item")
    private Long newPrice;

    public ClothCreateBindingModel() {
    }

    public MultipartFile getCoverPicture() {
        return coverPicture;
    }

    public ClothCreateBindingModel setCoverPicture(MultipartFile coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public MultipartFile getFrontPicture() {
        return frontPicture;
    }

    public ClothCreateBindingModel setFrontPicture(MultipartFile frontPicture) {
        this.frontPicture = frontPicture;
        return this;
    }

    public MultipartFile getThirdPicture() {
        return thirdPicture;
    }

    public ClothCreateBindingModel setThirdPicture(MultipartFile thirdPicture) {
        this.thirdPicture = thirdPicture;
        return this;
    }

    public MultipartFile getFourthPicture() {
        return fourthPicture;
    }

    public ClothCreateBindingModel setFourthPicture(MultipartFile fourthPicture) {
        this.fourthPicture = fourthPicture;
        return this;
    }

    public ClothSexEnum getSex() {
        return sex;
    }

    public ClothCreateBindingModel setSex(ClothSexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothCreateBindingModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ClothColorEnum getColor() {
        return color;
    }

    public ClothCreateBindingModel setColor(ClothColorEnum color) {
        this.color = color;
        return this;
    }

    public String getType() {
        return type;
    }

    public ClothCreateBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    public ClothSizeEnum getSize() {
        return size;
    }

    public ClothCreateBindingModel setSize(ClothSizeEnum size) {
        this.size = size;
        return this;
    }

    public ClothSeasonEnum getSeason() {
        return season;
    }

    public ClothCreateBindingModel setSeason(ClothSeasonEnum season) {
        this.season = season;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public ClothCreateBindingModel setComposition(String composition) {
        this.composition = composition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothCreateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public ClothCreateBindingModel setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public ClothCreateBindingModel setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
        return this;
    }
}
