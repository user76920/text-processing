package com.epam.msfrolov.textprocessing.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CharTest {

    @Test
    public void testCheckType() throws Exception {
        char[] letterOrNumber = {'J', 'u', 'Й', 'ё', '0'};
        char[] otherSymbol = {'~', ' ', '\n', '&', '.', ',', '?', '!'};

        assertSymbol(letterOrNumber, Type.LETTER_OR_NUMBER);
        assertSymbol(otherSymbol, Type.OTHER_SYMBOL);
    }

    private void assertSymbol(char[] array, Type type) {
        List<Char> list = new ArrayList<>(array.length);
        for (char sb : array) list.add(Char.create(sb));
        for (Char ch : list) {
            assertEquals(type, ch.getType());
        }
    }
}