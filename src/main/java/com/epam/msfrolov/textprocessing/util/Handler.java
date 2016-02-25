package com.epam.msfrolov.textprocessing.util;

public class Handler {
    public static boolean isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
            //return false;
        }
        return true;
    }
}
