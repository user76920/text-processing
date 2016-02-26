package com.epam.msfrolov.textprocessing.util;

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
    }

    private static Map<Type, String> regExMap;

    static {
        regExMap = new HashMap<>();
        regExMap.put(Type.TEXT, "(?<=\\n)");
        regExMap.put(Type.PARAGRAPH, "(?<=[.!?])");
        String REGEX_SENTENCES_WORD = "((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s.,!?~#$%^&*()=+'\":;№@`]+))";
        String REGEX_SENTENCES_WHITESPACE = "((?=[a-zA-Zа-яА-ЯЁё0-9-_.,!?~#$%^&*()=+'\":;№@`]+)(?<=[\\s]+))";
        String REGEX_SENTENCES_OTHER = "((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[.,!?~#$%^&*()=+'\":;№@`]+))\"";
        regExMap.put(Type.SENTENCES, REGEX_SENTENCES_WORD + "|" + REGEX_SENTENCES_WHITESPACE + "|" + REGEX_SENTENCES_OTHER);

    }


    private static Composite parseComposite(String string, Type type) {
        Composite composite = Composite.create(type);
        String[] strings = string.split(getRegEx(type));
        Type typeForComponent = getNextType(type);
        for (String s : strings) {
            if (typeForComponent != null) {
                Composite c = parseComposite(s, typeForComponent);
                composite.add(c);
            } else {
                //  s
            }
        }


        return composite;
    }

    public static Type getNextType(Type type) {
        Handler.isNull(type);
        return typeMap.get(type);
    }

    private static String getRegEx(Type type) {
        Handler.isNull(type);
        return regExMap.get(type);
    }

    public static Composite parse(String string) {
        Handler.isNull(string);
        return parseComposite(string, Type.TEXT);
    }

}
