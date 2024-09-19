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

    private void logAsciiValues(String line) {
        System.out.print("Debug: ASCII values of line: ");
        for (char c : line.toCharArray()) {
            System.out.print((int) c + " ");
        }
        System.out.println();
    }

    boolean verificarSet(BufferedReader reader) throws Exception {
        // Updated regex to handle both single characters and ranges
        Pattern p = Pattern.compile("\\s*([A-Z_]+)\\s*=\\s*'([A-Za-z0-9_])'\\s*\\.\\.\\s*'([A-Za-z0-9_])'(\\s*\\+\\s*('([A-Za-z0-9_])'|('([A-Za-z0-9_])'\\s*\\.\\.\\s*'([A-Za-z0-9_])')))*\\s*|\\s*([A-Z_]+)\\s*=\\s*CHR\\(\\d+\\)\\s*\\.\\.\\s*CHR\\(\\d+\\)\\s*");

        String line = "";
        int lineNumber = 0; // Track line number

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number
            System.out.println("Debug: Reading line " + lineNumber + ": " + line);
            logAsciiValues(line);  // Log ASCII values for debugging

            // Remove all whitespace (spaces, tabs, etc.)
            String cleanedLine = removeWhitespace(line);
            System.out.println("Debug: Cleaned line: " + cleanedLine);

            // Print the regular expression
            System.out.println("Debug: Regular Expression: " + p.toString());

            if (cleanedLine.isEmpty()) {
                System.out.println("Debug: Skipping empty line.");
                continue;  // Skip empty lines
            }

            Matcher m = p.matcher(cleanedLine);
            boolean isMatching = m.matches();
            System.out.println("Debug: Matching result for line " + lineNumber + ": " + isMatching);  // Log whether the regex matched the cleaned line

            if (!isMatching) {
                // Get the error column and show a more detailed message
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            } else {
                System.out.println("Debug: Line " + lineNumber + " passed validation.");
            }
        }
        return true;
    }
    // Function to remove all whitespace from a line
    private String removeWhitespace(String line) {
        return line.replaceAll("\\s+", "");  // Replace all whitespace (spaces, tabs, etc.) with nothing
    }




    boolean verificarTokens(BufferedReader reader) throws Exception {
        Pattern p = Pattern.compile("\\s+(\\d+)\\s*=\\s*((LETRA\\s*\\(\\s*LETRA\\s*\\|\\s*DIGITO\\s*\\)\\s*\\)\\s*\\{\\s*RESERVADAS\\(\\)\\s*\\})|('(?:[^']*)')|(\"(?:[^\"]*)\")|(DIGITO\\s+DIGITO\\s*\\*)|([A-Za-z0-9*(){}\\[\\]\"'\\\\;,.!@#$%^&+=~`|/_><-]+))");
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
