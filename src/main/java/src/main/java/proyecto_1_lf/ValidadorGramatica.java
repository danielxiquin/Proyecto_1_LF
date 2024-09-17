package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;


public class ValidadorGramatica {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("GRAMATICA.txt"));
        String readLine = "";
        ValidacionDeExpresiones expresiones = new ValidacionDeExpresiones();


        while ((readLine = reader.readLine()) != null) {

            if (readLine.isEmpty()) {
                continue;
            }

            if (readLine.startsWith("SETS")) {
                expresiones.verificarSet(reader);
            } else if (readLine.startsWith("TOKENS")) {
                
            } else if (readLine.startsWith("ACTIONS")) {

            } else if (readLine.startsWith("ERROR")) {

            }

        }

    }

}