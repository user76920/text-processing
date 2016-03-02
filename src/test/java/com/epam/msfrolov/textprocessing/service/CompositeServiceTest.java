package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.ComponentFactory;

import java.util.List;

import static org.junit.Assert.*;

public class CompositeServiceTest {

    private static Logger LOG = LoggerFactory.getLogger(CompositeServiceTest.class.getName());

    @Test
    public void shouldGiveNumberOfChar() throws Exception {

        Composite compositeText = CompositeFactory.getCompositeText();

        int numberOfCharInText = CompositeService.getNumberOfChar(compositeText);
        LOG.debug("Number of Char classes into composition = {}", String.valueOf(numberOfCharInText));
        boolean test = false;
        if (numberOfCharInText > 0) {
            test = true;
        }

        assertTrue(test);
    }

    @Test
    public void testExtractListUniqueWords() throws Exception {
        Composite randomSentence = CompositeFactory.getCompositeText();
        List<Composite> composites = CompositeService.extractListUniqueWords(randomSentence);
        for (Composite composite:composites) {
            LOG.debug("type {} value {}",composite.getType().name(),composite.toPlainString());
        }
    }
}