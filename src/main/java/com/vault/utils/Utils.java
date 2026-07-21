package com.vault.utils;

public class Utils {

    public static boolean isNullOrEmptyString(String str){
        return str == null || str.isBlank() || str.isEmpty();
    }
}
