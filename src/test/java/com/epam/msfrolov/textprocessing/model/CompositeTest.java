package com.epam.msfrolov.textprocessing.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeTest {

    @Test
    public void testToPlainString() throws Exception {

        //GIVEN
        String testString = "Слово не воробей, вылетит не поймаешь!";

        //WHEN
        char[] chars = testString.toCharArray();
        Composite composite = createCompositeForTest(chars);
        String testCompositeString = composite.toPlainString(new StringBuilder()).toString();

        //THEN
        assertEquals(testString, testCompositeString);
    }

    private Composite createCompositeForTest(char[] chars) {
        Composite mainComposite = Composite.create();
        for (char aChar : chars) {
            Composite composite = Composite.create();
            mainComposite.add(composite);
            for (int i = 0; i < 4; i++) {
                Composite newCp;
                composite.add(newCp = Composite.create());
                composite = newCp;
            }
            composite.add(Char.create(aChar));
        }
        return mainComposite;
    }
}