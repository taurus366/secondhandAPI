package com.secondhand.secondhand.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "guest-likes",
        attributeNodes = {
                @NamedAttributeNode("likeList")
        }
)

@Entity
@Table(name = "guest_token")
public class GuestTokenEntity extends BaseEntity{

    @Column(nullable = false)
    private String token;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClothEntity> clothesList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ClothEntity> likeList = new ArrayList<>();

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

    public List<ClothEntity> getLikeList() {
        return likeList;
    }

    public GuestTokenEntity setLikeList(List<ClothEntity> likeList) {
        this.likeList = likeList;
        return this;
    }
}
