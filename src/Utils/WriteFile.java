package Utils;

import models.EntitaBaseAbstract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Map;

/**
 * La classe {@code WriteFile} fornisce metodi per scrivere dati su file CSV.
 */
public class WriteFile {

    // Costruttore privato per impedire l'istanza della classe
    private WriteFile() {
    }

    /**
     * Scrive una lista di valori in un file CSV.
     * Ogni valore è rappresentato come una stringa.
     *
     * @param filePath Il percorso del file CSV in cui scrivere i dati.
     * @param valori   La lista di valori da scrivere nel file.
     * @throws IOException Se si verifica un errore durante la scrittura del file.
     */
    public static void writeCsv(String filePath, List<String> valori) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Unisce ogni valore della lista con il delimitatore ";"
            String line = String.join(";", valori);
            // Scrive la nuova riga nel file
            writer.write(line);
            writer.newLine();  // Aggiunge una nuova riga
            System.out.println("Nuova riga aggiunta al file CSV.");
        } catch (IOException e) {
            System.out.println("Errore durante l'aggiunta della riga al file: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Scrive una mappa di dati in un file CSV.
     * Ogni valore della mappa è convertito in una stringa CSV.
     *
     * @param filePath Il percorso del file CSV in cui scrivere i dati.
     * @param mappa    La mappa di dati da scrivere nel file.
     * @throws IOException Se si verifica un errore durante la scrittura del file.
     */
    public static void writeMapToCsv(String filePath, Map<String, ?> mappa) throws IOException {
        StringBuilder righeDaSalvare = new StringBuilder();

        mappa.forEach((k, v) -> {
            righeDaSalvare.append(((EntitaBaseAbstract) v).convertToCsv()).append("\r");
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(righeDaSalvare.toString());
            System.out.println(righeDaSalvare);
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
            throw e;
        }
    }

}