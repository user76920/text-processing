package com.epam.msfrolov.textprocessing.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharTest {

    @Test
    public void testSetType() throws Exception {
        Char ch1 = Char.newChar('J');
        Char ch2 = Char.newChar('u');
        Char ch3 = Char.newChar('Й');
        Char ch4 = Char.newChar('ё');
        Char ch5 = Char.newChar('0');
        Char ch6 = Char.newChar('~');
        Char ch7 = Char.newChar(' ');
        Char ch8 = Char.newChar('\n');
        Char ch9 = Char.newChar('&');
        Char ch10 = Char.newChar('.');
        Char ch11 = Char.newChar(',');
        Char ch12 = Char.newChar('?');
        Char ch13 = Char.newChar('!');
        assertEquals(Type.LETTER_OR_NUMBER, ch1.getType());
        assertEquals(Type.LETTER_OR_NUMBER, ch2.getType());
        assertEquals(Type.LETTER_OR_NUMBER, ch3.getType());
        assertEquals(Type.LETTER_OR_NUMBER, ch4.getType());
        assertEquals(Type.LETTER_OR_NUMBER, ch5.getType());
        assertEquals(Type.OTHER_SYMBOL, ch6.getType());
        assertEquals(Type.OTHER_SYMBOL, ch7.getType());
        assertEquals(Type.OTHER_SYMBOL, ch8.getType());
        assertEquals(Type.OTHER_SYMBOL, ch9.getType());
        assertEquals(Type.OTHER_SYMBOL, ch10.getType());
        assertEquals(Type.OTHER_SYMBOL, ch11.getType());
        assertEquals(Type.OTHER_SYMBOL, ch12.getType());
        assertEquals(Type.OTHER_SYMBOL, ch13.getType());

    }
}