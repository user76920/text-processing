package com.epam.msfrolov.textprocessing.parser;

import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.Type;
import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.*;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class.getName());

    private static Map<Type, Type> typeMap;

    static {

        LOG.info("");

        typeMap = new HashMap<>();
        typeMap.put(TEXT, PARAGRAPH);
        typeMap.put(PARAGRAPH, SENTENCE);
    }

    private Map<Composite.CompositeType, String> regExMap;


    private static Composite parse(String string, CompositeType type) {
        Composite composite = Composite.create(type);
        String[] strings = string.split(getRegex(type));
        Type typeForComponent = getTypeHeir(type);
        LOG.info("typeForComponent " + String.valueOf(typeForComponent));
        if (type != null) {
            LOG.info("type != null");
            for (String componentString : strings) {
                Composite parseComposite = parse(componentString, typeForComponent);
                composite.add(parseComposite);
            }
        } else {
            LOG.info("type == null");
        }
        return composite;
    }

    public static Type getTypeHeir(Type type) {
        Checker.isNull(type);
        return typeMap.get(type);
    }

    public static String getRegex(Type type) {
        Checker.isNull(type);
        return regExMap.get(type);
    }

    public static Composite parse(String string) {
        Checker.isNull(string);
        return parse(string, TEXT);
    }

}
