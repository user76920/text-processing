package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Type;

import java.util.HashMap;
import java.util.Map;

public class Handler {
    public static boolean isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
            //return false;
        }
        return false;
    }


}
