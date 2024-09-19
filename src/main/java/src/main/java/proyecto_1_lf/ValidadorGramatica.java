package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidadorGramatica {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("GRAMATICA.txt"));
        String readLine = "";

        Map<String, List<String>> sections = new HashMap<>();
        List<String> currentSection = null;
        String currentSectionName = "";

        // Read the file and classify each line based on the section
        while ((readLine = reader.readLine()) != null) {

            // Skip empty lines
            if (readLine.trim().isEmpty()) {
                continue;
            }

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
                currentSection.add(readLine.trim());
            }
        }

        reader.close();

        // Now, pass each section to its validation method
        ValidacionDeExpresiones expresiones = new ValidacionDeExpresiones();

        boolean validacion = true;

        if (sections.containsKey("SETS")) {
            System.out.println("Debug: Validating SETS section.");
            validacion = expresiones.verificarSet(sections.get("SETS"));
        }

        if (validacion && sections.containsKey("TOKENS")) {
            System.out.println("Debug: Validating TOKENS section.");
            validacion = expresiones.verificarTokens(sections.get("TOKENS"));
        }

        if (validacion && sections.containsKey("ACTIONS")) {
            System.out.println("Debug: Validating ACTIONS section.");
            validacion = expresiones.verificarActions(sections.get("ACTIONS"));
        }

        if (validacion && sections.containsKey("ERROR")) {
            System.out.println("Debug: Validating ERROR section.");
            validacion = expresiones.verificarError(sections.get("ERROR"));
        }

        if (validacion) {
            System.out.println("All sections passed validation.");
        } else {
            System.out.println("Validation failed.");
        }
    }
}
