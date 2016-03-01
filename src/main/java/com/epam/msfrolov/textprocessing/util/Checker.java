package com.epam.msfrolov.textprocessing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checker {
    private static Logger LOG = LoggerFactory.getLogger(Checker.class.getName());

    public static boolean isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return false;
    }


}
