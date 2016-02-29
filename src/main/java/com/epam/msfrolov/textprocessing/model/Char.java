package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import com.epam.msfrolov.textprocessing.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.*;

public class Char extends Component {
    private final char value;
    private static Logger LOG = LoggerFactory.getLogger(Char.class.getName());

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
        if (String.valueOf(symbol).matches(Parser.getRegex(PUNCTUATION)))
            return PUNCTUATION;
        if (Character.isWhitespace(symbol))
            return WHITESPACE;
        if (Character.isLetter(symbol))
            return LETTER;
        if (Character.isDigit(symbol))
            return DIGIT;
        else
            return OTHER;
    }


    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    public enum CharType implements Type {
        LETTER, DIGIT, WHITESPACE, PUNCTUATION, OTHER
    }

}
