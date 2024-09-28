package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorGramatica {

    // hola
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("GRAMATICA.txt"));
        String readLine = "";

        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher("");


        ValidacionDeExpresiones expresiones = new ValidacionDeExpresiones();

        // Read the file and classify each line based on the section
        while ((readLine = reader.readLine()) != null) {


            if (readLine.trim().isEmpty()) {
                expresiones.lineNumber++;
                continue;
            }


            if (pattern.compile("SETS").matcher(readLine).matches()) {
                
            }
            else if (pattern.compile("TOKENS").matcher(readLine).matches()) {

            }
            else if (pattern.compile("ACTIONS").matcher(readLine).matches()) {

            }
            else if (pattern.compile("ERROR").matcher(readLine).matches()) {

            }
            else{
                validationCamp(readLine,expresiones.lineNumber);
            }


            /*  
            // Determine if we are starting a new section
            if (readLine.trim().startsWith("SETS")) {
                currentSectionName = "SETS";
                currentSection = new ArrayList<>();
                sections.put(currentSectionName, currentSection);
                System.out.println("Debug: Starting to read SETS section.");
            } else if (readLine.trim().startsWith("TOKENS")) {
                currentSectionName = "TOKENS";
                currentSection = new ArrayList<>();
                sections.put(currentSectionName, currentSection);
                System.out.println("Debug: Starting to read TOKENS section.");
            } else if (readLine.trim().startsWith("ACTIONS")) {
                currentSectionName = "ACTIONS";
                currentSection = new ArrayList<>();
                sections.put(currentSectionName, currentSection);
                System.out.println("Debug: Starting to read ACTIONS section.");
            } else if (readLine.trim().startsWith("ERROR")) {
                currentSectionName = "ERROR";
                currentSection = new ArrayList<>();
                sections.put(currentSectionName, currentSection);
                System.out.println("Debug: Starting to read ERROR section.");
            }

            // Add lines to the current section
            if (currentSection != null && !readLine.trim().startsWith("SETS") && !readLine.trim().startsWith("TOKENS")
                    && !readLine.trim().startsWith("ACTIONS") && !readLine.trim().startsWith("ERROR")) {
                currentSection.add(readLine);
            }*/
        }

        /*reader.close();

        // Now, pass each section to its validation method

        boolean validacion = true;

        if (sections.containsKey("SETS")) {
            System.out.println("Debug: Validating SETS section.");
            expresiones.lineNumber++;
            validacion = expresiones.verificarSet(sections.get("SETS"));
        }

        if (validacion && sections.containsKey("TOKENS")) {
            System.out.println("Debug: Validating TOKENS section.");
            expresiones.lineNumber++;
            validacion = expresiones.verificarTokens(sections.get("TOKENS"));
        }

        if (validacion && sections.containsKey("ACTIONS")) {
            System.out.println("Debug: Validating ACTIONS section.");
            expresiones.lineNumber++;
            validacion = expresiones.verificarActions(sections.get("ACTIONS"));
        }

        if (validacion && sections.containsKey("ERROR")) {
            System.out.println("Debug: Validating ERROR section.");
            expresiones.lineNumber++;
            validacion = expresiones.verificarError(sections.get("ERROR"));
        }

        if (validacion) {
            System.out.println("All sections passed validation.");
        } else {
            System.out.println("Validation failed.");
        }*/
    }

    public static void validationCamp(String line, int lineNumber){
        ValidacionDeExpresiones temp = new ValidacionDeExpresiones();
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(line);

        if ((matcher = pattern.compile("SETS").matcher(line)).matches() || matcher.hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column " + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        }
        else if ((pattern = pattern.compile("TOKENS")).matcher(line).hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column " + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        }
        else if ((pattern = pattern.compile("ACTIONS")).matcher(line).hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column " + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        }
        else if ((pattern = pattern.compile("ERROR")).matcher(line).hitEnd()) {
            throw new IllegalStateException("Error in line " + lineNumber + " at column " + temp.getErrorColumn(line, matcher.pattern()) + ": " + line);
        }
    }
}