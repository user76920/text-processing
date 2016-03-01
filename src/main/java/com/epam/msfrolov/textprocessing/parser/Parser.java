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

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class.getName());

    private Map<CompositeType, CompositeType> typeMap;
    private Map<CompositeType, String> regexMap;

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

    private Map<CompositeType, CompositeType> initializationTypeMap() {
        Map<CompositeType, CompositeType> typeMap = new HashMap<>();
        typeMap.put(TEXT, PARAGRAPH);
        typeMap.put(PARAGRAPH, SENTENCE);
        typeMap.put(SENTENCE, WORD);
        return typeMap;
    }

    private Composite parse(String string, CompositeType type) {
        Composite composite = Composite.create(type);
        String[] strings = string.split(getRegex(type));
        CompositeType typeForComponent = getSubType(type);
        if (typeForComponent != WORD) {
            for (String componentString : strings) {
                Composite parseComposite = parse(componentString, typeForComponent);
                composite.add(parseComposite);
            }
        } else for (String componentString : strings) {
            Composite compositeWord = Composite.create(WORD);
            char[] symbols = componentString.toCharArray();
            for (int i = 0; i < symbols.length; i++) {   //create new Char class or use .isSymbolForWord for char;
                if (Char.isSymbolForWord(symbols[i])){
                    compositeWord.add(Char.create(symbols[i]));
                    if (!Char.isSymbolForWord(symbols[i + 1])) composite.add(compositeWord);
                } else composite.add(Char.create(symbols[i]));
            }
        }
        return composite;
    }

    public Composite parse(String string) {
        Checker.isNull(string);
        LOG.debug("");
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

    static class RegexArgument {

        private static Logger LOG = LoggerFactory.getLogger(RegexArgument.class.getName());

        public static Map<CompositeType, String> getRegexFromProperty() {
            Map<CompositeType, String> regexMap = new HashMap<>();
            String text = getRegexTypeFromProperty("text");
            String paragraph = getRegexTypeFromProperty("paragraph");
            String sentences = getRegexTypeFromProperty("sentences");
            String word = getRegexTypeFromProperty("word");
            regexMap.put(TEXT, text);
            regexMap.put(PARAGRAPH, paragraph);
            regexMap.put(SENTENCE, sentences);
            regexMap.put(WORD, word);
            return regexMap;
        }

        private static String getRegexTypeFromProperty(String propertyKey) {
            Properties regexProperties = new Properties();
            try {
                regexProperties.load(RegexArgument.class.getClassLoader().getResourceAsStream("regExType.properties"));
            } catch (IOException e) {
                LOG.error("Property with RegEx arguments was not read!", e);
            }
            return regexProperties.getProperty(propertyKey);
        }

    }
}
