package com.epam.msfrolov.textprocessing.parser;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.parser.Parser;
import com.epam.msfrolov.textprocessing.util.TextReader;
import org.junit.Test;

import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.*;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void shouldGiveSubClass() throws Exception {
        Parser parser = Parser.create();
        assertEquals(PARAGRAPH, parser.getSubType(TEXT));
        assertEquals(SENTENCE, parser.getSubType(PARAGRAPH));
    }

    @Test
    public void shouldCheckEqualityInputAndOutputParseString() throws Exception {
        //GIVEN
        Parser parser = Parser.create();
        String testString = TextReader.read("text");
        //WHEN
        Composite text = parser.parse(testString);
        //THEN
        assertEquals(testString, text.toPlainString());
    }



    @Test
    public void test1() throws Exception {
        Parser parser = Parser.create();
        String testString = TextReader.read("text");
        String[] strings = testString.split("(?<=[\\n])");

        for (String s:strings) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(s);
            System.out.println("---------------------------------------------------------------------");
        }

    }
}