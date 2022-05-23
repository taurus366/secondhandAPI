package com.secondhand.secondhand.model.binding;
import com.secondhand.secondhand.model.validator.PhoneNumberExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SpeedyNewAddressBindingModel {


    @NotBlank(message = "name should not be null")
    @Size(min = 3,message = "name should be min 3 word")
    private String firstName;


    @NotBlank(message = "last name should not be null")
    @Size(min = 3,message = "lastname should be min 3 word")
    private String lastName;


    @NotBlank(message = "phone number should not be null")
    @Size(min = 9,message = "phone number should be min 10 numbers")
    @PhoneNumberExists
    private String phoneNumber;

    @NotNull(message = "please select city")
    private Long cityId;

    @NotNull(message = "please select office address")
    private Long speedyOfficeId;

    public SpeedyNewAddressBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public SpeedyNewAddressBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SpeedyNewAddressBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SpeedyNewAddressBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public SpeedyNewAddressBindingModel setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getSpeedyOfficeId() {
        return speedyOfficeId;
    }

    public SpeedyNewAddressBindingModel setSpeedyOfficeId(Long speedyOfficeId) {
        this.speedyOfficeId = speedyOfficeId;
        return this;
    }
}
