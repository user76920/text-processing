package com.epam.msfrolov.textprocessing.factory;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.parser.Parser;
import com.epam.msfrolov.textprocessing.util.TextReader;

public class CompositeFactory {
    public static Composite getCompositeText() {
        Parser parser = Parser.create();
        String testString = TextReader.read("text");
        return parser.parse(testString);
    }


    public static Composite getRandomSentence() {
        Parser parser = Parser.create();
        String s = TextReader.getRandomLine("text");
        return parser.parse(s);
    }
}
