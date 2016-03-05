package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Component.Type.TEXT;
import static com.epam.msfrolov.textprocessing.model.Component.Type.WORD;
import static com.epam.msfrolov.textprocessing.util.Checker.isNull;

public class Composite extends Component implements Iterable<Component> {
    public static final Map<String, String> COMPOSITE_PROPERTIES = PropertiesService.get("compositeHierarchy.properties");
    private static final Logger log = LoggerFactory.getLogger(Composite.class);
    private List<Component> components;


    private Composite(Type type) {
        isNull(type);
        this.components = new ArrayList<>();
        this.setType(type);
    }

    public static Composite create() {
        return create(TEXT);
    }

    //DELEGATION FROM LIST:

    public static Composite create(Type type) {
        return new Composite(type);
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public void add(Component component) {
        components.add(component);
    }

    public Component get(int i) {
        return components.get(i);
    }

    public boolean remove(Component component) {
        return components.remove(component);
    }

    public void clear() {
        components.clear();
    }

    @Override
    public String toPlainString() {
        StringBuilder stringBuilder = toPlainString(new StringBuilder());
        return stringBuilder.toString();
    }

    @Override
    protected StringBuilder toPlainString(StringBuilder builder) {
        for (Component cp : components) {
            cp.toPlainString(builder);
        }
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Composite composite = (Composite) o;

        return components != null ? components.equals(composite.components) : composite.components == null;

    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }


    //ITERATORS:

    public Iterator<Component> iterator() {
        return components.iterator();
    }
}
