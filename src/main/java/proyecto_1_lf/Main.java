package src.main.java.proyecto_1_lf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new FileReader("GRAMATICA.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            


        }
        
        System.out.println("Hello world!");


    }
}