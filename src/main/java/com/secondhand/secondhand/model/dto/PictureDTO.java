package com.secondhand.secondhand.model.dto;

public class PictureDTO {

    private String url;

    private String publicId;

    public PictureDTO() {
    }

    public String getUrl() {
        return url;
    }

    public PictureDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureDTO setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
