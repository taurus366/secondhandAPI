package com.secondhand.secondhand.web;

import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    CHECK IF USER IS LOGGED OR NOT!
    @GetMapping("/validate")
    public ResponseEntity<UserInformationDTO> checkUserIfLogged(@AuthenticationPrincipal SecondHandUser principal){
        UserInformationDTO userByEmail = null;
        try {
            userByEmail = userService.findUserByEmail(principal.getUserIdentifierEmail());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userByEmail);
    }

    @GetMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response){

        System.out.println(request.getCookies()[0].getValue());

        Cookie cookie = new Cookie("test2","123456");
        cookie.setHttpOnly(true);

        Cookie cookie2 = new Cookie("test23","1234563");
        cookie2.setHttpOnly(true);

        response.addCookie(cookie);
        response.addCookie(cookie2);
    }
}
