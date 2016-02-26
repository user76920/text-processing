package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {


    @Test
    public void testGetNextType() throws Exception {
        assertEquals(Type.PARAGRAPH, Parser.getNextType(Type.TEXT));
        assertEquals(Type.SENTENCES, Parser.getNextType(Type.PARAGRAPH));
    }


    @Test
    public void testParse() throws Exception {

    }
}