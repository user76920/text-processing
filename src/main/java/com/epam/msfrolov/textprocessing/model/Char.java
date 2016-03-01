package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.*;

public class Char extends Component {
    private static Logger LOG = LoggerFactory.getLogger(Char.class.getName());

    private CharType type;
    private final char value;

    public CharType getType() {
        Checker.isNull(type);
        return type;
    }

    private void setType(CharType type) {
        Checker.isNull(type);
        this.type = type;
    }

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value) {
        Char character = new Char(value);
        character.setType(checkType(value));
        Checker.isNull(character.type);
        return character;
    }

    private static CharType checkType(char symbol) {
        if (String.valueOf(symbol).matches("([.!?,:\"';()\\[\\]\\{\\}])"))
            return PUNCTUATION;
        if (Character.isWhitespace(symbol))
            return WHITESPACE;
        if (Character.isLetter(symbol))
            return LETTER;
        if (Character.isDigit(symbol))
            return DIGIT;
        if (String.valueOf(symbol).matches("([-_])"))
            return DASH;
        else
            return OTHER;
    }

    public boolean isSymbolForWord(){
        return type == Char.CharType.LETTER
                || type == Char.CharType.DIGIT
                || type == Char.CharType.DASH;
    }
    public static boolean isSymbolForWord(char value){
        return  (Character.isLetter(value)||Character.isDigit(value)||String.valueOf(value).matches("([-_])"));
    }

    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    public enum CharType {
        LETTER, DIGIT, WHITESPACE, PUNCTUATION, DASH, OTHER
    }

}
