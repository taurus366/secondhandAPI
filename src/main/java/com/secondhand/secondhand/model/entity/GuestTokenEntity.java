package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest_token")
public class GuestTokenEntity extends BaseEntity{

    @Column(nullable = false)
    private String token;

    @ManyToMany
    private List<ClothEntity> clothesList = new ArrayList<>();

    public GuestTokenEntity() {
    }

    public String getToken() {
        return token;
    }

    public GuestTokenEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public List<ClothEntity> getClothesList() {
        return clothesList;
    }

    public GuestTokenEntity setClothesList(List<ClothEntity> clothesList) {
        this.clothesList = clothesList;
        return this;
    }
}
