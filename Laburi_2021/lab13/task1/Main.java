package lab13.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static void readAndPrintLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("Eroare citire de la stdin");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Eroare inchidere BufferedReader");
            }
        }
    }

    public static void main(String[] args) {
        readAndPrintLine();
    }
}
