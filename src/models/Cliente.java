package models;

/**
 * La classe {@code Cliente} rappresenta un cliente nel sistema.
 * Un cliente è definito da un nome, cognome, codice fiscale, numero di telefono e indirizzo email.
 * La classe estende {@link EntitaBaseAbstract} per acquisire un identificatore univoco (ID) e altre funzionalità di base.
 */
public class Cliente extends EntitaBaseAbstract {

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String telefono;
    private String email;

    /**
     * Costruttore che inizializza un nuovo oggetto {@code Cliente} senza ID.
     * Questo costruttore viene usato per la creazione di un cliente prima di assegnargli un ID.
     *
     * @param nome          Il nome del cliente.
     * @param cognome       Il cognome del cliente.
     * @param codiceFiscale Il codice fiscale del cliente.
     * @param telefono      Il numero di telefono del cliente.
     * @param email         L'indirizzo email del cliente.
     */
    public Cliente(String nome, String cognome, String codiceFiscale, String telefono, String email) {
        super();
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Costruttore che inizializza un nuovo oggetto {@code Cliente} con un ID specificato.
     * Questo costruttore viene usato quando si crea un cliente con un ID già esistente.
     *
     * @param idCliente     L'ID del cliente.
     * @param nome          Il nome del cliente.
     * @param cognome       Il cognome del cliente.
     * @param codiceFiscale Il codice fiscale del cliente.
     * @param telefono      Il numero di telefono del cliente.
     * @param email         L'indirizzo email del cliente.
     */
    public Cliente(String idCliente, String nome, String cognome, String codiceFiscale, String telefono, String email) {
        setId(idCliente);
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Costruttore predefinito.
     */
    public Cliente() {
        super();
    }

    /**
     * Restituisce il nome del cliente.
     *
     * @return Il nome del cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del cliente.
     *
     * @param nome Il nome del cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome del cliente.
     *
     * @return Il cognome del cliente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome del cliente.
     *
     * @param cognome Il cognome del cliente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il codice fiscale del cliente.
     *
     * @return Il codice fiscale del cliente.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il codice fiscale del cliente.
     *
     * @param codiceFiscale Il codice fiscale del cliente.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce il numero di telefono del cliente.
     *
     * @return Il numero di telefono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il numero di telefono del cliente.
     *
     * @param telefono Il numero di telefono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Restituisce l'indirizzo email del cliente.
     *
     * @return L'indirizzo email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email del cliente.
     *
     * @param email L'indirizzo email del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converte i dati del cliente in formato CSV.
     * Il formato restituito sarà: {@code ID;NOME;COGNOME;CODICE FISCALE;TELEFONO;EMAIL}.
     *
     * @return Una stringa rappresentante i dati del cliente in formato CSV.
     */
    @Override
    public String convertToCsv() {
        return getId() + ";" + nome + ";" + cognome + ";" + codiceFiscale + ";" + telefono + ";" + email;
    }

    /**
     * Converte i dati del cliente in un array di stringhe.
     *
     * @return Un array di stringhe rappresentante i dati del cliente.
     */
    @Override
    public String[] convertToArray() {
        return new String[]{getId(), nome, cognome, codiceFiscale, telefono, email};
    }
}