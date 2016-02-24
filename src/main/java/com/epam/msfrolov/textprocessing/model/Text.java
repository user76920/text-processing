package com.epam.msfrolov.textprocessing.model;

import java.util.Iterator;

public class Text extends Composite{

    private String pattern = "[\n]+";

    @Override
    public Unit create(String str) {
        Unit composite = new Paragraph();
        composite.setValue(str);
        return composite;

    }

    public String getPattern(){
        return pattern;
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
    }
}
