package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.PropertiesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Component implements Comparable<Component> {
    public static final Map<String, String> REGEX_PROPERTIES = PropertiesService.get("regExType.properties");
    public static final Map<Type, List<Type>> TYPE_HIERARCHY = new HashMap<>();

    //INIT TYPE HIERARCHY
    static {
        Map<String, String> typeProperties = PropertiesService.get("typeHierarchy.properties");
        for (Map.Entry<String, String> entry : typeProperties.entrySet()) {
            Type typeKey = Type.valueOf(entry.getKey());
            List<Type> typesValue = new ArrayList<>();
            String[] strings = entry.getValue().split(",");
            for (String s : strings) {
                typesValue.add(Type.valueOf(s));
            }
            TYPE_HIERARCHY.put(typeKey, typesValue);
        }
    }

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

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public enum Type {
        TEXT,
        PARAGRAPH,
        SENTENCE,
        WORD,
        LETTER,
        WHITESPACE,
        PUNCTUATION,
        OTHER;

        public boolean isComposite() {
            return (this == TEXT || this == PARAGRAPH || this == SENTENCE || this == WORD);
        }

        @Override
        public String toString() {
            return name();
        }
    }
}
