package com.epam.msfrolov.textprocessing.model;


import com.epam.msfrolov.textprocessing.util.Handler;

public class Type {
    public static final Type TEXT = new Type("TEXT");
    public static final Type PARAGRAPH = new Type("PARAGRAPH");
    public static final Type SENTENCES = new Type("SENTENCES");
    public static final Type WORD = new Type("WORD");
    public static final Type LETTER_OR_NUMBER = new Type("LETTER_OR_NUMBER");
    public static final Type PUNCTUATION = new Type("PUNCTUATION");
    public static final Type WHITESPACE = new Type("WHITESPACE");
    public static final Type OTHER_SYMBOL = new Type("OTHER SYMBOL");

    private final String name;

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

