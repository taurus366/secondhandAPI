package com.secondhand.secondhand.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserChangePasswordBindingModel {

    @NotNull
    @NotBlank
    private String currentPassword;

    @NotNull
    @NotBlank
    @Size(min = 6,max = 15)
    private String newPassword;

    @NotNull
    @NotBlank
    @Size(min = 6,max = 15)
    private String confirmPassword;

    public UserChangePasswordBindingModel() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public UserChangePasswordBindingModel setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordBindingModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserChangePasswordBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
