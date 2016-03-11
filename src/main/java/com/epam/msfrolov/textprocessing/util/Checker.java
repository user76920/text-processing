package com.epam.msfrolov.textprocessing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checker {
    private static Logger log = LoggerFactory.getLogger(Checker.class.getName());

    public static boolean isNull(Object o) {
        //log.debug("Object: {}", o);
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return false;
    }


}
