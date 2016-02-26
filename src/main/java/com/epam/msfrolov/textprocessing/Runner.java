package com.epam.msfrolov.textprocessing;


import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Type;
import com.epam.msfrolov.textprocessing.util.Parser;
import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
       /*Exception in thread "main" java.util.regex.PatternSyntaxException:
        Look-behind group does not have an obvious maximum length near index 40
        */


        String testS =
                "       Это 1 провероч 4565 ^$^^ %%^^ 5 ный, текст для, тестирования... работы программы." +
                        " Это 2 п   роверочный текст для тестирования работы программы.\n" +
                        "   Это 3 проверочный текст для тестирования работы программы.\n";

        String saf = "";
        /*
        String REGEX_SENTENCES_PART1 = "((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s.,!?~#$%^&*()=+'\":;№@`]+))";
        String REGEX_SENTENCES_PART2 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_.,!?~#$%^&*()=+'\":;№@`]+)(?<=[\\s]+))";
        String REGEX_SENTENCES_PART3 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[.,!?~#$%^&*()=+'\":;№@`]+))\"";
*/
        /*+++++++
        String REGEX_SENTENCES_PART1 = "((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s\t\n ,.!?~#$%^&*()=+'\":;№@`]+))";
        String REGEX_SENTENCES_PART2 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_,.!?~#$%^&*()=+'\":;№@`]+)(?<=[\\s\t\n]+))";
        String REGEX_SENTENCES_PART3 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_\\s\t\n]+)(?<=[,.!?~#$%^&*()=+'\":;№@`]+))\"";
        String REGEX_SENTENCES_WORD = "([a-zA-Zа-яА-ЯЁё0-9-_]+)";
        String REGEX_SENTENCES_WHITESPACE = "([\\s\t\n]+)";
        String REGEX_SENTENCES_OTHER = "([,.!?~#$%^&*()=+'\":;№@`]+)";
*/
        /*Pattern.UNICODE_CASE;
        Pattern.UNICODE_CHARACTER_CLASS;*/

       // String fffff = REGEX_SENTENCES_PART1 + "|" + REGEX_SENTENCES_PART2 + "|" + REGEX_SENTENCES_PART3;
        //String fffff = "()"
/*
        Pattern pattern = Pattern.compile("((?<=[\\w]+)(?=[\\W]+))|((?<=[\\W]+)(?=[\\w]+))",Pattern.UNICODE_CHARACTER_CLASS);
        Pattern pattern1 = Pattern.compile("[\\w]+]",Pattern.UNICODE_CASE);
        //Pattern pattern2 = Pattern.compile("[\\s]+",Pattern.UNICODE_CHARACTER_CLASS);
        Pattern pattern3 = Pattern.compile("[\\W]+",Pattern.UNICODE_CASE);
*/
        /*
        String[] split = testS
               // .split("((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s.,]))|((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[\\s.,]))|((?=[a-zA-Zа-яА-ЯЁё0-9-_.,?!]+)(?<=[\\s]))");
                .split(fffff);
                */
        String REGEX_SENTENCES_PART1 = "((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s\t\n ,.!?~#$%^&*()=+'\":;№@`]+))";
        String REGEX_SENTENCES_PART2 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[\\s\t\n ,.!?~#$%^&*()=+'\":;№@`]+))";
       // String REGEX_SENTENCES_PART3 = "((?=[a-zA-Zа-яА-ЯЁё0-9-_\\s\t\n]+)(?<=[,.!?~#$%^&*()=+'\":;№@`]+))\"";
        String REGEX_SENTENCES_WORD = "([a-zA-Zа-яА-ЯЁё0-9-_]+)";
       // String REGEX_SENTENCES_WHITESPACE = "([\\s\t\n]+)";
        String REGEX_SENTENCES_OTHER = "([\\s\t\n,.!?~#$%^&*()=+'\":;№@`]+)";

        String[] split = testS.split(REGEX_SENTENCES_PART1 + "|" + REGEX_SENTENCES_PART2);
        for (String x : split) {
            System.out.println("///////////////////////////////////////////////////");
            System.out.println(x);

            if (x.matches(REGEX_SENTENCES_WORD)) System.out.println("REGEX_SENTENCES_WORD");
           // if (x.matches(REGEX_SENTENCES_WHITESPACE)) System.out.println("REGEX_SENTENCES_WHITESPACE");
            if (x.matches(REGEX_SENTENCES_OTHER)) System.out.println("REGEX_SENTENCES_OTHER");

            /*
            if (pattern1.matcher(x).find()) System.out.println("REGEX_SENTENCES_WORD");
            //if (pattern2.matcher(x).find()) System.out.println("REGEX_SENTENCES_WHITESPACE");
            if (pattern3.matcher(x).find()) System.out.println("REGEX_SENTENCES_OTHER");
*/
            /*String[] split1 = x.split("(?>=[a-zA-Zа-яА-ЯЁё_-]+)");
            for (String sp:split1) {
                System.out.println("--------------------------------------------------");
                System.out.println(sp);
                System.out.println("--------------------------------------------------");
            */
            saf = saf.concat(x);
            /*
            }
*/
            System.out.println("///////////////////////////////////////////////////");
        }
        System.out.println("СРАВНЕНИЕ" + saf.equals(testS));

/*
 String testS =
                "Это 1 проверочный текст для тестирования работы программы.\n" +
                        "Это 2 проверочный текст для тестирования работы программы.\n" +
                        "Это 3 проверочный текст для тестирования работы программы.\n" +
                        "Это 4 проверочный текст для тестирования работы программы.\n" +
                        "   Это 5 проверочный текст для тестирования работы программы.\n" +
                        "   Это 6 проверочный текст для тестирования работы программы.\n" +
                        " Это 7 проверочный текст для тестирования работы программы.\n" +
                        " Это 8 проверочный текст для тестирования работы программы.\n";
        Component component = Parser.parse(testS);
        StringBuilder stringBuilder = component.toPlainString(new StringBuilder());
        String componentToString = stringBuilder.toString();
        System.out.println(componentToString);
*/
    }
}
