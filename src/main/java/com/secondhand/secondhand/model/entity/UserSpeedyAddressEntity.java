package com.secondhand.secondhand.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_speedy_address")
public class UserSpeedyAddressEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne
    private SpeedyAddressEntity address;

    @OneToOne
    private SpeedyCityEntity city;

    public UserSpeedyAddressEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserSpeedyAddressEntity setFirstName(String name) {
        this.firstName = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserSpeedyAddressEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserSpeedyAddressEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SpeedyAddressEntity getAddress() {
        return address;
    }

    public UserSpeedyAddressEntity setAddress(SpeedyAddressEntity address) {
        this.address = address;
        return this;
    }

    public SpeedyCityEntity getCity() {
        return city;
    }

    public UserSpeedyAddressEntity setCity(SpeedyCityEntity city) {
        this.city = city;
        return this;
    }
}
