package com.secondhand.secondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

//    EXAMPLE !!!

//    @ExceptionHandler({ItemAlreadyExistsException.class})
//    public ModelAndView handleException(ItemAlreadyExistsException e) {
//        ModelAndView modelAndView = new ModelAndView("offer-not-found");
//        modelAndView
//                .addObject("message", e.getMessage())
//                .addObject("offerId", e.getOfferId());
//        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//
//        return modelAndView;
//    }
}
