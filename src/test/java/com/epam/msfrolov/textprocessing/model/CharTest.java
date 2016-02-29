package com.epam.msfrolov.textprocessing.model;

import com.epam.msfrolov.textprocessing.model.Char.CharType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.epam.msfrolov.textprocessing.model.Char.CharType.*;
import static org.junit.Assert.*;

public class CharTest {

    private static Logger LOG = LoggerFactory.getLogger(CharTest.class);

    @Test
    public void testCheckType() throws Exception {

        //ARRANGE
        char[] symbol = {'J', 'u', 'Й', 'ё',};
        char[] digit = {'0', '5', '9'};
        char[] whitespace = {' ', '\n'};
        char[] other = {'~', '&','%','$','#'};
        char[] punctuation = {'.','!','?',',',':','"','\'',';','(',')','[',']','{','}',};

        //ACT
        assertSymbol(symbol, LETTER);
        assertSymbol(digit, DIGIT);
        assertSymbol(whitespace, WHITESPACE);
        assertSymbol(other, OTHER);
        assertSymbol(punctuation, PUNCTUATION);
    }

    private void assertSymbol(char[] array, CharType type) {
        List<Char> list = new ArrayList<>(array.length);
        for (char sb : array) list.add(Char.create(sb));

        //ASSERT
        for (Char ch : list) {
            assertEquals(type, ch.getType());
        }
    }
}