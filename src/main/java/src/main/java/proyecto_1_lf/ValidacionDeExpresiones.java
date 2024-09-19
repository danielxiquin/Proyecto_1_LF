package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionDeExpresiones {

    final String regexSets = "";

    // Method to get the column number of the first match failure
    private int getErrorColumn(String line, Pattern p) {
        Matcher m = p.matcher(line);
        for (int i = 0; i < line.length(); i++) {
            if (!m.find(i)) {
                return i + 1; // Return the first column with an error (1-based index)
            }
        }
        return -1; // No error found
    }

    boolean verificarSet(BufferedReader reader) throws Exception {
        Pattern p = Pattern.compile("\\s([A-Z]+)\\s*=\\s*('([A-Za-z_0-9])'(\\.\\.'([A-Za-z_0-9])')?)((\\s*\\+\\s*'([A-Za-z_0-9])'(\\.\\.'([A-Za-z_0-9])')?))?|\\s([A-Z]+)\\s=\\s*CHR\\((\\d+)\\)\\.\\.CHR\\((\\d+)\\)");
        String line = "";
        int lineNumber = 0; // Track line number

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number
            Matcher m = p.matcher(line);

            if (!m.matches()) {
                // Get the error column and show a more detailed message
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            }
        }
        return true;
    }

    boolean verificarTokens(BufferedReader reader) throws Exception {
        Pattern p = Pattern.compile("\\s+(\\d+)\\s*=\\s*((LETRA\\s*(\\(\\s*LETRA\\s*\\|\\s*DIGITO\\s*\\)\\s*\\)\\s{\\s*RESERVADAS\\(\\)\\s*\\})|'('|\")'\\s*(CHARSET)\\s*'('|\")'\\s*\\|\\s*'('|\")'\\s*(CHARSET)\\s*'('|\")'|('([A-Za-z0-9*(){}\\[\\]\\'\"\\\\;,.!@#$%^&+=~`|/_><-])')+|(DIGITO)\\s+(DIGITO)\\s+\\*)");
        String line = "";
        int lineNumber = 0; // Track line number

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number
            Matcher m = p.matcher(line);

            if (!m.matches()) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            }
        }
        return true;
    }

    boolean verificarActions(BufferedReader reader) throws Exception {
        Pattern p = Pattern.compile("\\d+\\s*=\\s*'[A-Za-z0-9]+(?:'[A-Za-z0-9])'");
        String line = "";
        int lineNumber = 0; // Track line number

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number
            Matcher m = p.matcher(line);

            if (!m.matches()) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            }
        }
        return true;
    }

    boolean verificarError(BufferedReader reader) throws Exception {
        Pattern p = Pattern.compile("\\s*=\\s*(\\d+)");
        String line = "";
        int lineNumber = 0; // Track line number

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number
            Matcher m = p.matcher(line);

            if (!m.matches()) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            }
        }
        return true;
    }
}
