package models;

/**
 * La classe {@code Stanza} rappresenta una stanza di un hotel, con attributi come il numero della stanza,
 * la tipologia, il prezzo e la descrizione della stanza.
 */
public class Stanza extends EntitaBaseAbstract {

	private int numeroStanza;
	private String tipologia;
	private double prezzo;
	private String descrizione;

	/**
	 * Costruttore della stanza con id auto-generato.
	 *
	 * @param numeroStanza Il numero della stanza.
	 * @param tipologia    La tipologia della stanza (es. singola, doppia, suite, ecc.).
	 * @param prezzo       Il prezzo per notte della stanza.
	 * @param descrizione  Una descrizione aggiuntiva della stanza.
	 */
	public Stanza(int numeroStanza, String tipologia, double prezzo, String descrizione) {
		super();
		init(numeroStanza, tipologia, prezzo, descrizione);
	}

	/**
	 * Costruttore della stanza con id specificato.
	 *
	 * @param idStanza     L'ID specifico della stanza.
	 * @param numeroStanza Il numero della stanza.
	 * @param tipologia    La tipologia della stanza (es. singola, doppia, suite, ecc.).
	 * @param prezzo       Il prezzo per notte della stanza.
	 * @param descrizione  Una descrizione aggiuntiva della stanza.
	 */
	public Stanza(String idStanza, int numeroStanza, String tipologia, double prezzo, String descrizione) {
		setId(idStanza);
		init(numeroStanza, tipologia, prezzo, descrizione);
	}

	/**
	 * Costruttore predefinito.
	 */
	public Stanza() {
		super();
	}

	/**
	 * Inizializza i campi della stanza.
	 *
	 * @param numeroStanza Il numero della stanza.
	 * @param tipologia    La tipologia della stanza (es. singola, doppia, suite, ecc.).
	 * @param prezzo       Il prezzo per notte della stanza.
	 * @param descrizione  Una descrizione aggiuntiva della stanza.
	 */
	private void init(int numeroStanza, String tipologia, double prezzo, String descrizione) {
		this.numeroStanza = numeroStanza;
		this.tipologia = tipologia;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
	}

	/**
	 * Restituisce il numero della stanza.
	 *
	 * @return Il numero della stanza.
	 */
	public int getNumeroStanza() {
		return numeroStanza;
	}

	/**
	 * Imposta il numero della stanza.
	 *
	 * @param numeroStanza Il numero della stanza da impostare.
	 */
	public void setNumeroStanza(int numeroStanza) {
		this.numeroStanza = numeroStanza;
	}

	/**
	 * Restituisce la tipologia della stanza.
	 *
	 * @return La tipologia della stanza (es. singola, doppia, suite, ecc.).
	 */
	public String getTipologia() {
		return tipologia;
	}

	/**
	 * Imposta la tipologia della stanza.
	 *
	 * @param tipologia La tipologia della stanza da impostare.
	 */
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	/**
	 * Restituisce il prezzo per notte della stanza.
	 *
	 * @return Il prezzo per notte della stanza.
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Imposta il prezzo per notte della stanza.
	 *
	 * @param prezzo Il prezzo per notte da impostare.
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 *
	 * @return La descrizione della stanza.
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Imposta la descrizione della stanza.
	 *
	 * @param descrizione La descrizione da impostare per la stanza.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Converte la stanza in una stringa formattata come CSV.
	 *
	 * @return La stanza come stringa in formato CSV.
	 */
	@Override
	public String convertToCsv() {
		return getId() + ";" + numeroStanza + ";" + tipologia + ";" + prezzo + ";" + descrizione;
	}

	/**
	 * Converte la stanza in un array di stringhe.
	 *
	 * @return Un array di stringhe rappresentante i dati della stanza.
	 */
	@Override
	public String[] convertToArray() {
		return new String[]{getId(), String.valueOf(numeroStanza), tipologia, String.valueOf(prezzo), descrizione};
	}
}