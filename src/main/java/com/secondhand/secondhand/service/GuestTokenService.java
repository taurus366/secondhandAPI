package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.entity.GuestTokenEntity;

public interface GuestTokenService {

    GuestTokenEntity findByCookie(String cookie);
    String createNewGuest(String ...args);
    boolean checkIfCookieExists(String cookie);
}
