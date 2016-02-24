package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.textprocessing.util.Validator.validate;

public  class Composite extends BaseEntity implements Unit {

    private List<Unit> composites = new ArrayList<>();


    @Override
    public char[] getValue() {
        char[] chars = new char[0];
        for (Unit unit : composites)
            ArrayUtil.concat(chars, unit.getValue());
        return chars;
    }

    @Override
    public Iterator iterator() {
        return composites.iterator();
    }

    void add(Unit component) {
        if (component == null) validate();
        composites.add(component);
    }

    public Unit create(String s) {
        return null;
    }
    public String getPattern(){
        return "";
    }

    public void setValue(String value) {
        String[] split = value.split(getPattern());
        for (String s : split) {
            this.add(create(s));
        }

    }
}
