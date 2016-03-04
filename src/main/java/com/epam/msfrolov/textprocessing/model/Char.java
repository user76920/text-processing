package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;


public class Char extends Component {
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
        if (String.valueOf(symbol).matches(Component.regexProperties.get(PUNCTUATION)))
            return PUNCTUATION;
        if (Character.isWhitespace(symbol))
            return WHITESPACE;
        if (Character.isLetter(symbol)
                || Character.isDigit(symbol)
                || String.valueOf(symbol).matches(Component.regexProperties.get(LETTER)))
            return LETTER;
        else
            return OTHER;
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
