package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import com.epam.msfrolov.textprocessing.util.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Component.Type.TEXT;
import static com.epam.msfrolov.textprocessing.util.Checker.isNull;

public class Composite extends Component implements Iterable<Component> {
    public static final Map<String, String> COMPOSITE_PROPERTIES = PropertiesService.get("composite.hierarchy.properties");
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

    public static Type getSubCompositeType(Type type) {
        Checker.isNull(type);
        String strType = COMPOSITE_PROPERTIES.get(type.toString());
        return Type.valueOf(strType);
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public boolean contains(Component component) {
        return components.contains(component);
    }

    public int size() {
        return components.size();
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

    public int removeAllComponent(Component component) {
        int counter = 0;
        return removeAllComponent(this, component, counter);
    }

    private int removeAllComponent(Composite composite, Component removableComponent, int counter) {
        while (composite.remove(removableComponent))
            counter++;
        for (Component component : composite)
            if (component instanceof Composite)
                counter = removeAllComponent((Composite) component, removableComponent, counter);
        return counter;
    }


    public void replaceComposite(Composite composite) {
        this.clear();
        this.components = composite.components;
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

    public boolean containsSubtype(Type subType) {
        Checker.isNull(this.getType());
        Checker.isNull(subType);
        return TYPE_HIERARCHY.get(this.getType()).contains(subType);
    }

    public int getNumberOfComponents(Type type) {
        int quantity = 0;
        quantity = countNumberOfElements(type, quantity, this);
        return quantity;
    }

    private int countNumberOfElements(Type type, int quantity, Composite composite) {
        if (composite.getType() == type) quantity++;
        for (Component component : composite)
            if (component.getType() == type)
                quantity++;
            else if (component instanceof Composite && ((Composite) component).containsSubtype(type))
                quantity = countNumberOfElements(type, quantity, (Composite) component);
        return quantity;
    }

//ITERATORS:

    public Iterator<Component> iterator() {
        return components.iterator();
    }

    public Iterator<Component> iterator(Type type) {
        if (containsSubtype(type)) {
            if (type == getSubCompositeType(Composite.this.getType()))
                return Composite.this.iterator();
            else return new TypeIterator(type);
        } else throw new IllegalArgumentException("Selected type is not subtype!");
    }

    private class TypeIterator implements Iterator<Component> {
        private List<Component> components = new ArrayList<>();
        private Type selectedType;
        private Iterator<Component> iterator;

        public TypeIterator(Type type) {
            selectedType = type;
            accumulateComponent(Composite.this);
            iterator = components.iterator();
        }

        private void accumulateComponent(Composite composite) {
            for (Component component : composite) {
                if (component.getType() == selectedType)
                    components.add(component);
                else if (component instanceof Composite && ((Composite) component).containsSubtype(selectedType))
                    accumulateComponent((Composite) component);
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Component next() {
            return iterator.next();
        }
    }

}
