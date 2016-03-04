package com.epam.msfrolov.textprocessing.model;

public abstract class Component {
    protected Enum type;
    protected abstract StringBuilder toPlainString(StringBuilder sb);
    public abstract String toPlainString();
    public abstract Enum getType();

    public enum Type {
        TEXT, PARAGRAPH, SENTENCE, WORD, LETTER, WHITESPACE, PUNCTUATION, OTHER
    }
}
