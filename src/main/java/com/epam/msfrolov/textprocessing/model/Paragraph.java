package com.epam.msfrolov.textprocessing.model;

public class Paragraph extends Composite {

    private String pattern = "[\n]+";

    @Override
    public Unit create(String str) {
        Unit composite = new Sentence();
        composite.setValue(str);
        return composite;

    }

    public String getPattern() {
        return pattern;
    }

}
