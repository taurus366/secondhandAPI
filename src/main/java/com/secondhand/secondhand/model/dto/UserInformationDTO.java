package com.secondhand.secondhand.model.dto;

import com.secondhand.secondhand.model.entity.enums.UserSexEnum;

import java.util.ArrayList;
import java.util.List;

public class UserInformationDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleDTO> roles = new ArrayList<>();
    private UserSexEnum sex;
    private String phoneNumber;
    private List<AddressDTO> addresses = new ArrayList<>();
    private List<SpeedyAddressDTO> SpeedyAddressList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public UserInformationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public UserInformationDTO setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserInformationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserInformationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInformationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public UserInformationDTO setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public UserSexEnum getSex() {
        return sex;
    }

    public UserInformationDTO setSex(UserSexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserInformationDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<SpeedyAddressDTO> getSpeedyAddressList() {
        return SpeedyAddressList;
    }

    public UserInformationDTO setSpeedyAddressList(List<SpeedyAddressDTO> speedyAddressList) {
        this.SpeedyAddressList = speedyAddressList;
        return this;
    }
}
