package com.epam.msfrolov.textprocessing.util;

public class Checker {
    public static boolean isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return false;
    }


}
