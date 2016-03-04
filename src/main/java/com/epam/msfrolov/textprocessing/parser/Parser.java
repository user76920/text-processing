package com.epam.msfrolov.textprocessing.parser;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.Composite.CompositeType;
import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class.getName());



    private Parser() {
        this.typeMap = initializationTypeMap();
        this.regexMap = RegexArgument.getRegexFromProperty();
    }

    private Parser(Map<CompositeType, String> regexMap) {
        this.typeMap = initializationTypeMap();
        this.regexMap = regexMap;
    }

    public static Parser create() {
        return new Parser();
    }

    public static Parser create(Map<CompositeType, String> regexMap) {
        return new Parser(regexMap);
    }

    private Composite parse(String string, CompositeType type) {
        Composite composite = Composite.create(type);
        if (type == WORD) {
            //TODO
        } else if (type == SENTENCE) {
            String[] strings = string.split(getRegex(type));
            CompositeType subType = getSubType(type);
            for (String componentString : strings) {
                Composite compositeWord = Composite.create(WORD);
                char[] symbols = componentString.toCharArray();
                for (int i = 0; i < symbols.length; i++) {  //create new Char class and compare type or use .isSymbolForWord for char;
                    if (Char.isSymbolForWord(symbols[i])) {
                        compositeWord.add(Char.create(symbols[i]));
                        if (!Char.isSymbolForWord(symbols[i + 1])) composite.add(compositeWord);
                    } else composite.add(Char.create(symbols[i]));
                }
            }
        } else {
            String[] strings = string.split(getRegex(type));
            CompositeType subType = getSubType(type);
            for (String componentString : strings) {
                Composite component = parse(componentString, subType);
                composite.add(component);
            }
        }
        return composite;
    }

    public Composite parse(String string) {
        Checker.isNull(string);
        return parse(string, TEXT);
    }

    public CompositeType getSubType(CompositeType type) {
        Checker.isNull(type);
        return typeMap.get(type);
    }

    public String getRegex(CompositeType type) {
        Checker.isNull(type);
        return regexMap.get(type);
    }

}
