package com.epam.msfrolov.textprocessing.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandlerTest {

    @Test
    public void testIsNull() throws Exception {
        assertEquals(false, Handler.isNull(new Integer(42)));
    }
}