package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeServiceTest {

    @Test
    public void testRemoveInTextAllWordsBeginningConsonantLetterWithLength() throws Exception {
        //GIVEN
        Composite text = CompositeFactory.getCompositeText();
        //WHEN
        int testCounter = CompositeService.removeInTextAllWordsBeginningConsonantLetterWithLength(5, text);
        //THEN
        assertEquals(16, testCounter);
    }

    @Test
    public void testreplaceInTextWordWithSpecifiedLengthSubstring() throws Exception {
        //GIVEN
        Composite text = CompositeFactory.getCompositeText();
        //WHEN
        int testCounter = CompositeService.replaceInTextWordWithSpecifiedLengthSubstring(5, "*Java*", text);
        //THEN
        assertEquals(21, testCounter);
    }


}