package pl.pp;
import java.io.*;
import java.util.*;

public class mojaTrzynastaAplikacja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputFilePath;
        String outputFilePath;

        while (true) {
            System.out.print("Podaj ścieżkę do pliku wejściowego: ");
            inputFilePath = scanner.nextLine();
            try {
                File inputFile = new File(inputFilePath);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                reader.close();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Plik wejściowy nie istnieje. Proszę podać poprawną ścieżkę.");
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas sprawdzania pliku: " + e.getMessage());
            }
        }


        System.out.print("Podaj ścieżkę do pliku wyjściowego: ");
        outputFilePath = scanner.nextLine();

        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            Map<String, Integer> wordCountMap = new HashMap<>();
            int totalWordCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                totalWordCount += words.length;

                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                    if (word.isEmpty() || word.matches("\\d+")) continue;
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }

            System.out.println("Liczba wszystkich słów: " + totalWordCount);
            writer.write("Nazwa pliku wejściowego: " + inputFilePath + "\n");
            writer.write("Liczba wszystkich słów: " + totalWordCount + "\n");

            System.out.println("Liczba wystąpień każdego słowa:");
            writer.write("Liczba wystąpień każdego słowa:\n");

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
}
