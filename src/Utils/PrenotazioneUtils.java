package Utils;

import Constants.Dati;
import models.Prenotazione;
import models.Stanza;

import java.time.LocalDate;
import java.util.List;

/**
 * La classe {@code PrenotazioneUtils} fornisce metodi di utilità per la gestione delle prenotazioni.
 * Contiene metodi per popolare la lista di prenotazioni da un file e per verificare la validità di una prenotazione.
 */
public class PrenotazioneUtils {

	/**
	 * Popola la lista di prenotazioni (mappa da {@linkplain Dati}) con i dati letti dal file.
	 * Per ogni riga nel file CSV, viene creata una nuova prenotazione che viene poi aggiunta alla mappa
	 * di prenotazioni in {@link Dati.PRENOTAZIONE_MAP}.
	 *
	 * @param listaDiRighe La lista di righe contenenti i dati delle prenotazioni da caricare.
	 *                     Ogni riga è rappresentata da un array di stringhe, in cui ogni elemento
	 *                     corrisponde a un dato specifico della prenotazione.
	 */
	public static void popolaListaPrenotazioni(List<String[]> listaDiRighe) {
		for (String[] riga : listaDiRighe) {
			String idPrenotazione = riga[0];
			String idCliente = riga[1];
			String idStanza = riga[2];
			String[] listaIdServizi = riga[3].split(",");

			LocalDate checkIn = LocalDate.parse(riga[4]);
			LocalDate checkOut = LocalDate.parse(riga[5]);

			int numeroDiPersone = Integer.parseInt(riga[6]);
			double prezzoTotale = Double.parseDouble(riga[7]);

			Prenotazione prenotazione = new Prenotazione(idPrenotazione, idCliente, idStanza, listaIdServizi, checkIn, checkOut, numeroDiPersone, prezzoTotale);
			Dati.PRENOTAZIONE_MAP.put(prenotazione.getId(), prenotazione);
		}
	}

	/**
	 * Verifica se le date di check-in e check-out sono valide per la prenotazione di una stanza.
	 * Una prenotazione è considerata valida se:
	 * <ul>
	 *     <li>La data di check-in è precedente alla data di check-out.</li>
	 *     <li>La data di check-in è successiva alla data corrente.</li>
	 *     <li>Non esistono altre prenotazioni che si sovrappongono con la nuova prenotazione per la stessa stanza.</li>
	 * </ul>
	 *
	 * @param checkIn  La data di check-in della prenotazione.
	 * @param checkOut La data di check-out della prenotazione.
	 * @param stanza   La stanza per cui si vuole effettuare la prenotazione.
	 * @return {@code true} se la prenotazione è valida, {@code false} altrimenti.
	 *         Una prenotazione non è valida se le condizioni sopra non sono rispettate.
	 */
	public static boolean isPrenotazioneValida(LocalDate checkIn, LocalDate checkOut, Stanza stanza) {
		LocalDate dataCorrente = LocalDate.now();

		// Verifica che check-in sia prima di check-out e che check-in non sia passato
		if (!checkIn.isBefore(checkOut) || checkIn.isBefore(dataCorrente)) {
			return false;
		}

		// Controllo delle prenotazioni esistenti
		for (Prenotazione prenotazione : Dati.PRENOTAZIONE_MAP.values()) {
			LocalDate prenotazioneCheckIn = prenotazione.getDataCheckIn();
			LocalDate prenotazioneCheckOut = prenotazione.getDataCheckOut();

			// Verifica sovrapposizione delle date con altre prenotazioni per la stessa stanza
			if (prenotazione.getStanza().equals(stanza) &&
					checkIn.isBefore(prenotazioneCheckOut) &&
					checkOut.isAfter(prenotazioneCheckIn)) {
				return false;
			}
		}

		return true;
	}
}
