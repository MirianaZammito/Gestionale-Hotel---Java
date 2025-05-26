package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code ReadFile} fornisce metodi per leggere dati da file CSV.
 */
public class ReadFile {

    // Costruttore privato per impedire l'istanza della classe
    private ReadFile() {
    }

    /**
     * Legge il contenuto di un file CSV e restituisce una lista di righe,
     * dove ogni riga è rappresentata come un array di stringhe.
     *
     * @param path Il percorso del file CSV da leggere.
     * @return Una lista di array di stringhe, dove ogni array rappresenta una riga del file CSV.
     * Ogni elemento dell'array rappresenta una cella della riga separata dal delimitatore.
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    public static List<String[]> readCSV(String path) throws IOException {
        List<String[]> listaDiRighe = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            // Legge ogni riga del file fino alla fine
            while ((line = reader.readLine()) != null) {
                String[] riga = line.split(";");
                listaDiRighe.add(riga);
            }

        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
            throw e;
        }
        return listaDiRighe;
    }
}