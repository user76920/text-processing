package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.factory.CompositeFactory;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class CompositeServiceTest {

    private static Logger LOG = LoggerFactory.getLogger(CompositeServiceTest.class.getName());

    @Test
    public void shouldGiveNumberOfChar() throws Exception {

        Composite compositeText = CompositeFactory.createCompositeText();
        LOG.debug("*****///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        int numberOfCharInText = CompositeService.getNumberOfChar(compositeText);
        LOG.debug(String.valueOf(numberOfCharInText));
        boolean test = false;
        if (numberOfCharInText>0){
            test = true;
        }
        assertTrue(test);
    }
}