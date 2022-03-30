package com.secondhand.secondhand.web;

import com.secondhand.secondhand.common.CookieFunction;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.service.GuestTokenService;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GuestTokenService guestTokenService;
    private final CookieFunction cookieFunction;

    public UserController(UserService userService, GuestTokenService guestTokenService, com.secondhand.secondhand.common.CookieFunction cookieFunction) {
        this.userService = userService;
        this.guestTokenService = guestTokenService;
        this.cookieFunction = cookieFunction;
    }

    //    CHECK IF USER IS LOGGED OR NOT!
    @GetMapping("/validate")
    public ResponseEntity<UserInformationDTO> checkUserIfLogged(@AuthenticationPrincipal SecondHandUser principal, HttpServletRequest request, HttpServletResponse response) {

        try {
            userService.findUserByEmail(principal.getUserIdentifierEmail());
        } catch (Exception e) {


//            HERE I CHECK THE GSESSIONID THEN SEND TO CLIENT
//            OR IF THE CLIENTS HAS ALREADY IT
//            THEN I ONLY CHECK IF ITS EXISTS IN DB
//            this.cookieFunction.CheckThenIfNecessaryAddNewCookie(request, response);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("isAdmin()")
    @GetMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getCookies()[0].getValue());

        Cookie cookie = new Cookie("test2", "123456");
        cookie.setHttpOnly(true);

        Cookie cookie2 = new Cookie("test23", "1234563");
        cookie2.setHttpOnly(true);

        response.addCookie(cookie);
        response.addCookie(cookie2);
    }
}
