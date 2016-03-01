package com.epam.msfrolov.textprocessing.factory;

import com.epam.msfrolov.textprocessing.model.Composite;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompositeFactoryTest {

    @Test
    public void testGetRandomSentence() throws Exception {
        Composite composite = CompositeFactory.getRandomSentence();
        String s = composite.toPlainString();
        System.out.println(s);
    }
}