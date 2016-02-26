package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Char extends Component {
    private final char value;

    private Char(char value) {
        this.value = value;
    }

    public static Char create(char value){
        Char character = new Char(value);
        character.setType(checkType(value));
        return character;
    }

    private static Type checkType(char symbol) {

        /*Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9]");
        Matcher matcher = pattern.matcher(String.valueOf(symbol));
        if (matcher.find()) return Type.LETTER_OR_NUMBER;
        */
        if (String.valueOf(symbol).matches("[a-zA-Zа-яА-ЯЁё0-9-_]"))
            return Type.LETTER_OR_NUMBER;
        else
        return Type.OTHER_SYMBOL;
    }


    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }
}
