package models;

/**
 * L'interfaccia {@code EntitaBaseInterface} definisce i metodi base che devono essere implementati da tutte le entità.
 * Ogni entità che implementa questa interfaccia deve fornire un identificatore univoco, 
 * la capacità di convertirsi in una rappresentazione CSV e la capacità di convertirsi in un array di stringhe.
 */
public interface EntitaBaseInterface {

    /**
     * Restituisce l'ID univoco dell'entità.
     * L'ID univoco è un identificatore che distingue un'entità dalle altre, 
     * necessario per gestire e accedere alle istanze in modo preciso.
     *
     * @return L'ID univoco dell'entità, sotto forma di stringa.
     */
    String getId();

    /**
     * Converte l'entità in una stringa formattata come CSV (Comma-Separated Values).
     * Questo metodo permette di esportare i dati dell'entità in un formato leggibile da un file CSV,
     * facilitando l'archiviazione o l'interscambio di dati tra diverse applicazioni o sistemi.
     *
     * @return La rappresentazione dell'entità come stringa in formato CSV.
     */
    String convertToCsv();

    /**
     * Converte l'entità in un array di stringhe.
     * Questo metodo restituisce un array che rappresenta i dati dell'entità in modo strutturato,
     * utile per operazioni di manipolazione o esportazione di dati in altre forme.
     *
     * @return Un array di stringhe rappresentante i dati dell'entità.
     */
    String[] convertToArray();
}
