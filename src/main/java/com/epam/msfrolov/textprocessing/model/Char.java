package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import com.epam.msfrolov.textprocessing.util.PropertiesService;

import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;

public class Char extends Component {
    private static final Map<String, List<Component>> TYPE_LETTERS_PROPERTIES = new HashMap<>();

    static {
       Map<String,String> properties = PropertiesService.get("typeLetters.properties");
        for (Map.Entry<String,String> entry:properties.entrySet()) {
            String key = entry.getKey();
            List<Component> value = new ArrayList<>();
            String[] letters = entry.getValue().split(",");
            for (String letter:letters) {
                value.add(Char.create(letter.charAt(0)));
            }
            TYPE_LETTERS_PROPERTIES.put(key,value);
        }
    }

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

    public boolean isVowel(){
        return  (TYPE_LETTERS_PROPERTIES.get("vowels").contains(this));
    }
    public boolean isConsonant(){
        return  (TYPE_LETTERS_PROPERTIES.get("consonants").contains(this));
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
