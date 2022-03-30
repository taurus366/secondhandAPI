package com.secondhand.secondhand.exception;

public class ItemAlreadyExistsException extends RuntimeException{

    private  static String reason;

    public ItemAlreadyExistsException(String reason) {
       super(setReason(reason));
    }

    public String getReason() {
        return reason;
    }

    public static String setReason(String reason2) {
        if (reason2.length() == 0){
        reason = "Item already Exists!";

        }else {
            reason = reason2;
        }
        return reason;
    }
}
