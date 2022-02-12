package com.secondhand.secondhand.model.binding;

import com.secondhand.secondhand.model.entity.enums.SexEnum;
import com.secondhand.secondhand.model.validator.UniqueEmail;

import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class UserRegistrationBindingModel {

    @NotNull
    @NotBlank
    @Size(min = 3,max = 12)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3,max = 12)
    private String lastName;

    @Email
    @NotBlank
    @UniqueEmail
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6,max = 15)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 6,max = 15)
    private String confirmPassword;


    @NotNull(message = "Please choose SEX")
    private SexEnum sex;

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public UserRegistrationBindingModel setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }
}
