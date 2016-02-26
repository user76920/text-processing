package com.epam.msfrolov.textprocessing.model;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {
    private List<Component> components;

    private Composite(Type type) {
        this.components = new ArrayList<>();
        this.setType(type);
    }

    private Composite() {
        this(Type.TEXT);
    }

    public static Composite create() {
        return new Composite();
    }

    public static Composite create(Type type) {
        return new Composite(type);
    }


    public void add(Component cp) {
        components.add(cp);
    }

    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        for (Component cp : components) {
            cp.toPlainString(sb);
            System.out.println(sb);
        }
        return sb;
    }
}
