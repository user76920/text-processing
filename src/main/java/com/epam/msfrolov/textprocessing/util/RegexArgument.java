package com.epam.msfrolov.textprocessing.util;

import java.io.IOException;
import java.util.Properties;

public class RegexArgument {
    String REGEX_TEXT = getRegexTypeFromProperty("text");
    String REGEX_PARAGRAPH = getRegexTypeFromProperty("paragraph");
    String REGEX_SENTENCES = getRegexTypeFromProperty("sentences");
    String REGEX_WORD = getRegexTypeFromProperty("word");
    String REGEX_PUNCTUATION = getRegexTypeFromProperty("punctuation");


    regExMap = new HashMap<>();
    regExMap.put(TEXT, REGEX_TEXT);
    regExMap.put(PARAGRAPH, REGEX_PARAGRAPH);
    regExMap.put(SENTENCE, REGEX_SENTENCES);
    regExMap.put(WORD, REGEX_WORD);
    regExMap.put(PUNCTUATION, REGEX_PUNCTUATION);

    private static String getRegexTypeFromProperty(String propertyKey) {
        Properties regexProperties = new Properties();
        try {
            regexProperties.load(Parser.class.getClassLoader().getResourceAsStream("regExType.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String propertyValue = regexProperties.getProperty(propertyKey);
        return propertyValue;
    }

}
