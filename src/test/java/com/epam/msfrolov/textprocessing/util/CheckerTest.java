package com.epam.msfrolov.textprocessing.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckerTest {

    @Test()
    public void testIsNull() {
        assertEquals(false, Checker.isNull(42));

        //GIVEN
        boolean thrown = false;
        //WHEN
        try {
            Checker.isNull(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        //THEN
        assertTrue(thrown);
    }
}