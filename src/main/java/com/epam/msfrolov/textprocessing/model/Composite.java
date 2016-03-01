package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;

public class Composite extends Component implements Iterable<Component> {

    private CompositeType type;
    private List<Component> components;

    private Composite(CompositeType type) {
        this.components = new ArrayList<>();
        this.setType(type);
    }


    public static Composite create(CompositeType type) {
        return new Composite(type);
    }

    public static Composite create() {
        return create(TEXT);
    }

    public Iterator<Component> iterator() {
        return components.iterator();
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public CompositeType getType() {
        Checker.isNull(type);
        return type;
    }

    private void setType(CompositeType type) {
        Checker.isNull(type);
        this.type = type;
    }

    public void add(Component component) {
        components.add(component);
    }

    //TODO implemetn method
    public void remove() {
    }

    public void clear() {
        components.clear();
    }

    public String toPlainString() {
        StringBuilder stringBuilder = toPlainString(new StringBuilder());
        return stringBuilder.toString();
    }

    @Override
    public StringBuilder toPlainString(StringBuilder builder) {
        for (Component cp : components) {
            cp.toPlainString(builder);
            System.out.println(builder);
        }
        return builder;
    }


    public enum CompositeType {
        TEXT, PARAGRAPH, SENTENCE, WORD
    }

}
