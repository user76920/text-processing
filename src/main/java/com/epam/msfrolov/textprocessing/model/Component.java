package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class Component implements Comparable<Component> {
   private static final Logger log = LoggerFactory.getLogger(Component.class);
    public static final Map<String, String> REGEX_PROPERTIES = PropertiesService.get("regExType.properties");
    public static final Map<Type, List<Type>> TYPE_HIERARCHY = new HashMap<>();
   // private static final Map<String, String> TYPE_PROPERTIES = PropertiesService.get("typeHierarchy.properties");

    //INIT TYPE HIERARCHY
    static {
        log.debug("into static init class Component");
        Map<String, String> typeProperties = PropertiesService.get("typeHierarchy.properties");
        log.debug("typeProperties==null - {}",typeProperties==null);
        for (Map.Entry<String, String> entry : typeProperties.entrySet()) {
            log.debug("entry.getKey() {}",entry.getKey());
            Type typeKey = Type.valueOf(entry.getKey());
            log.debug("typeKey {}",typeKey);
            List<Type> typesValue = new ArrayList<>();
            log.debug("current typeKey {} and typesValue {}", typeKey, entry.getValue());
            String[] strings = entry.getValue().split(",");
            log.debug("current typeKey {} and typesValue {}", typeKey, Arrays.toString(strings));
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
