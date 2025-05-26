package Utils;

import Constants.Dati;
import models.Stanza;

import java.util.List;

/**
 * La classe {@code StanzaUtils} fornisce metodi di utilità per la gestione delle stanze.
 */
public class StanzaUtils {

    /**
     * Popola la lista delle stanze a partire da una lista di righe.
     *
     * @param listaDiRigheStanze La lista di righe contenenti i dati delle stanze.
     */
    public static void popolaListaStanze(List<String[]> listaDiRigheStanze) {
        for (String[] riga : listaDiRigheStanze) {
            int numeroStanza = Integer.parseInt(riga[1]);
            double prezzo = Double.parseDouble(riga[3]);

            Stanza stanza = new Stanza(riga[0], numeroStanza, riga[2], prezzo, riga[4]);
            Dati.STANZA_MAP.put(stanza.getId(), stanza);
        }
    }
}