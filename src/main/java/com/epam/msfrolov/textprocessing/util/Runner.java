package com.epam.msfrolov.textprocessing.util;

import com.epam.msfrolov.textprocessing.model.Composite;
import com.epam.msfrolov.textprocessing.model.Text;
import com.epam.msfrolov.textprocessing.model.Unit;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
       /* String s = "Fuck   \n\n\n  the \n\n\n\n\n  system   !";
        Pattern p = Pattern.compile("[\n,' ']+");
        String[] split = s.split("[\n,' ']+");
      //  p.
        //System.out.println(Arrays.toString(split));
        for (String x:split) {
            System.out.println(x);
        }
        */
        String s = "jrgnjerng \nnjirng n nrjgnrrmgnmok\nreng nmrogn  nronogn r nrg no\nrgn  nrio nron gor \nrn ionr oni";

        Text text = new Text();
        text.setValue(s);


        for (Unit unit:text){
        }
    }
}
