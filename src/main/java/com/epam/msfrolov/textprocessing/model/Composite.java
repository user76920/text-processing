package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.Checker;
import com.epam.msfrolov.textprocessing.util.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.epam.msfrolov.textprocessing.model.Component.Type.TEXT;
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


    public void replaceComposite(Composite composite){
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
        if (containsSubtype(type))
            return new TypeIterator(type);
        else throw new IllegalArgumentException("Selected type is not subtype!");
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

    private class IteratorComponent implements Iterator<Component> {
        private Type selectedType;
        private Component nextComponent;
        private Deque<Iterator<Component>> stack = new ArrayDeque<>();

        public IteratorComponent(Type type) {
            selectedType = type;
            stack.addLast(components.iterator());
            if (stack.peekLast().hasNext())
                nextComponent = stack.peekLast().next();
        }

        @Override
        public boolean hasNext() {
            return nextComponent != null;
        }

        @Override
        public Component next() {
            if (nextComponent == null) throw new NoSuchElementException();
            return null;
        }
    }

    private class IteratorComposite implements Iterator<Component> {

        private Type selectedType;
        private Component nextComponent;
        private Component currentComposite;
        private Deque<Iterator<Component>> stack = new ArrayDeque<>();

        public IteratorComposite(Type type) {
            selectedType = type;
            stack.addLast(components.iterator());
            nextComponent = next();
        }

        @Override
        public boolean hasNext() {
            return nextComponent != null;
        }


        @Override
        public Component next() {
            if (hasNext()) {
                currentComposite = nextComponent;
                nextComponent = null;
            }
            log.debug("1");
            if (stack.peekLast().hasNext()) {
                Component component = stack.peekLast().next();
                if (component.getType() == selectedType) {
                    log.debug("3");
                    nextComponent = component;
                    if (currentComposite == null) {
                        log.debug("4");
                        return nextComponent;
                    }
                    return currentComposite;
                } else if (component instanceof Composite && ((Composite) component).containsSubtype(selectedType)) {
                    log.debug("2");
                    stack.addLast(((Composite) component).iterator());
                    // nextComponent = next();
                } else {
                    stack.pollLast();
                    //nextComponent = next();
                }
                nextComponent = next();
                if (currentComposite == null)
                    return nextComponent;
                return currentComposite;
            } else if (stack.size() == 0) {
                return currentComposite;
            } else {
                nextComponent = next();
                return currentComposite;
            }
        }
    }

    private class CompositeIterator implements Iterator<Component> {
        Deque<Component> stack = new ArrayDeque<>();
        Type selectedType;
        Component nextComponent;

        public CompositeIterator(Type type) {
            selectedType = type;
            stack.addLast(Composite.this);


            Component component = stack.pollLast();
            if (component == null || component.getType() == selectedType)
                nextComponent = component;
            else {
                if (component instanceof Composite) {
                    Composite composite = (Composite) component;
                    for (Component comp : composite) {
                        stack.addLast(comp);
                    }
                }
            }

//            Component component = stack.pollLast();
//            while (component != null || component.getType()!=selectedType){
//                if (component instanceof Composite) {
//                    Composite composite = (Composite) component;
//                    for (Component comp : composite) {
//                        stack.addLast(comp);
//                    }
//
//                }
//                component = stack.pollLast();
//            }

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

            Component component = stack.pollLast();
            if (component != null)
                if (component instanceof Composite) {
                    Composite composite = (Composite) component;
                    for (Component comp : composite) {
                        stack.addLast(comp);
                    }

                }


            return component;
        }
    }

    private class IteratorComposite2 implements Iterator<Component> {

        Type selectedType;
        Deque<Iterator<Component>> stack = new ArrayDeque<>();

        public IteratorComposite2(Type type) {
            selectedType = type;
            stack.addLast(components.iterator());
        }

        @Override
        public boolean hasNext() {
            log.debug("1@!!!!");
            if (stack.isEmpty())
                return false;
            log.debug("2@!!!!");
            if (stack.peekLast().hasNext())
                return true;
            log.debug("3@!!!!");
            stack.pollLast();
            log.debug("4@!!!!");
            return hasNext();
        }

        @Override
        public Component next() {
            log.debug("\\start next size {}", stack.size());
            if (!hasNext()) {
                log.debug("enter !hasNext");
                return null;
            }
            log.debug("ok - 1");
            Component component = stack.peekLast().next();
            hasNext();
            log.debug("ok - 2");
            if (component instanceof Composite) {
                log.debug("enter inst");
                stack.addLast(((Composite) component).iterator());
            }
            if (component.getType() == selectedType) {
                log.debug("enter component.getType() == selectedType {}", component.getType() == selectedType);
                return component;
            } else {
                log.debug("enter next//");
                return next();
            }


        }
    }

    private class ComponentIterator implements Iterator<Component> {

        private static final int FIRST_ELEMENT = 0;
        private Deque<Iterator<Component>> stack;
        private Type typeSelected;
        private Component nextComponent;

        public ComponentIterator(Type type) {
            stack = new ArrayDeque<>();
            typeSelected = type;
            Composite composite = Composite.this;
            fillStackOfFirstIterators(composite);
            if (stack.peek().hasNext())
                nextComponent = stack.peek().next();
            else throw new IllegalArgumentException("Composite is empty!");
        }

        private void fillStackOfFirstIterators(Composite composite) {
            if (getSubCompositeType(composite.getType()) == typeSelected) {
                stack.addLast(composite.iterator());
            } else if (composite.containsSubtype(typeSelected)) {
                Iterator<Component> iterator = composite.iterator();
                if (iterator.hasNext()) iterator.next();
                else throw new IllegalArgumentException("Composite is empty!");
                stack.addLast(iterator);
                fillStackOfFirstIterators((Composite) composite.get(FIRST_ELEMENT));
            }
        }

        @Override
        public boolean hasNext() {
            return nextComponent != null;
            // return innerHasNext(false);
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
            Component value = this.nextComponent;
            this.nextComponent = findNextComponent();
            return value;
        }

        private Component findNextComponent() {
            Component value;
            if (stack.size() == 0) value = null;
            else if (!stack.peek().hasNext()) {
                stack.pollLast();
                value = findNextComponent();
            } else {
                value = stack.peek().next();
                if (!(value.getType() == typeSelected))
                    if (value instanceof Composite)
                        stack.addLast(((Composite) value).iterator());
                findNextComponent();
            }
            return value;
        }
    }


}
