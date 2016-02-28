package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Handler;
import com.epam.msfrolov.textprocessing.util.Parser;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.*;

public class Char extends Component {
    private final char value;

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value) {
        Char character = new Char(value);
        character.setType(checkType(value));
        Handler.isNull(character.type);
        return character;
    }

    private static Type checkType(char symbol) {
        if (String.valueOf(symbol).matches(Parser.getRegex(PUNCTUATION)))
            return PUNCTUATION;
        if (String.valueOf(symbol).matches(Parser.getRegex(WHITESPACE)))
            return WHITESPACE;
        if (String.valueOf(symbol).matches(Parser.getRegex(SYMBOL)))
            return SYMBOL;
        else
            return OTHER;
    }


    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    public enum CharType implements Type {
        SYMBOL, WHITESPACE, PUNCTUATION, OTHER
    }

}
