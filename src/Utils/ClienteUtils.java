package Utils;

import Constants.Dati;
import models.Cliente;
import java.util.List;

/**
 * La classe {@code ClienteUtils} fornisce metodi di utilità per la gestione dei clienti.
 */
public class ClienteUtils {

	/**
	 * Popola la lista dei clienti a partire da una lista di righe.
	 *
	 * @param listaDiRighe La lista di righe contenenti i dati dei clienti.
	 */
	public static void popolaListaClienti(List<String[]> listaDiRighe) {
		for (String[] riga : listaDiRighe) {
					
			Cliente cliente = new Cliente(riga[0], riga[1], riga[2], riga[3], riga[4], riga[5]);
			Dati.CLIENTE_MAP.put(cliente.getId(), cliente);
		}
	}
	
	/**
	 * Verifica se il codice fiscale fornito è valido.
	 * 
	 * Questo metodo esegue una serie di controlli per determinare la validità del codice fiscale:
	 * 1. Verifica che la lunghezza del codice fiscale sia di 16 caratteri.
	 * 2. Controlla che il codice fiscale contenga solo lettere e numeri.
	 * 3. Verifica che il codice fiscale non sia già associato a un altro cliente (per evitare duplicati).
	 * 
	 * @param codiceFiscale Il codice fiscale da validare.
	 * @param cliente Il cliente che sta eseguendo la validazione (viene utilizzato per evitare
	 *                conflitti con il codice fiscale dello stesso cliente).
	 * @return {@code true} se il codice fiscale è valido, 
	 *         {@code false} altrimenti.
	 */
	public static boolean isCodiceFiscaleValido(String codiceFiscale, Cliente cliente) {
	    // Verifica che il codice fiscale sia lungo 16 caratteri
	    if (codiceFiscale.length() != 16) {
	        System.err.println("Codice Fiscale non valido: la lunghezza deve essere di 16 caratteri");
	        return false;
	    }

	    // Verifica che il formato del codice fiscale sia valido
	    if (!codiceFiscale.matches("[A-Za-z0-9]+")) {
	        System.err.println("Codice Fiscale non valido: deve contenere solo lettere e numeri");
	        return false;
	    }

	    // Verifica se il codice fiscale è già associato a un cliente (oppure se è duplicato)
	    for (Cliente c : Dati.CLIENTE_MAP.values()) {
	        if (c.getCodiceFiscale().equals(codiceFiscale) && !c.equals(cliente)) {
	            System.err.println("Codice Fiscale duplicato trovato per il cliente: " + c.getNome() + " " + c.getCognome());
	            return false;
	        }
	    }

	    // Se tutte le verifiche passano, il codice fiscale è valido
	    return true;
	}
	
	/**
	 * Verifica se il formato dell'indirizzo email fornito è valido.
	 * 
	 * Questo metodo utilizza un'espressione regolare per controllare se l'indirizzo email
	 * segue il formato standard di un'email, che include una parte locale, il simbolo "@"
	 * e un dominio valido con un TLD (dominio di livello superiore) di almeno due caratteri.
	 * 
	 * @param email L'indirizzo email che si desidera validare.
	 * @param cliente Il cliente che effettua la validazione
	 * @return {@code true} se l'indirizzo email è valido secondo l'espressione regolare,
	 *         {@code false} altrimenti.
	 */
	public static boolean isEmailValida(String email, Cliente cliente) {
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    return email != null && email.matches(emailRegex);
	}



}