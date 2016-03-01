package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Composite;

public class CompositeService {
    public static int getNumberOfChar(Composite composite) {
        return getNumberOfChar(composite, 0);
    }

    private static int getNumberOfChar(Composite composite, int i) {
        for (Component subComponent : composite) {
            if (subComponent instanceof Char) {
                i++;
            } else {
                i = getNumberOfChar((Composite) subComponent, i);
            }
        }
        return i;
    }
}
