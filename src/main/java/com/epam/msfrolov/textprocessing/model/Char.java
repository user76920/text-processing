package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;


public class Char extends Component {
    private final char value;
    private Type type;

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value) {
        Char character = new Char(value);
        character.setType(checkType(value));
        Checker.isNull(character.type);
        return character;
    }

    private static Type checkType(char symbol) {
        if (String.valueOf(symbol).matches("([.!?,:\"';()\\[\\]\\{\\}])"))
            return PUNCTUATION;
        if (Character.isWhitespace(symbol))
            return WHITESPACE;
        if (Character.isLetter(symbol) || Character.isDigit(symbol) || String.valueOf(symbol).matches("([-_])"))
            return LETTER;
        else
            return OTHER;
    }

    public static boolean isSymbolForWord(char value) {
        return (Character.isLetter(value) || Character.isDigit(value) || String.valueOf(value).matches("([-_])"));
    }

    @Override
    public Type getType() {
        Checker.isNull(type);
        return type;
    }

    private void setType(Type type) {
        Checker.isNull(type);
        this.type = type;
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

        if (value != aChar.value) return false;
        return type == aChar.type;

    }

    @Override
    public int hashCode() {
        int result = (int) value;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
