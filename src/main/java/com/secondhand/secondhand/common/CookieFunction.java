package com.secondhand.secondhand.common;

import com.secondhand.secondhand.service.GuestTokenService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieFunction {

    private final GuestTokenService guestTokenService;

    public CookieFunction(GuestTokenService guestTokenService) {
        this.guestTokenService = guestTokenService;
    }

    public Cookie CheckThenIfNecessaryAddNewCookie(HttpServletRequest request, HttpServletResponse response){
        String guestSessionId = "";
        Cookie newCookie = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("GSESSIONID") && cookie.getValue().length() > 0) {
                    guestSessionId = cookie.getValue();
                    break;
                }
            }
        }

        boolean isInDb = guestTokenService.checkIfCookieExists(guestSessionId);
        if (!isInDb) {

            String newGuest = this.guestTokenService.createNewGuest();

            newCookie = new Cookie("GSESSIONID", newGuest);
            newCookie.setPath("/");
            newCookie.setHttpOnly(true);
            newCookie.setMaxAge(43000);
            response.addCookie(newCookie);
            return newCookie;

        }

      return new Cookie("GSESSIONID",guestSessionId);
    }
}
