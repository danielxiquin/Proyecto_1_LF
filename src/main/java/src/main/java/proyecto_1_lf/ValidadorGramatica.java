package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidadorGramatica {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("GRAMATICA.txt"));
        String readLine = "";
        ValidacionDeExpresiones expresiones = new ValidacionDeExpresiones();
        Boolean validacion = true;

        while ((readLine = reader.readLine()) != null && validacion) {

            if (readLine.isEmpty()) {
                continue;
            }

            if (readLine.startsWith("SETS")) {
                validacion = expresiones.verificarSet(reader);
            } else if (readLine.startsWith("TOKENS")) {
                validacion = expresiones.verificarTokens(reader);
            } else if (readLine.startsWith("ACTIONS")) { 
                validacion = expresiones.verificarActions(reader);
            } else if (readLine.startsWith("ERROR")) {
                validacion = expresiones.verificarError(reader);
            }

        }

        reader.close();

    }

}