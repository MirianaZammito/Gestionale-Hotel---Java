package models;

/**
 * La classe {@code ServizioAggiuntivo} rappresenta un servizio extra disponibile in una struttura ricettiva, con informazioni
 * sul titolo del servizio, la descrizione e il relativo prezzo.
 */
public class ServizioAggiuntivo extends EntitaBaseAbstract {

	private String titolo;
	private String descrizione;
	private double prezzo;

	/**
	 * Costruttore per creare un servizio aggiuntivo con ID auto-generato.
	 *
	 * @param titolo      Il titolo del servizio aggiuntivo.
	 * @param descrizione La descrizione del servizio aggiuntivo.
	 * @param prezzo      Il prezzo del servizio aggiuntivo.
	 */
	public ServizioAggiuntivo(String titolo, String descrizione, double prezzo) {
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	/**
	 * Costruttore per creare un servizio aggiuntivo con ID specificato.
	 *
	 * @param idServizioAggiuntivo L'ID specifico del servizio aggiuntivo.
	 * @param titolo               Il titolo del servizio aggiuntivo.
	 * @param descrizione          La descrizione del servizio aggiuntivo.
	 * @param prezzo               Il prezzo del servizio aggiuntivo.
	 */
	public ServizioAggiuntivo(String idServizioAggiuntivo, String titolo, String descrizione, double prezzo) {
		setId(idServizioAggiuntivo);
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	/**
	 * Costruttore predefinito.
	 */
	public ServizioAggiuntivo() {
		super();
	}

	/**
	 * Restituisce il titolo del servizio aggiuntivo.
	 *
	 * @return Il titolo del servizio aggiuntivo.
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Imposta il titolo del servizio aggiuntivo.
	 *
	 * @param titolo Il titolo del servizio aggiuntivo da impostare.
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Restituisce la descrizione del servizio aggiuntivo.
	 *
	 * @return La descrizione del servizio aggiuntivo.
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Imposta la descrizione del servizio aggiuntivo.
	 *
	 * @param descrizione La descrizione del servizio aggiuntivo da impostare.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Restituisce il prezzo del servizio aggiuntivo.
	 *
	 * @return Il prezzo del servizio aggiuntivo.
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Imposta il prezzo del servizio aggiuntivo.
	 *
	 * @param prezzo Il prezzo del servizio aggiuntivo da impostare.
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Converte il servizio aggiuntivo in una stringa formattata come CSV.
	 *
	 * @return Il servizio aggiuntivo come stringa in formato CSV.
	 */
	@Override
	public String convertToCsv() {
		return getId() + ";" + titolo + ";" + descrizione + ";" + prezzo;
	}

	/**
	 * Converte il servizio aggiuntivo in un array di stringhe.
	 *
	 * @return Un array di stringhe rappresentante i dati del servizio aggiuntivo.
	 */
	@Override
	public String[] convertToArray() {
		return new String[]{getId(), titolo, descrizione, String.valueOf(prezzo)};
	}
}