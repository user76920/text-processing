package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Handler;
import com.epam.msfrolov.textprocessing.util.Parser;

public class Char extends Component {
    private final char value;

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value) {
        Char character = new Char(value);
        character.setType(checkType(value));
       // Handler.isNull(character.type);
        return character;
    }

    private static Type checkType(char symbol) {
        /*Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9]");
        Matcher matcher = pattern.matcher(String.valueOf(symbol));
        if (matcher.find()) return Type.LETTER_OR_NUMBER;
        */
        if (String.valueOf(symbol).matches(Parser.getRegEx(Type.WORD)))
        return Type.LETTER_OR_NUMBER;
        if (String.valueOf(symbol).matches(Parser.getRegEx(Type.NON_WORD)))
        return Type.OTHER_SYMBOL;
        return null;
    }


    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    public enum CharType implements Type {
        WHITESPACE, PUNCTUATION,OTHER
    }

}
