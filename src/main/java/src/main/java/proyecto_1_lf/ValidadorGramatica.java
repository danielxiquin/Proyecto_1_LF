package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorGramatica {

    // hola
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("prueba_1-1.txt"));
        BufferedReader temp;
        String readLine = "";

        Pattern pattern = Pattern.compile("");

        ValidacionDeExpresiones expresiones = new ValidacionDeExpresiones();
        System.out.println(reader.lines());
        String currentName = "";

        // Read the file and classify each line based on the section
        while ((readLine = reader.readLine()) != null) {
            if (readLine.trim().isEmpty()) {
                expresiones.lineNumber++;
                continue;
            }
            expresiones.lineNumber++;

            if (pattern.compile("ERROR").matcher(readLine).find() || currentName.equals("ERROR")) {
                currentName = "ERROR";
                expresiones.verificarError(readLine);
            } else if (pattern.compile("ACTIONS").matcher(readLine).find() || currentName.equals("ACTIONS")) {
                currentName = "ACTIONS";
                expresiones.verificarActions(reader,readLine);

                
            } else if (pattern.compile("TOKENS").matcher(readLine).find() || currentName.equals("TOKENS")) {
                currentName = "TOKENS";
                if (readLine.equals("TOKENS")) {
                    continue;
                }
                expresiones.verificarTokens(readLine);
            } else if (pattern.compile("SETS").matcher(readLine).find() || currentName.equals("SETS")) {
                currentName = "SETS";
                if (readLine.equals("SETS")) {
                    continue;
                }
                expresiones.verificarSet(readLine);
            } else {
                validationCamp(readLine, expresiones.lineNumber);
            }
        }
        reader.close();
    }

    public static void validationCamp(String line, int lineNumber) {
        ValidacionDeExpresiones temp = new ValidacionDeExpresiones();
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(line);

        if ((matcher = pattern.compile("SETS").matcher(line)).matches() || matcher.hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column "
                    + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        } else if ((matcher = pattern.compile("TOKENS").matcher(line)).matches() || matcher.hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column "
                    + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        } else if ((matcher = pattern.compile("ACTIONS").matcher(line)).matches() || matcher.hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column "
                    + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        } else if ((matcher = pattern.compile("ERROR").matcher(line)).matches() || matcher.hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column "
                    + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        }
    }
}