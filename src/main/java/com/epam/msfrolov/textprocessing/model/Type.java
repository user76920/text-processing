package com.epam.msfrolov.textprocessing.model;


import com.epam.msfrolov.textprocessing.util.Handler;

import java.util.ArrayList;
import java.util.List;

public class Type {
    public static final Type TEXT = new Type("TEXT");
    public static final Type PARAGRAPH = new Type("PARAGRAPH");
    public static final Type SENTENCES = new Type("SENTENCES");

    public static final Type WORD = new Type("WORD");
    public static final Type LETTER_OR_NUMBER = new Type("LETTER_OR_NUMBER");

    public static final Type NON_WORD = new Type("NON_WORD");
    public static final Type PUNCTUATION = new Type("PUNCTUATION");
    public static final Type WHITESPACE = new Type("WHITESPACE");
    public static final Type OTHER_SYMBOL = new Type("OTHER SYMBOL");

    private static List<Type> PERMANENT_INDIVISIBLE_TYPES;

    static {
        PERMANENT_INDIVISIBLE_TYPES = new ArrayList<>();
        PERMANENT_INDIVISIBLE_TYPES.add(LETTER_OR_NUMBER);
        PERMANENT_INDIVISIBLE_TYPES.add(PUNCTUATION);
        PERMANENT_INDIVISIBLE_TYPES.add(WHITESPACE);
        PERMANENT_INDIVISIBLE_TYPES.add(OTHER_SYMBOL);
    }

    private static List<Type> PERMANENT_NON_INDIVISIBLE_TYPES;

    static {
        PERMANENT_NON_INDIVISIBLE_TYPES = new ArrayList<>();
        PERMANENT_NON_INDIVISIBLE_TYPES.add(TEXT);
        PERMANENT_NON_INDIVISIBLE_TYPES.add(PARAGRAPH);
        PERMANENT_NON_INDIVISIBLE_TYPES.add(SENTENCES);
        PERMANENT_NON_INDIVISIBLE_TYPES.add(WORD);
        PERMANENT_NON_INDIVISIBLE_TYPES.add(NON_WORD);
    }

    private static List<Type> INDIVISIBLE_TYPES;

    static {
        INDIVISIBLE_TYPES = new ArrayList<>();
        INDIVISIBLE_TYPES.addAll(PERMANENT_INDIVISIBLE_TYPES);
    }

    private final String name;

    public static boolean isIndivisibleTypes(Type type) {
        return INDIVISIBLE_TYPES.contains(type);
    }

    public static boolean addIndivisibleTypes(Type type) {
        if (!PERMANENT_NON_INDIVISIBLE_TYPES.contains(type)
                && !PERMANENT_INDIVISIBLE_TYPES.contains(type)) {
            INDIVISIBLE_TYPES.add(type);
            return true;
        } else return false;
    }

    private Type(String name) {
        this.name = name;
    }

    public static Type newType(String name) {
        return new Type(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        Handler.isNull(o);
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Type type = (Type) o;

        return name != null ? name.equals(type.name) : type.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

