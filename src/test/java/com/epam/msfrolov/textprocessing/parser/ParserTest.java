package com.epam.msfrolov.textprocessing.parser;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static final Logger log = LoggerFactory.getLogger(ParserTest.class);

    @Test
    public void shouldGiveSubComposite() throws Exception {
        assertEquals(PARAGRAPH.toString(), Composite.COMPOSITE_PROPERTIES.get(TEXT.toString()));
        assertEquals(SENTENCE.toString(), Composite.COMPOSITE_PROPERTIES.get(PARAGRAPH.toString()));
    }

    @Test
    public void shouldCheckEqualityInputAndOutputParseString() throws Exception {
        System.out.println(PARAGRAPH.name());
        //GIVEN
        Parser parser = Parser.create();
        Composite testCompositeText = CompositeFactory.getCompositeText();
        String testString = testCompositeText.toPlainString();
        //WHEN
        Composite textComposite = parser.parse(testString);
        //THEN
        assertEquals(testCompositeText, textComposite);

        log.debug("testCompositeText.equals(textComposite) {}", testCompositeText.equals(textComposite));
    }
}














