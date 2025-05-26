package models;

import java.util.UUID;

/**
 * La classe astratta {@code EntitaBaseAbstract} rappresenta una base comune per tutte le entità nel sistema.
 * Ogni entità avrà un identificatore univoco generato automaticamente alla creazione.
 * Le classi che estendono questa classe avranno un ID univoco e un'interfaccia comune.
 */
public abstract class EntitaBaseAbstract implements EntitaBaseInterface {

	private String id;

	/**
	 * Costruttore che genera un identificatore univoco per l'entità.
	 * Utilizza UUID per garantire l'unicità dell'ID.
	 */
	public EntitaBaseAbstract() {
		this.id = UUID.randomUUID().toString(); // Genera un identificatore univoco
	}

	/**
	 * Restituisce l'ID univoco dell'entità.
	 *
	 * @return L'ID univoco dell'entità.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Imposta l'ID dell'entità.
	 *
	 * @param id L'ID da impostare per l'entità.
	 */
	protected void setId(String id) {
		this.id = id;
	}
}