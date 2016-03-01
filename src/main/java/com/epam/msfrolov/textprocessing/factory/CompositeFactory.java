package com.epam.msfrolov.textprocessing.factory;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.parser.Parser;
import com.epam.msfrolov.textprocessing.util.TextReader;

public class CompositeFactory {
    public static Composite createCompositeText() {
        Parser parser = Parser.create();
        String testString = TextReader.read("text");
        Composite text = parser.parse(testString);
        return text;
    }
}
