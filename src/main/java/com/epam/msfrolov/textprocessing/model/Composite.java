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
    public Iterator<Component> iterator(Type type) {
        Iterator<Component> iterator = null;
        log.debug("type {}", type);
        if (type == Composite.this.getType())
            throw new IllegalArgumentException("Not valid type");
        else if (type == WORD) iterator = new FilterIterator();
        else if (type.toString().equals(COMPOSITE_PROPERTIES.get(Composite.this.getType())))
            iterator = Composite.this.iterator();
        else {
            iterator = new DeepIterator();
        }
        return iterator;
    }


    private class CompositeIterator implements Iterator<Component> {

        Deque<Component> stack = new LinkedList<>();


        public CompositeIterator() {
            stack.add(Composite.this);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Component next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }
            Component node = stack.pop();
            if (node != null) { //only if Composite.children has null
                if (node instanceof Composite) {
                    Composite ac = (Composite) node;
                    for (Component acc : ac.components) {
                        stack.add(acc);
                    }
                }
            }
            return node;
        }
    }

    private class FilterIterator implements Iterator<Component> {


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Component next() {
            return null;
        }
    }

    private class DeepIterator implements Iterator<Component> {
        @Override
        public boolean hasNext() {
            return ;
        }

        @Override
        public Component next() {
            return null;
        }
    }

}
