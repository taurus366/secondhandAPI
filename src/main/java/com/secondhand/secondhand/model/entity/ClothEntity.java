package com.secondhand.secondhand.model.entity;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clothes")
public class ClothEntity extends BaseEntity{


    @ManyToOne
    private ClothTypeEntity clothType;

    @ManyToOne
    private ClothBrandEntity clothBrandEntity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSizeEnum clothSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSexEnum clothSex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothColorEnum clothColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSeasonEnum clothSeason;

    @ManyToOne
    private ClothCompositionEntity clothComposition;

    @Column(nullable = false)
    @Size(max = 50)
    @NotBlank
    private String description;

    @Column(nullable = false)
    @Positive
    private Long startPrice;

    @Column(nullable = false)
    @Positive
    private Long newPrice;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer likes;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PictureEntity> sidePictures = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private PictureEntity coverPicture;

    @OneToOne(cascade = CascadeType.ALL)
    private PictureEntity frontPicture;

    @Column(nullable = false)
    @Positive
    private int quantity;

    public ClothEntity() {
    }

    public ClothTypeEntity getClothType() {
        return clothType;
    }

    public ClothEntity setClothType(ClothTypeEntity clothType) {
        this.clothType = clothType;
        return this;
    }

    public ClothBrandEntity getClothBrand() {
        return clothBrandEntity;
    }

    public ClothEntity setClothBrand(ClothBrandEntity clothBrandEntity) {
        this.clothBrandEntity = clothBrandEntity;
        return this;
    }

    public ClothSizeEnum getClothSize() {
        return clothSize;
    }

    public ClothEntity setClothSize(ClothSizeEnum clothSize) {
        this.clothSize = clothSize;
        return this;
    }

    public ClothSexEnum getClothSex() {
        return clothSex;
    }

    public ClothEntity setClothSex(ClothSexEnum clothSex) {
        this.clothSex = clothSex;
        return this;
    }

    public ClothColorEnum getClothColor() {
        return clothColor;
    }

    public ClothEntity setClothColor(ClothColorEnum clothColor) {
        this.clothColor = clothColor;
        return this;
    }

    public ClothSeasonEnum getClothSeason() {
        return clothSeason;
    }

    public ClothEntity setClothSeason(ClothSeasonEnum clothSeason) {
        this.clothSeason = clothSeason;
        return this;
    }

    public ClothCompositionEntity getClothComposition() {
        return clothComposition;
    }

    public ClothEntity setClothComposition(ClothCompositionEntity clothComposition) {
        this.clothComposition = clothComposition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public ClothEntity setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public ClothEntity setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public ClothEntity setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public ClothBrandEntity getClothBrandEntity() {
        return clothBrandEntity;
    }

    public ClothEntity setClothBrandEntity(ClothBrandEntity clothBrandEntity) {
        this.clothBrandEntity = clothBrandEntity;
        return this;
    }

    public List<PictureEntity> getSidePictures() {
        return sidePictures;
    }

    public ClothEntity setSidePictures(List<PictureEntity> sidePictures) {
        this.sidePictures = sidePictures;
        return this;
    }

    public PictureEntity getCoverPicture() {
        return coverPicture;
    }

    public ClothEntity setCoverPicture(PictureEntity coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public PictureEntity getFrontPicture() {
        return frontPicture;
    }

    public ClothEntity setFrontPicture(PictureEntity frontPicture) {
        this.frontPicture = frontPicture;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ClothEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
