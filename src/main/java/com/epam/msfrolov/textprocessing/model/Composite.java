package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.TEXT;

public class Composite extends Component implements Iterable<Component> {

    public static final Comparator<Component> COMPARE_SUBCOMPONENT
            = (o1, o2) -> o1.toPlainString().compareTo(o2.toString());
    private static final int INDEX_FIRST_ELEMENT = 0;
    private static final Logger LOG = LoggerFactory.getLogger(Composite.class.getName());

    private CompositeType type;
    private List<Component> components;
    private static Map<CompositeType, Integer> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put(TEXT, 0);
        typeMap.put(PARAGRAPH, 1);
        typeMap.put(SENTENCE, 2);
        typeMap.put(WORD, 3);
    }


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

    public boolean isEmpty() {
        return components.isEmpty();
    }

    @Override
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
    protected StringBuilder toPlainString(StringBuilder builder) {
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

    private Component getFirstElement() {
        return components.get(INDEX_FIRST_ELEMENT);
    }

    //Iterators

    public Iterator<Component> iterator(CompositeType type) {
        if (type == WORD)
            return new WordIterator();
        if (type == SENTENCE)
            return new SentenceIterator();
        return null;
    }

    private Component getLastElement() {
        return components.get(components.size() - 1);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    public Iterator<Component> iterator() {
        return components.iterator();
    }

    private class WordIterator implements Iterator<Component> {

        private Deque<Iterator<Component>> stack;

        public WordIterator() {
            stack = new ArrayDeque<>();
            Composite composite = Composite.this;
            checkCompositeType(composite.getType());
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
            return innerHasNext(false);
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
            return stack.peekLast().next();
        }

    }

    private class SentenceIterator implements Iterator<Component> {

        private Deque<Iterator<Component>> stack;

        public SentenceIterator() {
            stack = new ArrayDeque<>();
            Composite composite = Composite.this;
            checkCompositeType(composite.getType());
            findFirstIterator(composite, stack);
        }

        private void findFirstIterator(Composite composite, Deque<Iterator<Component>> iteratorDeque) {
            if (composite.getType() == PARAGRAPH) {
                iteratorDeque.addLast(composite.iterator());
            } else {
                Iterator<Component> iterator = composite.iterator();
                iterator.next();
                iteratorDeque.addLast(iterator);
                findFirstIterator((Composite) composite.getFirstElement(), iteratorDeque);
            }
        }

        private void checkCompositeType(CompositeType currentType) {
            if (currentType != PARAGRAPH && currentType != TEXT) {
                LOG.error("Wrong type for this operation (only {},{},{})", TEXT, PARAGRAPH);
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean hasNext() {
            return innerHasNext(false);
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
            return stack.peekLast().next();
        }


    }

    //Function

    public int getNumberOfChar() {
        return getNumberOfChar(this, 0);
    }

    private int getNumberOfChar(Composite composite, int i) {
        for (Component subComponent : composite) {
            if (subComponent instanceof Char) {
                i++;
            } else {
                i = getNumberOfChar((Composite) subComponent, i);
            }
        }
        return i;
    }

    public int getNumberOfComposite(CompositeType type) {
        return getNumberOfComposite(this, 0, type);
    }

    private int getNumberOfComposite(Composite composite, int i, CompositeType type) {

        for (Component subComponent : composite) {
            if (subComponent.getType() == type) {
                i++;
            } else if (!(subComponent instanceof Char))
                if (typeMap.get(((Composite) subComponent).getType()) < typeMap.get(type))
                    i = getNumberOfComposite((Composite) subComponent, i, type);
        }
        return i;
    }

    public List<Composite> extractListComposite(CompositeType type, boolean unique) {
        List<Composite> compositeList = new ArrayList<>();
        extractListComposite(this, compositeList, type, unique);
        return compositeList;
    }

    private void extractListComposite(Composite value, List<Composite> compositeList, CompositeType type, boolean unique) {
        for (Component component : value) {
            if (component instanceof Composite) {
                Composite composite = (Composite) component;
                if (composite.getType() == type) {
                    if (unique) {
                        if (!compositeList.contains(composite)) {
                            compositeList.add(composite);
                        }
                    }else compositeList.add(composite);
                } else if (typeMap.get(composite.getType()) < typeMap.get(type))
                    extractListComposite(composite, compositeList, type, unique);
            }
        }
    }

    public List<String> extractListString(CompositeType type, boolean unique) {
        List<String> compositeList = new ArrayList<>();
        extractListString(this, compositeList, type, unique);
        return compositeList;
    }

    private void extractListString(Composite value, List<String> compositeList, CompositeType type, boolean unique) {
        for (Component component : value) {
            if (component instanceof Composite) {
                Composite composite = (Composite) component;
                if (composite.getType() == type) {
                    if (unique) {
                        if (!compositeList.contains(composite)) {
                            compositeList.add(composite.toPlainString());
                        }
                    }else compositeList.add(composite.toPlainString());
                } else if (typeMap.get(composite.getType()) < typeMap.get(type))
                    extractListString(composite, compositeList, type, unique);
            }
        }
    }

}
