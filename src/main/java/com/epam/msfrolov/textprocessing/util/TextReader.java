package com.epam.msfrolov.textprocessing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TextReader {
    private static Logger LOG = LoggerFactory.getLogger(TextReader.class.getName());
    public static Random random = new Random();

    public static String read(String path) {
        InputStream inputStream = TextReader.class.getClassLoader().getResourceAsStream(path);
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }

    public static String getRandomLine(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            LOG.error("Unable to read a text file", e);
        }
        return lines.get(random.nextInt(lines.size()));
    }

    public static String getRandomLine() {
        return getRandomLine("text");
    }

}
