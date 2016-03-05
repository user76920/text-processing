package com.epam.msfrolov.textprocessing.parser;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.epam.msfrolov.textprocessing.model.Component.*;
import static com.epam.msfrolov.textprocessing.model.Component.Type.*;
import static com.epam.msfrolov.textprocessing.model.Composite.COMPOSITE_PROPERTIES;

public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class.getName());
    private Map<String, String> regexProperties;
    private Map<String, String> compositeProperties;
    private Map<Type, List<Type>> typeHierarchy;

    private Parser() {
        log.debug("line before init typeHierarchy");
        this.typeHierarchy = TYPE_HIERARCHY;
        log.debug("line after init typeHierarchy");
        this.compositeProperties = COMPOSITE_PROPERTIES;
    }

    private Parser(Map<String, String> regexProperties) {
        this();
        this.regexProperties = regexProperties;
    }

    public static Parser create() {
        Parser parser = new Parser();
        parser.regexProperties = REGEX_PROPERTIES;
        return parser;
    }

    public static Parser create(Map<String, String> regexMap) {
        return new Parser(regexMap);
    }


    public Composite parse(String string) {
        Checker.isNull(string);
        return parse(string, TEXT);
    }

    private Composite parse(String string, Type currentType) {
        Composite composite = Composite.create(currentType);
        if (currentType == WORD) {
            return composite;
        } else if (currentType == SENTENCE) {
            String[] strings = string.split(getRegex(currentType));
            for (String componentString : strings) {
                Composite compositeWord = parse("", getSubComposite(currentType));
                char[] symbols = componentString.toCharArray();
                for (int i = 0; i < symbols.length; i++) {
                    Char currentChar = Char.create(symbols[i]);
                    if (currentChar.getType() == LETTER) {
                        compositeWord.add(currentChar);
                        if (i + 1 == symbols.length || !Char.isSymbolForWord(symbols[i + 1]))
                            composite.add(compositeWord);
                    } else composite.add(currentChar);
                }
            }
        } else {
            log.debug("getRegex(currentType) {} currentType {}", getRegex(currentType), currentType);
            String[] strings = string.split(getRegex(currentType));
            Type subType = getSubComposite(currentType);
            for (String componentString : strings)
                composite.add(parse(componentString, subType));
        }
        return composite;
    }

    public boolean isSubType(Type mainType, Type subType) {
        Checker.isNull(mainType);
        Checker.isNull(subType);
        return typeHierarchy.get(mainType).contains(subType);
    }

    public Type getSubComposite(Type type) {
        Checker.isNull(type);
        log.debug("type {} and type.toString() {}", type, type.toString());
        log.debug("compositeProperties.get(type.toString()) {}",compositeProperties.get(type.toString()));
        return Type.valueOf(compositeProperties.get(type.toString()));
    }

    public String getRegex(Type type) {
        Checker.isNull(type);
        return regexProperties.get(type.toString());
    }

}
