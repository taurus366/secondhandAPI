package com.secondhand.secondhand.web;

import com.secondhand.secondhand.common.CookieFunction;
import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.service.CartService;
import com.secondhand.secondhand.service.ClothService;
import com.secondhand.secondhand.service.GuestTokenService;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {

    private final CookieFunction cookieFunction;
    private final CartService cartService;

    public CartController(CookieFunction cookieFunction, CartService cartService) {

        this.cookieFunction = cookieFunction;
        this.cartService = cartService;
    }


    @GetMapping("/get")
    public ResponseEntity<Object> getAllItemFromCart(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response) {

        List<ClothDTO> clientCart = null;


        try {
            String userEmail = secondHandUser.getUserIdentifierEmail();

            clientCart = this.cartService
                    .getUserCart(userEmail)
                    .stream()
                    .toList();

        } catch (NullPointerException ex) {

            String cookieValue = cookieFunction.CheckThenIfNecessaryAddNewCookie(request, response).getValue();

            clientCart = this.cartService
                    .getGuestCart(cookieValue);
        }
        return ResponseEntity.ok().body(clientCart);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> postItemToCart(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response, @RequestBody Long id) {

        ClothDTO addedCloth = null;


        if (id != null && id > 0) {
            try {
                try {
                    String userEmail = secondHandUser.getUserIdentifierEmail();

                    addedCloth = this.cartService
                            .addItemToUserCart(userEmail, id);

                } catch (NullPointerException ex) {


                    Cookie cookie = this.cookieFunction
                            .CheckThenIfNecessaryAddNewCookie(request, response);
                    addedCloth = this.cartService
                            .addItemToGuestCart(cookie.getValue(), id);

                }
            } catch (ItemAlreadyExistsException e) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());

            }
        }

        return ResponseEntity.ok(addedCloth);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteItemFromCart(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {

        if (id != null && id > 0) {

            try {

                try {
                    String userEmail = secondHandUser
                            .getUserIdentifierEmail();

                    this.cartService
                            .removeItemFromUserCart(userEmail, id);

                    return ResponseEntity.ok().build();

                } catch (NullPointerException ex) {

                    String cookieValue = cookieFunction
                            .CheckThenIfNecessaryAddNewCookie(request, response).getValue();

                    this.cartService
                            .removeItemFromGuestCart(cookieValue, id);

                    return ResponseEntity.ok().build();

                }
            } catch (UsernameNotFoundException ex) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We couldn't find the user !");

            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
