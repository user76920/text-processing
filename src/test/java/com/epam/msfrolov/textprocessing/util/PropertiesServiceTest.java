package com.epam.msfrolov.textprocessing.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.epam.msfrolov.textprocessing.model.Component.Type.*;
import static org.junit.Assert.assertEquals;

public class PropertiesServiceTest {
    private static final Logger log = LoggerFactory.getLogger(PropertiesServiceTest.class);

    @Test
    public void testGetRegEx() throws Exception {
        Map<String, String> propertiesService = PropertiesService.get("regExType.properties");
        for (Map.Entry<String, String> x : propertiesService.entrySet()) {
            log.debug("KEY {} VALUE {}", x.getKey(), x.getValue());
        }
        assertEquals(propertiesService.get(LETTER.toString()), "([-_])");
        assertEquals(propertiesService.get(PUNCTUATION.toString()), "([.!?,:\"';()[]{}])");
        assertEquals(propertiesService.get(WORD.toString()), "([A-Za-zА-Яа-яЁё0-9-_])");
    }
}