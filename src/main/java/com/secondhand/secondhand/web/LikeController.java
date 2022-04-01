package com.secondhand.secondhand.web;

import com.secondhand.secondhand.common.CookieFunction;
import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import com.secondhand.secondhand.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final CookieFunction cookieFunction;

    public LikeController(LikeService likeService, CookieFunction cookieFunction) {
        this.likeService = likeService;
        this.cookieFunction = cookieFunction;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getClientLikes(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response) {

        List<ClothDTO> clientLikeList = null;

        try {

            String userIdentifierEmail = secondHandUser.getUserIdentifierEmail();

            clientLikeList = likeService
                    .getUserLikes(userIdentifierEmail);

        } catch (NullPointerException ex) {

            String cookieValue = cookieFunction.CheckThenIfNecessaryAddNewCookie(request, response).getValue();

            clientLikeList = this.likeService
                    .getGuestLikes(cookieValue);

        }


        return ResponseEntity.ok(clientLikeList);
    }


    @PostMapping(value = "like")
    public ResponseEntity<Object> likeCloth(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response, @RequestBody Long itemId) {

        ClothDTO addedCloth = null;
        try {

            try {

                String userIdentifierEmail = secondHandUser.getUserIdentifierEmail();

                addedCloth = this.likeService
                        .giveUserLikeToCloth(userIdentifierEmail, itemId);

            } catch (NullPointerException ex) {

                String cookieValue = this.cookieFunction
                        .CheckThenIfNecessaryAddNewCookie(request, response).getValue();

                addedCloth = this.likeService
                        .giveGuestLikeToCloth(cookieValue, itemId);

            }
        } catch (ItemAlreadyExistsException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());

        }


        return ResponseEntity.ok(addedCloth);
    }

    @DeleteMapping(value = "unlike/{id}")
    public ResponseEntity<Object> unlikeCloth(@AuthenticationPrincipal SecondHandUser secondHandUser, HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {

        ClothDTO unlikedCloth = null;

        if (id != null && id > 0) {
            try {

                try {
                    String userIdentifierEmail = secondHandUser.getUserIdentifierEmail();

                  unlikedCloth =  this.likeService
                            .deleteUserLikeFromCloth(userIdentifierEmail, id);

                    return ResponseEntity.ok().body(unlikedCloth);

                } catch (NullPointerException ex) {

                    String cookieValue = this.cookieFunction
                            .CheckThenIfNecessaryAddNewCookie(request, response).getValue();

                    unlikedCloth = this.likeService
                            .deleteGuestLikeFromCloth(cookieValue, id);

                    return ResponseEntity.ok().body(unlikedCloth);

                }

            } catch (UsernameNotFoundException e) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We couldn't find the user !");

            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
