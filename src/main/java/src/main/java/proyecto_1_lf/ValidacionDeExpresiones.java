package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionDeExpresiones {

    final String regexSets = "";

    boolean verificarSet(BufferedReader reader) throws Exception{
        //Colocar las expresion para validar SET
        Pattern p = Pattern.compile("\\s([A-Z]+)\\s*=\\s*('([A-Za-z_0-9])'(\\.\\.'([A-Za-z_0-9])')?)((\\s*\\+\\s*'([A-Za-z_0-9])'(\\.\\.'([A-Za-z_0-9])')?))?|\\s([A-Z]+)\\s=\\s*CHR\\((\\d+)\\)\\.\\.CHR\\((\\d+)\\)");
        String line = "";

        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (!m.matches()) {
                System.out.println("Fallo en la linea: " + line);
                return false;
            }
        }
        return true;
    }

    boolean verificarTokens(BufferedReader reader) throws Exception{
        //Colocar las expresion para validar TOKENS
        Pattern p = Pattern.compile("\\s+(\\d+)\\s*=\\s*((LETRA\\s*(\\(\\s*LETRA\\s*\\|\\s*DIGITO\\s*\\)\\s*\\)\\s{\\s*RESERVADAS\\(\\)\\s*\\})|'('|\")'\\s*(CHARSET)\\s*'('|\")'\\s*\\|\\s*'('|\")'\\s*(CHARSET)\\s*'('|\")'|('([A-Za-z0-9*(){}\\[\\]\\'\"\\\\;,.!@#$%^&+=~`|/_><-])')+|(DIGITO)\\s+(DIGITO)\\s+\\*)");
        String line = "";

        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (!m.matches()) {
                System.out.println("Fallo en la linea: " + line);
                return false;
            }
        }
        return true;
    }
    boolean verificarActions(BufferedReader reader)throws Exception{
        //Colocar las expresion para validar ACTIONS
        Pattern p = Pattern.compile("\\d+\\s*=\\s*'[A-Za-z0-9]+(?:'[A-Za-z0-9])'");
        String line = "";

        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (!m.matches()) {
                System.out.println("Fallo en la linea: " + line);
                return false;
            }
        }
        return true;
    }

    boolean verificarError(BufferedReader reader)throws Exception{
        //Colocar las expresion para validar ERROR
        Pattern p = Pattern.compile("\\s*=\\s*(\\d+)");
        String line = "";

        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (!m.matches()) {
                System.out.println("Fallo en la linea: " + line);
                return false;
            }
        }
        return true;
    }

}
