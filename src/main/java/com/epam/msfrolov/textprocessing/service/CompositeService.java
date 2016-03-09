package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.textprocessing.model.Component.Type.SENTENCE;
import static com.epam.msfrolov.textprocessing.model.Component.Type.WORD;


public class CompositeService {

    private static Logger log = LoggerFactory.getLogger(CompositeService.class.getName());

    //12. Из текста удалить все слова заданной длины, начинающиеся на согласную букву.
    public static int removeInTextAllWordsBeginningConsonantLetterWithLength(int wordLength, Composite text) {
        final int NUMBER_FIRST_ELEMENT = 0;
        Iterator<Component> iterator = text.iterator(WORD);
        List<Component> components = new ArrayList<>();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            Composite composite = (Composite) component;
            if (composite.size() != wordLength)
                continue;
            Char firstLetter = (Char) composite.get(NUMBER_FIRST_ELEMENT);
            if (firstLetter.isConsonant())
                components.add(component);
        }
        int counter = 0;
        for (Component component : components) {
            log.debug("component: {}", component.toPlainString());
            counter += text.removeAllComponent(component);
        }
        log.debug("Text: {}", text.toPlainString());
        return counter;
    }

    //16. В некотором предложении текста слова заданной длины заменить указанной подстрокой,
    // длина которой может не совпадать с длиной слова.
    public static int replaceInTextWordWithSpecifiedLengthSubstring(int wordLength, String substring, Composite text) {
        int counter = 0;
        Parser parser = Parser.create();

        Composite substringComposite = parser.parse(substring, SENTENCE);

        Iterator<Component> iterator = text.iterator(WORD);
        while (iterator.hasNext()) {
            Component component = iterator.next();
            Composite composite = (Composite) component;
            if (composite.size() != wordLength) continue;
            composite.replaceComposite(substringComposite);
            counter++;
        }
        log.debug("Text: {}", text.toPlainString());
        return counter;
    }


}

