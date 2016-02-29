package com.epam.msfrolov.textprocessing.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckerTest {

    @Test
    public void testIsNull() throws Exception {
        assertEquals(false, Checker.isNull(new Integer(42)));
    }
}