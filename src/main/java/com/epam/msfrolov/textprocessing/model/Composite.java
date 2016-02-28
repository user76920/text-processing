package com.epam.msfrolov.textprocessing.model;

import java.util.ArrayList;
import java.util.List;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;

public class Composite extends Component {
    private List<Component> components;

    private Composite(Type type) {
        this.components = new ArrayList<>();
        this.setType(type);
    }

    public static Composite create(Type type) {
        return new Composite(type);
    }

    public static Composite create() {
        return create(TEXT);
    }

    public void add(Component component) {
        components.add(component);
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

    public enum CompositeType implements Type {
        TEXT, PARAGRAPH, SENTENCE, WORD
    }

}
