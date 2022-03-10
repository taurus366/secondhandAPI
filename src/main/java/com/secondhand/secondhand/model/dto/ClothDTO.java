package com.secondhand.secondhand.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ClothDTO {

    private Long id;

    private String type;

    private String brand;

    private String size;

    private String sex;

    private String color;

    private String season;

    private String composition;

    private String description;

    private Long startPrice;

    private Long newPrice;

    private Integer likes;

    private List<PictureDTO> sidePictures = new ArrayList<>();

    private PictureDTO coverPicture;

    private PictureDTO frontPicture;

    public ClothDTO() {
    }

    public Long getId() {
        return id;
    }

    public ClothDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public ClothDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ClothDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSize() {
        return size;
    }

    public ClothDTO setSize(String size) {
        this.size = size;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public ClothDTO setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ClothDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getSeason() {
        return season;
    }

    public ClothDTO setSeason(String season) {
        this.season = season;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public ClothDTO setComposition(String composition) {
        this.composition = composition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public ClothDTO setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public ClothDTO setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public ClothDTO setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public List<PictureDTO> getSidePictures() {
        return sidePictures;
    }

    public ClothDTO setSidePictures(List<PictureDTO> sidePictures) {
        this.sidePictures = sidePictures;
        return this;
    }

    public PictureDTO getCoverPicture() {
        return coverPicture;
    }

    public ClothDTO setCoverPicture(PictureDTO coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public PictureDTO getFrontPicture() {
        return frontPicture;
    }

    public ClothDTO setFrontPicture(PictureDTO frontPicture) {
        this.frontPicture = frontPicture;
        return this;
    }
}
