package com.secondhand.secondhand.model.entity;

import com.secondhand.secondhand.model.entity.enums.UserSexEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(
        name="user-roles",
        attributeNodes = {
                @NamedAttributeNode("roles")
        }
)
@NamedEntityGraph(
        name = "user-likes",
        attributeNodes = {
                @NamedAttributeNode("likesList"),
                @NamedAttributeNode(value = "likesList", subgraph = "likes"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "likes",
                        attributeNodes = {
                                @NamedAttributeNode("coverPicture"),
                                @NamedAttributeNode("clothComposition"),
                                @NamedAttributeNode("frontPicture"),
                                @NamedAttributeNode("clothType"),
                                @NamedAttributeNode("clothBrandEntity")
                        }
                )
        }
)
@NamedEntityGraph(
        name = "user-addresses",
        attributeNodes = {
                @NamedAttributeNode("addresses"),
//                @NamedAttributeNode(value = "addresses", subgraph = "address"),
        }
//        ,
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "address",
//                        attributeNodes = {
//                                @NamedAttributeNode("")
//                        }
//                )
//        }
)

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserSexEnum sex;

    private boolean isActive = true;

    @Column(nullable = true)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AddressEntity> addresses = new ArrayList<>();

    @ManyToMany()
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClothEntity> clothesList = new ArrayList<>();

    @ManyToMany()
    private List<ClothEntity> likesList = new ArrayList<>();

    public UserEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserSexEnum getSex() {
        return sex;
    }

    public UserEntity setSex(UserSexEnum sex) {
        this.sex = sex;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public UserEntity setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<ClothEntity> getClothesList() {
        return clothesList;
    }

    public UserEntity setClothesList(List<ClothEntity> clothesList) {
        this.clothesList = clothesList;
        return this;
    }

    public List<ClothEntity> getLikesList() {
        return likesList;
    }

    public UserEntity setLikesList(List<ClothEntity> likesList) {
        this.likesList = likesList;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
