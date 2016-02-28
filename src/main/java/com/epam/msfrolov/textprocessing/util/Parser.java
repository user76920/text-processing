package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.PUNCTUATION;
import static com.epam.msfrolov.textprocessing.model.Char.CharType.SYMBOL;
import static com.epam.msfrolov.textprocessing.model.Char.CharType.WHITESPACE;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class.getName());

    private static Map<Type, Type> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put(TEXT, PARAGRAPH);
        typeMap.put(PARAGRAPH, SENTENCE);
        typeMap.put(SENTENCE, WORD);
    }

    private static Map<Type, String> regExMap;

    static {
        String REGEX_TEXT = "(?<=\\n)";
        String REGEX_PARAGRAPH = "(?<=[.!?])";
        String REGEX_SENTENCES = "(?<=[\\s.!?,:\"';()\\[\\]\\{\\}]+)";
        String REGEX_WORD = "([\\s.!?,:\"';()\\[\\]\\{\\}])";
        String REGEX_PUNCTUATION = "([.!?,:\"';()\\[\\]\\{\\}])";
        String REGEX_WHITESPACE = "(\\s)";
        String REGEX_SYMBOL = "([a-zA-Zа-яА-ЯЁё0-9])";

        LOG.info(REGEX_TEXT);

        regExMap = new HashMap<>();
        regExMap.put(TEXT, REGEX_TEXT);
        regExMap.put(PARAGRAPH, REGEX_PARAGRAPH);
        regExMap.put(SENTENCE, REGEX_SENTENCES);
        regExMap.put(WORD, REGEX_WORD);
        regExMap.put(PUNCTUATION, REGEX_PUNCTUATION);
        regExMap.put(WHITESPACE, REGEX_WHITESPACE);
        regExMap.put(SYMBOL, REGEX_SYMBOL);
    }


    private static Composite parseComposite(String string, Type type) {
        Composite composite = Composite.create(type);
        String[] strings = string.split(getRegEx(type));
        Type typeForComponent = getNextType(type);
        for (String s : strings) {
            if (typeForComponent != null) {
                Composite parseComposite = parseComposite(s, typeForComponent);
                composite.add(parseComposite);
            } else {
                Composite parseComposite = parseComponent(s);
               // Handler.isNull(parseComposite);  //?
                composite.add(parseComposite);
            }
        }
        return composite;
    }

    private static Composite parseComponent(String s) {
        if (s.matches(getRegEx(Type.WORD))) {
            return parseChar(s, Type.WORD);
        }
        if (s.matches(getRegEx(Type.NON_WORD))) {
            return parseChar(s, Type.NON_WORD);
        }
        return null;
    }

    private static Composite parseChar(String s, Type type) {
        char[] chars = s.toCharArray();
        Composite composite = Composite.create(type);
        Type charType = getNextType(type);
        for (char ch:chars){
            Char newChar = Char.create(ch);
            composite.add(newChar);
        }
        return composite;
    }


    public static Type getTypeHeir(Type type) {
        Handler.isNull(type);
        return typeMap.get(type);
    }

    public static String getRegEx(Type type) {
        Handler.isNull(type);
        return regExMap.get(type);
    }

    public static Composite parse(String string) {
        Handler.isNull(string);
        return parseComposite(string, Type.TEXT);
    }

}
