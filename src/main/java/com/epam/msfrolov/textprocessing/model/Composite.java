package com.epam.msfrolov.textprocessing.model;

import java.util.List;

public class Composite extends Component {
    private List<Component> components;

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
