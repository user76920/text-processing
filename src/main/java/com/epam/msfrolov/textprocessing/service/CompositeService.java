package com.epam.msfrolov.textprocessing.service;

import com.epam.msfrolov.textprocessing.model.Char;
import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CompositeService {

    private static Logger LOG = LoggerFactory.getLogger(CompositeService.class.getName());

    public static int getNumberOfChar(Composite composite) {
        return getNumberOfChar(composite, 0);
    }

    private static int getNumberOfChar(Composite composite, int i) {
        for (Component subComponent : composite) {
            if (subComponent instanceof Char) {
                i++;
            } else {
                i = getNumberOfChar((Composite) subComponent, i);
            }
        }
        return i;
    }


    private static void extractListUniqueWords(Composite value, List<Composite> compositeList) {
        LOG.debug("extractListUniqueWords level 1 {}");
        for (Component component : value) {
            LOG.debug("extractListUniqueWords level 2{}");
            if (component instanceof Composite) {
                LOG.debug("extractListUniqueWords level 3 Type component {}", ((Composite) component).getType());
                Composite composite = (Composite) component;
                if ((composite.getType() == Composite.CompositeType.WORD)) {
                    LOG.debug("extractListUniqueWords level 4{}");
                    if (!compositeList.contains(composite)) {
                        LOG.debug("extractListUniqueWords level 5{}");
                        LOG.debug("extractListUniqueWords composite {}", composite);
                        compositeList.add(composite);
                    }
                }else extractListUniqueWords(composite, compositeList);
            }
        }
    }

    public static List<Composite> extractListUniqueWords(Composite value) {
        List<Composite> compositeList = new ArrayList<>();
        extractListUniqueWords(value, compositeList);
        return compositeList;
    }
}
