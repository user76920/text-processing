package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.parser.Parser;
import org.junit.Test;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;
import static org.junit.Assert.*;

public class ParserTest {


    @Test
    public void testGetNextType() throws Exception {
        assertEquals(PARAGRAPH, Parser.getTypeHeir(TEXT));
        assertEquals(SENTENCE, Parser.getTypeHeir(PARAGRAPH));
    }


    @Test
    public void testParse() throws Exception {
        String testString = TextReader.read("text");

        Composite text = Parser.parse(testString);
        assertEquals(testString, text.toPlainString());

    }
}