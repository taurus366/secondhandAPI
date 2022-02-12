package com.secondhand.secondhand.model.dto;

import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.enums.SexEnum;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleEntity> roles = new ArrayList<>();
    private SexEnum sex;

    public Long getId() {
        return id;
    }

    public UserRegistrationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserRegistrationDTO setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public UserRegistrationDTO setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }
}
