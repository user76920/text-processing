package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.LanguageUnit;

public class Validator {
    public static boolean validate() {
        throw new IllegalArgumentException();
        //return false;
    }
    public static boolean isLanguageUnit(Object o){
        return  (o instanceof LanguageUnit);
    }
    public static boolean isComposite(Object o){
        return  (o instanceof Composite);
    }
}
