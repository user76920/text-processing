package com.epam.msfrolov.textprocessing;


import com.epam.msfrolov.textprocessing.model.Component;
import com.epam.msfrolov.textprocessing.model.Type;
import com.epam.msfrolov.textprocessing.util.Parser;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
       /*Exception in thread "main" java.util.regex.PatternSyntaxException:
        Look-behind group does not have an obvious maximum length near index 40
        */


        String testS =
                "   Это 1 провероч 4565 ^$^^ %%^^ 5 ный, текст для, тестирования... работы программы." +
                        " Это 2 проверочный текст для тестирования работы программы.\n" +
                        "   Это 3 проверочный текст для тестирования работы программы.\n";

        String saf = "";
        String[] split = testS
               // .split("((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s.,]))|((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[\\s.,]))|((?=[a-zA-Zа-яА-ЯЁё0-9-_.,?!]+)(?<=[\\s]))");
                .split("((?<=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?=[\\s.,!?~#$%^&*()=+'\":;№@`]+))|((?=[a-zA-Zа-яА-ЯЁё0-9-_.,!?~#$%^&*()=+'\":;№@`]+)(?<=[\\s]+))|((?=[a-zA-Zа-яА-ЯЁё0-9-_]+)(?<=[.,!?~#$%^&*()=+'\":;№@`]+))");
        for (String x : split) {
            System.out.println("///////////////////////////////////////////////////");
            System.out.println(x);
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
