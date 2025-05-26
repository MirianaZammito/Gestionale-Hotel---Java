import Constants.Dati;

import Utils.ClienteUtils;
import Utils.PrenotazioneUtils;
import Utils.ServizioAggiuntivoUtils;
import Utils.StanzaUtils;
import models.Prenotazione;
import views.FormHotel;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * La classe {@code Main} rappresenta il punto di ingresso principale dell'applicazione di gestione delle prenotazioni.
 */
public class Main {

	/**
	 * Metodo principale che avvia l'applicazione.
	 *
	 * @param args Argomenti della riga di comando.
	 */
	public static void main(String[] args) {
		init();
	}

	/**
	 * Inizializza e carica i dati necessari all'applicazione.
	 * Questo metodo si occupa di leggere i file CSV contenenti i dati relativi a clienti, stanze,
	 * servizi aggiuntivi e prenotazioni, e popola le rispettive strutture dati.
	 * Successivamente, stampa le prenotazioni e carica il form principale dell'applicazione.
	 */
	public static void init() {
		try {
			// CARICAMENTO CLIENTI
			List<String[]> listaDiRigheClienti = Utils.ReadFile.readCSV("./resources/clienti.csv");
			ClienteUtils.popolaListaClienti(listaDiRigheClienti);

			// CARICAMENTO STANZE
			List<String[]> listaDiRigheStanze = Utils.ReadFile.readCSV("./resources/stanze.csv");
			StanzaUtils.popolaListaStanze(listaDiRigheStanze);

			// CARICAMENTO SERVIZI AGGIUNTIVI
			List<String[]> listaDiRigheServiziAggiuntivi = Utils.ReadFile.readCSV("./resources/servizi_aggiuntivi.csv");
			ServizioAggiuntivoUtils.popolaListaServiziAggiuntivi(listaDiRigheServiziAggiuntivi);

			// CARICAMENTO PRENOTAZIONI
			List<String[]> listaDiRighePrenotazioni = Utils.ReadFile.readCSV("./resources/prenotazioni.csv");
			PrenotazioneUtils.popolaListaPrenotazioni(listaDiRighePrenotazioni);

			// Stampa le prenotazioni caricate
			for (Map.Entry<String, Prenotazione> entry : Dati.PRENOTAZIONE_MAP.entrySet()) {
				System.out.println(entry.getValue().convertToCsv());
			}

			// Carica il form principale dell'applicazione
			caricaForm();
		} catch (IOException e) {
			System.err.println("Errore durante il caricamento dei dati: " + e.getMessage());
		}
	}

	/**
	 * Carica e visualizza il form principale dell'applicazione.
	 * Questo metodo crea una finestra principale dell'applicazione e la visualizza
	 * con dimensioni 800x600, permettendo all'utente di interagire con l'interfaccia.
	 */
	private static void caricaForm() {
		FormHotel mainForm = new FormHotel();
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainForm.setSize(800, 600);
		mainForm.setVisible(true);
	}

}