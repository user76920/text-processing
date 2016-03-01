package com.epam.msfrolov.textprocessing.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TextReader {
    public static Random random = new Random();

    public static String read(String path) {
        InputStream inputStream = TextReader.class.getClassLoader().getResourceAsStream(path);
        String text = new Scanner(inputStream).useDelimiter("\\A").next();
        return text;
    }

    private static void readFileToList() {
        List<String> lines = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getRandomLine(String fn) throws IllegalArgumentException {
        if (fn == null) throw new IllegalArgumentException();
        fileName = fn;
        readFileToList();
        return lines.get(ServiceRandom.random.nextInt(lines.size()));
    }
}
