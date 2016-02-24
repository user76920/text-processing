package com.epam.msfrolov.textprocessing.model;

public class Sentence extends Composite{

    private String pattern = "[\n]+";

    @Override
    public Unit create(String str) {
        Unit composite =  new Word();
        composite.setValue(str);
        return composite;

    }

    public String getPattern() {
        return pattern;
    }

}
