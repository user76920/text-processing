package com.epam.msfrolov.textprocessing.model;

import java.util.Iterator;

public interface Unit extends Iterable {
    char[] getValue();
    void setValue(String s);
    @Override
    Iterator iterator();
}