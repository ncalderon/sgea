package com.ibs.academic.models.vo;

/**
 * Created by nathaniel on 3/19/14.
 */
public enum UserGender {
    MALE("M"), FEMALE("F"), OTHERS("O");

    private String genderCode;
    private UserGender(String s) {
        genderCode = s;
    }

    public String getGenderCode() {
        return genderCode;
    }

}


