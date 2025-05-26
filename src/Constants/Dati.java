package Constants;

import models.Cliente;

import models.Prenotazione;
import models.ServizioAggiuntivo;
import models.Stanza;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe {@code Dati} contiene delle costanti statiche che rappresentano delle mappe
 * utilizzate per memorizzare informazioni relative a clienti, stanze, servizi aggiuntivi e prenotazioni.
 * Le mappe sono utilizzate per associare un identificatore (ID) di tipo {@code String} agli oggetti corrispondenti.
 */
public class Dati {

	/**
	 * Intestazioni per i dati dei clienti.
	 */
	public static final String[] HEADER_CLIENTI = {"ID", "NOME", "COGNOME", "CODICE FISCALE", "TELEFONO", "EMAIL"};

	/**
	 * Intestazioni per i dati delle stanze.
	 */
	public static final String[] HEADER_STANZE = {"ID", "NUMERO STANZA", "TIPOLOGIA", "PREZZO", "DESCRIZIONE"};

	/**
	 * Intestazioni per i dati dei servizi aggiuntivi.
	 */
	public static final String[] HEADER_SERVIZI_AGGIUNTIVI = {"ID", "TITOLO", "DESCRIZIONE", "PREZZO"};

	/**
	 * Intestazioni per i dati delle prenotazioni.
	 */
	public static final String[] HEADER_PRENOTAZIONI = {"ID", "Cliente", "Stanza", "Data Inizio", "Data Fine", "Servizi Aggiuntivi", "Numero Persone", "Prezzo Totale"};

	/**
	 * Mappa per memorizzare i clienti.
	 */
	public static final Map<String, Cliente> CLIENTE_MAP = new HashMap<>();

	/**
	 * Mappa per memorizzare le stanze.
	 */
	public static final Map<String, Stanza> STANZA_MAP = new HashMap<>();

	/**
	 * Mappa per memorizzare i servizi aggiuntivi.
	 */
	public static final Map<String, ServizioAggiuntivo> SERVIZIO_EXTRA_MAP = new HashMap<>();

	/**
	 * Mappa per memorizzare le prenotazioni.
	 */
	public static final Map<String, Prenotazione> PRENOTAZIONE_MAP = new HashMap<>();

	/**
	 * Costruttore privato per evitare che venga istanziata.
	 */
	private Dati() {
	}
}