package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;
import static org.junit.Assert.assertEquals;


public class CompositeTest {

    private static final Logger log = LoggerFactory.getLogger(CompositeTest.class.getName());

    @Test
    public void testToPlainString() throws Exception {

        //GIVEN
        String testString = "Слово не воробей, вылетит не поймаешь!";

        //WHEN
        char[] chars = testString.toCharArray();
        Composite composite = createCompositeForTest(chars);
        String testCompositeString = composite.toPlainString(new StringBuilder()).toString();

        //THEN
        assertEquals(testString, testCompositeString);
    }

    private Composite createCompositeForTest(char[] chars) {
        Composite mainComposite = Composite.create();
        for (char aChar : chars) {
            Composite composite = Composite.create();
            mainComposite.add(composite);
            for (int i = 0; i < 4; i++) {
                Composite newCp;
                composite.add(newCp = Composite.create());
                composite = newCp;
            }
            composite.add(Char.create(aChar));
        }
        return mainComposite;
    }

    @Test
    public void testIteratorComponent() throws Exception {
        Composite text = CompositeFactory.getCompositeText();
        Iterator<Component> iterator = text.iterator(WORD);
        while (iterator.hasNext()) {
            Component next = iterator.next();
            log.debug("type: {}| Component: {}", next.getType(), next.toPlainString());
        }
    }

    @Test
    public void testGetNumberOfComponents() throws Exception {
        //GIVEN
        Composite text = CompositeFactory.getCompositeText();

        //WHEN
        int numberOfText = text.getNumberOfComponents(TEXT);
        int numberOfParagraph = text.getNumberOfComponents(PARAGRAPH);
        int numberOfSentence = text.getNumberOfComponents(SENTENCE);
        int numberOfWord = text.getNumberOfComponents(WORD);
        int numberOfWhiteSpace = text.getNumberOfComponents(WHITESPACE);
        int numberOfLetter = text.getNumberOfComponents(LETTER);

        //THEN
        assertEquals(1, numberOfText);
        assertEquals(4, numberOfParagraph);
        assertEquals(17, numberOfSentence);
        assertEquals(169, numberOfWord);
        assertEquals(178, numberOfWhiteSpace);
        assertEquals(790, numberOfLetter);
    }

    @Test
    public void testRemoveAll() throws Exception {
        //GIVEN
        Composite text = CompositeFactory.getCompositeText();

        //WHEN
        int i = text.removeAllComponent(Char.create('о'));

        //THEN
        assertEquals(100, i);

    }
}