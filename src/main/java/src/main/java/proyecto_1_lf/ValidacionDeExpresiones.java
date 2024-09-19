package src.main.java.proyecto_1_lf;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionDeExpresiones {

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

    boolean verificarSet(List<String> lines) {
        System.out.println("Debug: Verifying SETS section.");

        Pattern p = Pattern.compile("\\s*([A-Z_]+)\\s*=\\s*'([A-Za-z0-9_])'\\s*\\.\\.\\s*'([A-Za-z0-9_])'(\\s*\\+\\s*('([A-Za-z0-9_])'|('([A-Za-z0-9_])'\\s*\\.\\.\\s*'([A-Za-z0-9_])')))*\\s*|\\s*([A-Z_]+)\\s*=\\s*CHR\\(\\d+\\)\\s*\\.\\.\\s*CHR\\(\\d+\\)\\s*");

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            System.out.println("Debug: Reading line " + lineNumber + ": " + line);
            logAsciiValues(line);

            // Clean the line and check against the regular expression
            String cleanedLine = removeWhitespace(line);
            System.out.println("Debug: Cleaned line: " + cleanedLine);

            Matcher m = p.matcher(cleanedLine);
            boolean isMatching = m.matches();
            System.out.println("Debug: Matching result for line " + lineNumber + ": " + isMatching);

            if (!isMatching) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            } else {
                System.out.println("Debug: Line " + lineNumber + " passed validation.");
            }
        }
        return true;
    }


    boolean verificarTokens(List<String> lines) {
        System.out.println("Debug: Verifying TOKENS section.");

        // Updated regular expression for TOKEN lines, including support for parentheses and functions
        Pattern p = Pattern.compile("\\s*TOKEN\\s+(\\d+)\\s*=\\s*([A-Za-z0-9_'\"()*+?|\\s-]+|'([^']|'')*'|\"[^\"]*\"|\\([A-Za-z0-9_'\"\\s|*+?]+\\)|\\{\\s*[A-Za-z0-9_]+\\(\\)\\s*}|\\(\\s*[A-Za-z0-9_'\"\\s|*+?]+\\s*\\)\\s*)");

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            System.out.println("Debug: Reading line " + lineNumber + ": " + line);

            Matcher m = p.matcher(line);
            boolean isMatching = m.matches();
            System.out.println("Debug: Matching result for line " + lineNumber + ": " + isMatching);

            if (!isMatching) {
                System.out.println("Error in line " + lineNumber + ": " + line);
                return false;
            } else {
                System.out.println("Debug: Line " + lineNumber + " passed validation.");
            }
        }
        return true;
    }





    boolean verificarActions(List<String> lines) {
        System.out.println("Debug: Verifying ACTIONS section.");

        Pattern p = Pattern.compile("(\\d+\\s*=\\s*'[A-Za-z0-9]+(?:'[A-Za-z0-9])'|RESERVADAS\\(\\))");

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            System.out.println("Debug: Reading line " + lineNumber + ": " + line);

            Matcher m = p.matcher(line);
            boolean isMatching = m.matches();
            System.out.println("Debug: Matching result for line " + lineNumber + ": " + isMatching);

            if (!isMatching) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            } else {
                System.out.println("Debug: Line " + lineNumber + " passed validation.");
            }
        }
        return true;
    }

    boolean verificarError(List<String> lines) {
        System.out.println("Debug: Verifying ERROR section.");

        Pattern p = Pattern.compile("\\s*=\\s*(\\d+)");

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            System.out.println("Debug: Reading line " + lineNumber + ": " + line);

            Matcher m = p.matcher(line);
            boolean isMatching = m.matches();
            System.out.println("Debug: Matching result for line " + lineNumber + ": " + isMatching);

            if (!isMatching) {
                int errorColumn = getErrorColumn(line, p);
                System.out.println("Error in line " + lineNumber + " at column " + errorColumn + ": " + line);
                return false;
            } else {
                System.out.println("Debug: Line " + lineNumber + " passed validation.");
            }
        }
        return true;
    }

    private String removeWhitespace(String line) {
        return line.replaceAll("\\s+", "");  // Replace all whitespace (spaces, tabs, etc.) with nothing
    }
}
