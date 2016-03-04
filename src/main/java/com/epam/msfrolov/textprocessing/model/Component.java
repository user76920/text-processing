package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.PropertiesService;

import java.util.Map;

public abstract class Component implements Comparable<Component> {
    private static final Map<Type, Type> typeProperties = PropertiesService.get("regExType.properties");
    private static final Map<String, String> regexProperties = PropertiesService.get("type.properties");

    private Type type;

    protected abstract StringBuilder toPlainString(StringBuilder sb);

    public abstract String toPlainString();

    public Type getType() {
        return this.type;
    }

    protected void setType(Type type) {
        this.type = type;
    }

    @Override
    public int compareTo(Component that) {
        return this.toPlainString().compareTo(that.toPlainString());
    }

    public enum Type {
        TEXT, PARAGRAPH, SENTENCE, WORD, LETTER, WHITESPACE, PUNCTUATION, OTHER;
    }
}
