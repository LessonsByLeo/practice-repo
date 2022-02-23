package com.lessonsbyleo.practicerepo.exception;

public class ZipCodeUnavailableException extends Exception {
    public ZipCodeUnavailableException(String zipcode) {
        super(getZipCodeMessage(zipcode));
    }

    public static String getZipCodeMessage(String zipcode){
        return "Zipcode " + zipcode + " is unavailable";
    }
}
