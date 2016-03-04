package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static com.epam.msfrolov.textprocessing.model.Composite.COMPARE_SUBCOMPONENT;
import static com.epam.msfrolov.textprocessing.model.Composite.CompositeType.WORD;

public class CompositeService {

    private static final int NUMBER_FIRST_ELEMENT = 0;

    private static Logger LOG = LoggerFactory.getLogger(CompositeService.class.getName());

//    6. Напечатать слова текста в алфавитном порядке по первой букве. Слова, на-
//    чинающиеся с новой буквы, печатать с красной строки.
    public static void printWordsFromTextInSpecialOrder(Composite text){
        List<String> wordList = text.extractListString(WORD, false);
        Collections.sort(wordList);

        char c = wordList.get(NUMBER_FIRST_ELEMENT);
        for (Component word:wordList) {
            
        }
    }
}
