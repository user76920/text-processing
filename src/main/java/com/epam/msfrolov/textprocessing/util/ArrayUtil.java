package com.epam.msfrolov.textprocessing.util;

public class ArrayUtil {
    public static char[] concat(char[] c1, char[] c2) {
        int c1Length = c1.length;
        int c2Length = c2.length;
        char[] c3 = new char[c1.length + c2.length];
        System.arraycopy(c1, 0, c3, 0, c1Length);
        System.arraycopy(c2, 0, c3, c1Length, c2Length);
        return c3;
    }
}
