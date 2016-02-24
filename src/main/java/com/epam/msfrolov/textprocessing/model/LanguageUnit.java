package com.epam.msfrolov.textprocessing.model;

import java.lang.reflect.Array;

public abstract class LanguageUnit extends BaseEntity implements Unit {

    private char[] value;

    @Override
    public char[] getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.toCharArray();

    }
}
