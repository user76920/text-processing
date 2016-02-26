package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.Type;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static Map<Type, Type> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put(Type.TEXT, Type.PARAGRAPH);
        typeMap.put(Type.PARAGRAPH, Type.SENTENCES);
        typeMap.put(Type.WORD, Type.LETTER_OR_NUMBER);
        typeMap.put(Type.NON_WORD, Type.OTHER_SYMBOL);
    }

    private static Map<Type, String> regExMap;

    static {
        String REGEX_TEXT = "(?<=\\n)";
        String REGEX_PARAGRAPH = "(?<=[.!?])";
        String REGEX_SENTENCES = "((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s\t\n ,.!?~#$%^&*()=+'\":;№@`]+))"
                + "((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[\\s\t\n ,.!?~#$%^&*()=+'\":;№@`]+))";
        String REGEX_SENTENCES_WORD = "([a-zA-Zа-яА-ЯЁё0-9-_]+)";
        String REGEX_SENTENCES_OTHER = "([\\s\t\n,.!?~#$%^&*()=+'\":;№@`]+)";

        regExMap = new HashMap<>();
        regExMap.put(Type.TEXT, REGEX_TEXT);
        regExMap.put(Type.PARAGRAPH, REGEX_PARAGRAPH);
        regExMap.put(Type.SENTENCES, REGEX_SENTENCES);
        regExMap.put(Type.WORD, REGEX_SENTENCES_WORD);
        regExMap.put(Type.NON_WORD, REGEX_SENTENCES_OTHER);
    }


    private static Composite parseComposite(String string, Type type) {
        Composite composite = Composite.create(type);
        String[] strings = string.split(getRegEx(type));
        Type typeForComponent = getNextType(type);
        for (String s : strings) {
            if (typeForComponent != null) {
                System.out.println("002 " + s);
                Composite parseComposite = parseComposite(s, typeForComponent);
                composite.add(parseComposite);
            } else {
                System.out.println("003 " + s);
                Composite parseComposite = parseComponent(s);
               // Handler.isNull(parseComposite);  //?
                composite.add(parseComposite);
            }
        }
        return composite;
    }

    private static Composite parseComponent(String s) {
        if (s.matches(getRegEx(Type.WORD))) {
            System.out.println("004 " + s);
            return parseChar(s, Type.WORD);
        }
        if (s.matches(getRegEx(Type.NON_WORD))) {
            System.out.println("005 " + s);
            return parseChar(s, Type.NON_WORD);
        }
        return null;
    }

    private static Composite parseChar(String s, Type type) {
        char[] chars = s.toCharArray();
        Composite composite = Composite.create(type);
        Type charType = getNextType(type);
        for (char ch:chars){
            System.out.println("007 " + s);
            Char newChar = Char.create(ch);
            composite.add(newChar);
        }
        return composite;
    }


    public static Type getNextType(Type type) {
        Handler.isNull(type);
        return typeMap.get(type);
    }

    public static String getRegEx(Type type) {
        Handler.isNull(type);
        return regExMap.get(type);
    }

    public static Composite parse(String string) {
        System.out.println("001 " + string);
        Handler.isNull(string);
        return parseComposite(string, Type.TEXT);
    }

}
