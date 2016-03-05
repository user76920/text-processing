package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;

public class Char extends Component {
   private static final Logger log = LoggerFactory.getLogger(Char.class);
    private final char value;

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value) {
        Char character = new Char(value);
        character.setType(checkType(value));
        Checker.isNull(character.getType());
        return character;
    }

    private static Type checkType(char symbol) {
        log.debug("symbol {} Component.REGEX_PROPERTIES.get(PUNCTUATION)) {}", symbol, Component.REGEX_PROPERTIES.get(PUNCTUATION.toString()));
        if (String.valueOf(symbol).matches(Component.REGEX_PROPERTIES.get(PUNCTUATION.toString())))
            return PUNCTUATION;
        if (Character.isWhitespace(symbol))
            return WHITESPACE;
        if (Character.isLetter(symbol) || Character.isDigit(symbol) || String.valueOf(symbol).matches(Component.REGEX_PROPERTIES.get(LETTER.toString())))
            return LETTER;
        else
            return OTHER;
    }

    public static boolean isSymbolForWord(char symbol) {
        return (Character.isLetter(symbol) || Character.isDigit(symbol) || String.valueOf(symbol).matches(Component.REGEX_PROPERTIES.get(LETTER.toString())));
    }

    @Override
    protected StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    @Override
    public String toPlainString() {
        StringBuilder stringBuilder = toPlainString(new StringBuilder());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Char aChar = (Char) o;

        return value == aChar.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
