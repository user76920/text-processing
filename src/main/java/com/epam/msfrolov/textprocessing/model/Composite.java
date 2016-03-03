package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.TEXT;

public class Composite extends Component implements Iterable<Component> {

    private static final int INDEX_FIRST_ELEMENT = 0;
    private static final Logger LOG = LoggerFactory.getLogger(Composite.class.getName());

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

    @Override
    public String toPlainString() {
        StringBuilder stringBuilder = toPlainString(new StringBuilder());
        return stringBuilder.toString();
    }

    @Override
    public StringBuilder toPlainString(StringBuilder builder) {
        for (Component cp : components) {
            cp.toPlainString(builder);
        }
        return builder;
    }


    public enum CompositeType {
        TEXT, PARAGRAPH, SENTENCE, WORD
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Composite composite = (Composite) o;

        if (type != composite.type) return false;
        return components != null ? components.equals(composite.components) : composite.components == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    public Iterator<Component> iterator(CompositeType type) {
        if (type == WORD)
            return new WordIterator();
        return null;
    }

    private class WordIterator implements Iterator<Component> {

        private Deque<Iterator<Component>> stack;
        private Component nextComponent;

        public WordIterator() {
            stack = new ArrayDeque<>();
            Composite composite = Composite.this;
            checkCompositeType(composite.getType());
            // stack.addLast(composite.iterator());
            findFirstIterator(composite, stack);
        }

        private void findFirstIterator(Composite composite, Deque<Iterator<Component>> iteratorDeque) {
            if (composite.getType() == SENTENCE) {
                iteratorDeque.addLast(composite.iterator());
            } else {
                Iterator<Component> iterator = composite.iterator();
                iterator.next();
                iteratorDeque.addLast(iterator);
                findFirstIterator((Composite) composite.getFirstElement(), iteratorDeque);
            }
        }

        private void checkCompositeType(CompositeType currentType) {
            if (currentType != TEXT && currentType != PARAGRAPH && currentType != SENTENCE) {
                LOG.error("Wrong type for this operation (only {},{},{})", TEXT, PARAGRAPH, SENTENCE);
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean hasNext() {
            Boolean checkNext;
            Boolean checkClass = false;
            Component component = null;
            if(innerHasNext(false))
            do {
                component = this.next();
                //component = stack.peekLast().next();
                checkNext = innerHasNext(false);
                checkClass = component instanceof Composite;
            }while (!checkClass || checkNext);

            if (checkClass){
                nextComponent = component;
                return true;
            }
            return false;
        }
        private Boolean innerHasNext(Boolean b) {
            if (stack.size() == 1) {
                if (stack.peekLast().hasNext()) {
                    Composite composite = (Composite) stack.peekLast().next();
                    stack.addLast(composite.iterator());
                    b = innerHasNext(b);
                } else b = false;
            } else if (stack.size() == 2) {
                if (stack.peekLast().hasNext()) {
                    Composite composite = (Composite) stack.peekLast().next();
                    stack.addLast(composite.iterator());
                    b = innerHasNext(b);
                } else {
                    stack.pollLast();
                    b = innerHasNext(b);
                }
            } else if (stack.size() == 3) {
                if (stack.peekLast().hasNext()) {
                    b = true;
                } else {
                    stack.pollLast();
                    b = innerHasNext(b);
                }
            } else {
                b = false;
            }
            return b;
        }

        @Override
        public Component next() {
            return nextComponent;
        }

    }

    private Component getFirstElement() {
        return components.get(INDEX_FIRST_ELEMENT);
    }

    private Component getLastElement() {
        return components.get(components.size() - 1);
    }
}
