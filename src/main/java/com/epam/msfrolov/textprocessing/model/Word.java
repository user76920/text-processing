package com.epam.msfrolov.textprocessing.model;

public class Word extends Composite {

    private String pattern = "[\n]+";

    public Unit create(String str) {
        Unit composite =  new Letter();
        composite.setValue(str);
        return composite;

    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public void setValue(String value) {
        String[] split = value.split(getPattern());
        for (String s : split)      {
            this.add(create(s));
        }
    }
}
