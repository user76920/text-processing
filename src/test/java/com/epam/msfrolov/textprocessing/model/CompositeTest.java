package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.SENTENCE;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.WORD;
import static org.junit.Assert.assertEquals;


public class CompositeTest {

    private static final Logger LOG = LoggerFactory.getLogger(CompositeTest.class.getName());

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
    public void wordIteratorShouldAllowIteratedBySentencePart() throws Exception {
        //GIVEN
        Composite testComposite = CompositeFactory.getCompositeText();
        LOG.debug(testComposite.toPlainString());
        String s = testComposite.toPlainString();
        Iterator<Component> iterator = testComposite.iterator(WORD);
        LOG.debug("Launch of iterator");
        StringBuilder stringBuilder = new StringBuilder();
        //WHEN
        while (iterator.hasNext()) {
            String ss = iterator.next().toPlainString();
            LOG.info(ss);
            stringBuilder.append(ss);
        }
        //THEN
        assertEquals(stringBuilder.toString(), s);
        LOG.info("TEST  {}", stringBuilder.toString().equals(s));
    }

    @Test
    public void sentenceIteratorShouldAllowIteratedBySentence() throws Exception {
        //GIVEN
        Composite testComposite = CompositeFactory.getCompositeText();
        LOG.debug(testComposite.toPlainString());
        String s = testComposite.toPlainString();
        Iterator<Component> iterator = testComposite.iterator(SENTENCE);
        LOG.debug("Launch of iterator");
        StringBuilder stringBuilder = new StringBuilder();

        //WHEN
        while (iterator.hasNext()) {
            String ss = iterator.next().toPlainString();
            LOG.info(ss);
            stringBuilder.append(ss);
        }

        //THEN
        assertEquals(stringBuilder.toString(), s);
    }

    @Test
    public void shouldGiveNumberOfChar() throws Exception {
        //GIVEN
        Composite compositeText = CompositeFactory.getCompositeText();

        //WHEN
        int numberOfCharInThisString = compositeText.toPlainString().length();
        int numberOfCharInThatString = compositeText.getNumberOfChar();
        LOG.debug("Number of Char into composition = {} & {} ", numberOfCharInThisString, numberOfCharInThatString);
        int numberOfChar = compositeText.toPlainString().length();

        //THEN
        assertEquals(numberOfCharInThisString, numberOfCharInThatString);
    }

    @Test
    public void testExtractListUniqueWordsAndWordIterator() throws Exception {
        //GIVEN
        Composite randomSentence = CompositeFactory.getCompositeText();

        //WHEN
        Iterator<Component> iterator = randomSentence.iterator(WORD);
        Set<Component> componentSet = new HashSet<>();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            LOG.debug("Type = {}, Component = {}", component.getType(), component);
            LOG.debug("component.getType() == WORD = {}", component.getType() == WORD);
            if (component.getType() == WORD) {
                componentSet.add(component);
            }

        }
        List<Composite> composites = randomSentence.extractListUniqueComposite(WORD);
        for (Composite composite : composites) {
            LOG.debug("type {} value {}", composite.getType().name(), composite.toPlainString());
        }

        //THEN
        LOG.debug("componentSet.size() {} composites.size() {}", componentSet.size(), composites.size());
        assertEquals(componentSet.size(), composites.size());
    }

    @Test
    public void testMethodGetNumberOfComposite() throws Exception {
        //GIVEN
        Composite randomSentence = CompositeFactory.getCompositeText();

        //WHEN
        //WORD
        Iterator<Component> iteratorWord = randomSentence.iterator(WORD);
        int counterWord = 0;
        while (iteratorWord.hasNext()) {
            Component component = iteratorWord.next();
            LOG.debug("Type = {}, Component = {}", component.getType(), component);
            LOG.debug("component.getType() == WORD = {}", component.getType() == WORD);
            if (component.getType() == WORD) {
                counterWord++;
            }
        }
        int numberWord = randomSentence.getNumberOfComposite(WORD);
        //SENTENCE
        Iterator<Component> iteratorSentence = randomSentence.iterator(SENTENCE);
        int counterSentence = 0;
        while (iteratorSentence.hasNext()) {
            Component component = iteratorSentence.next();
            LOG.debug("Type = {}, Component = {}", component.getType(), component);
            LOG.debug("component.getType() == WORD = {}", component.getType() == WORD);
            counterSentence++;

        }
        int numberSentence = randomSentence.getNumberOfComposite(SENTENCE);

        //THEN
        LOG.debug("counterWord {} numberWord {}", counterWord, numberWord);
        assertEquals(counterWord, numberWord);
        LOG.debug("counterSentence {} numberSentence {}", counterSentence, numberSentence);
        assertEquals(counterSentence, numberSentence);
    }
}