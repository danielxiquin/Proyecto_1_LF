package src.main.java.proyecto_1_lf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        
        String hola = "                         SET";
        Pattern p = Pattern.compile("SETS");
        Matcher m = p.matcher(hola);

        System.out.println(m.find());
        System.out.println(m.hitEnd());

        if((m = p.compile("SETS").matcher(hola)).matches() || m.hitEnd()) {
            System.out.println("");
        }
    }
}
