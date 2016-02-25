package com.epam.msfrolov.textprocessing.model;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {
    private List<Component> components;

    private Composite() {
        this.components = new ArrayList<>();
    }

    public static Composite create(){
        return new Composite();
    }

    public void add(Component cp){
        components.add(cp);
    }

    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        for (Component cp:components) {
            cp.toPlainString(sb);
        }
        return sb;
    }
}
