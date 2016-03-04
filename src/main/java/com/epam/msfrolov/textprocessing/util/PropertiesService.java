package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesService {

    public static <T> Map<T, T> get(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesService.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new FileReadException(e);
        }
        Map<T, T> mapProperties = new HashMap<>();
        for (Object key : properties.keySet()) {
            String value = properties.getProperty((String) key);
            mapProperties.put((T) key, (T) value);
        }
        return mapProperties;
    }
}
