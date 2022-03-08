package com.secondhand.secondhand.model.entity;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "clothes")
public class ClothEntity extends BaseEntity{


    @ManyToOne
    private ClothTypeEntity clothType;

    @ManyToOne
    private ClothBrand clothBrand;

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
    @NotEmpty
    private Long startPrice;

    @Column(nullable = false)
    @Positive
    @NotEmpty
    private Long newPrice;

    @Column(nullable = false)
    @PositiveOrZero
    @NotEmpty
    private Integer likes;

    public ClothEntity() {
    }

    public ClothTypeEntity getClothType() {
        return clothType;
    }

    public ClothEntity setClothType(ClothTypeEntity clothType) {
        this.clothType = clothType;
        return this;
    }

    public ClothBrand getClothBrand() {
        return clothBrand;
    }

    public ClothEntity setClothBrand(ClothBrand clothBrand) {
        this.clothBrand = clothBrand;
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
}
