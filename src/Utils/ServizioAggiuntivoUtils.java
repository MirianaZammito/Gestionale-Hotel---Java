package Utils;

import Constants.Dati;
import models.ServizioAggiuntivo;

import java.util.List;

/**
 * La classe {@code ServizioAggiuntivoUtils} fornisce metodi di utilità per la gestione dei servizi aggiuntivi.
 */
public class ServizioAggiuntivoUtils {

    /**
     * Popola la lista dei servizi aggiuntivi a partire da una lista di righe.
     *
     * @param listaDiRigheServiziAggiuntivi La lista di righe contenenti i dati dei servizi aggiuntivi.
     */
    public static void popolaListaServiziAggiuntivi(List<String[]> listaDiRigheServiziAggiuntivi) {
        for (String[] riga : listaDiRigheServiziAggiuntivi) {
            ServizioAggiuntivo servizioAggiuntivo = new ServizioAggiuntivo(riga[0], riga[1], riga[2], Double.parseDouble(riga[3]));
            Dati.SERVIZIO_EXTRA_MAP.put(servizioAggiuntivo.getId(), servizioAggiuntivo);
        }
    }

    /**
     * Converte una lista di servizi aggiuntivi in una stringa separata da virgole contenente gli ID dei servizi.
     *
     * @param listaServExtra La lista di servizi aggiuntivi.
     * @return Una stringa contenente gli ID dei servizi aggiuntivi separati da virgole.
     */
    public static String serviziExtraToString(List<ServizioAggiuntivo> listaServExtra) {
        StringBuilder serviziExtra = new StringBuilder();
        for (int i = 0; i < listaServExtra.size(); i++) {
            ServizioAggiuntivo servizio = listaServExtra.get(i);
            serviziExtra.append(servizio.getId());
            if (i != listaServExtra.size() - 1) {
                serviziExtra.append(",");
            }
        }
        return serviziExtra.toString();
    }
}