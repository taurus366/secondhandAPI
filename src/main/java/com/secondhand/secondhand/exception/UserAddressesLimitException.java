package com.secondhand.secondhand.exception;

import static com.secondhand.secondhand.exception.ItemAlreadyExistsException.setReason;

public class UserAddressesLimitException extends RuntimeException{

    private static String reason;

    public UserAddressesLimitException(String reason) {
        super(setReason(reason));
    }

    public static String setReason(String reason2) {
        if (reason2.length() == 0){
            reason = "User can't have more addresses than 3";
        }else {
            reason = reason2;
        }
        return reason;
    }
}
