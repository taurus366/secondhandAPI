package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.GuestTokenEntity;
import com.secondhand.secondhand.repository.GuestTokenRepository;
import com.secondhand.secondhand.service.GuestTokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.UUID;

@Service
public class GuestTokenServiceImpl implements GuestTokenService {

    private final GuestTokenRepository guestTokenRepository;

    public GuestTokenServiceImpl(GuestTokenRepository guestTokenRepository) {
        this.guestTokenRepository = guestTokenRepository;
    }

    @Override
    public GuestTokenEntity findByCookie(String cookie) {
        return null;
    }

    @Override
    public String createNewGuest(String ...args) {

        String cookie = "";

       if (args.length == 0){
           cookie = UUID.randomUUID().toString();
       }else {
           cookie = args[0];
       }


            GuestTokenEntity newGuest = new GuestTokenEntity();
            newGuest
                    .setToken(cookie)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

        return  this.guestTokenRepository
                .save(newGuest).getToken();
    }

    @Override
    public boolean checkIfCookieExists(String cookie) {

        return this.guestTokenRepository.findByToken(cookie) != null;
    }
}
