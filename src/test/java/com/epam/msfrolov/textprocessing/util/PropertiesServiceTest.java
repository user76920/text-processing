package com.epam.msfrolov.textprocessing.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesServiceTest {

    @Test
    public void testGet() throws Exception {
        for (String x: PropertiesService.get("regExType.properties").values()) {
            System.out.println(x);
        }

    }
}