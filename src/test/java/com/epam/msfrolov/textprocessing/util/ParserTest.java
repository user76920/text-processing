package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Composite;
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
        String testString = "Наверное, каждый программист знает или хотя бы слышал про регул" +
                "ярные выражения. Ведь в повседневных задачах часто возникает необходимость на" +
                "йти какие-то данные в тексте по какому-то закону, или проверить данные, котор" +
                "ые поступили от пользователя, или каким-нибудь образом модифицировать текст. \n" +
                "\n" +
                "Все эти задачи можно решить и классическим способом разбиения строки на массив " +
                "символов и выполнения с ним каких-нибудь махинаций. Но такой подход не рекоменд" +
                "уется. Код будет тяжело поддерживать. ";

        Composite text = Parser.parse(testString);
        StringBuilder stringBuilder = text.toPlainString(new StringBuilder());
        assertEquals(testString, stringBuilder.toString());

    }
}