package com.secondhand.secondhand.model.service;

public class UserChangePasswordServiceModel {

    private String newPassword;
    private String userEmail;


    public UserChangePasswordServiceModel() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordServiceModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserChangePasswordServiceModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
