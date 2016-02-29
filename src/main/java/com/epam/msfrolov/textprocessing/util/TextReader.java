package com.epam.msfrolov.textprocessing.util;

import java.io.InputStream;
import java.util.Scanner;

public class TextReader {
    public static String read(String path) {
        InputStream inputStream = TextReader.class.getClassLoader().getResourceAsStream(path);
        String text = new Scanner(inputStream).useDelimiter("\\A").next();
        return text;
    }
}
