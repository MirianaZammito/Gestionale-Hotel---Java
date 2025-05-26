package models;

import Constants.Dati;
import Utils.ServizioAggiuntivoUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code Prenotazione} rappresenta una prenotazione effettuata da un cliente per una stanza, con eventuali
 * servizi aggiuntivi, data di check-in, data di check-out.
 */
public class Prenotazione extends EntitaBaseAbstract {

    private Cliente cliente;
    private Stanza stanza;
    private List<ServizioAggiuntivo> listaServExtra;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private int numeroPersone;
    private double prezzoTotale;

    /**
     * Costruttore con id auto-generato per una prenotazione.
     *
     * @param idCliente      ID del cliente che ha effettuato la prenotazione
     * @param idStanza       ID della stanza prenotata
     * @param listaIdServizi Array degli ID dei servizi aggiuntivi richiesti
     * @param dataCheckIn    Data di check-in della prenotazione
     * @param dataCheckOut   Data di check-out della prenotazione
     * @param numeroPersone  Numero di persone per cui è effettuata la prenotazione
     * @param prezzoTotale   Prezzo totale della prenotazione
     */
    public Prenotazione(String idCliente, String idStanza, String[] listaIdServizi, LocalDate dataCheckIn, LocalDate dataCheckOut, int numeroPersone, double prezzoTotale) {
        super();
        init(idCliente, idStanza, listaIdServizi, dataCheckIn, dataCheckOut, numeroPersone, prezzoTotale);
    }

    /**
     * Costruttore con id specificato per una prenotazione.
     *
     * @param idPrenotazione ID specifico della prenotazione
     * @param idCliente      ID del cliente che ha effettuato la prenotazione
     * @param idStanza       ID della stanza prenotata
     * @param listaIdServizi Array degli ID dei servizi aggiuntivi richiesti
     * @param dataCheckIn    Data di check-in della prenotazione
     * @param dataCheckOut   Data di check-out della prenotazione
     * @param numeroPersone  Numero di persone per cui è effettuata la prenotazione
     * @param prezzoTotale   Prezzo totale della prenotazione
     */
    public Prenotazione(String idPrenotazione, String idCliente, String idStanza, String[] listaIdServizi, LocalDate dataCheckIn, LocalDate dataCheckOut, int numeroPersone, double prezzoTotale) {
        setId(idPrenotazione);
        init(idCliente, idStanza, listaIdServizi, dataCheckIn, dataCheckOut, numeroPersone, prezzoTotale);
    }

    /**
     * Costruttore predefinito.
     */
    public Prenotazione() {
        super();
    }

    /**
     * Metodo di inizializzazione per i campi della prenotazione.
     *
     * @param idCliente      ID del cliente che ha effettuato la prenotazione
     * @param idStanza       ID della stanza prenotata
     * @param listaIdServizi Array degli ID dei servizi aggiuntivi richiesti
     * @param dataCheckIn    Data di check-in della prenotazione
     * @param dataCheckOut   Data di check-out della prenotazione
     * @param numeroPersone  Numero di persone per cui è effettuata la prenotazione
     * @param prezzoTotale   Prezzo totale della prenotazione
     */
    private void init(String idCliente, String idStanza, String[] listaIdServizi, LocalDate dataCheckIn, LocalDate dataCheckOut, int numeroPersone, double prezzoTotale) {
        this.cliente = Dati.CLIENTE_MAP.get(idCliente);
        this.stanza = Dati.STANZA_MAP.get(idStanza);
        listaServExtra = new ArrayList<>();
        for (String idServizio : listaIdServizi) {
            listaServExtra.add(Dati.SERVIZIO_EXTRA_MAP.get(idServizio));
        }

        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.numeroPersone = numeroPersone;
        this.prezzoTotale = prezzoTotale;
    }

    /**
     * Restituisce il cliente associato alla prenotazione.
     *
     * @return Il cliente associato alla prenotazione
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Imposta il cliente per la prenotazione.
     *
     * @param cliente Il cliente da associare alla prenotazione
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Restituisce la stanza associata alla prenotazione.
     *
     * @return La stanza associata alla prenotazione
     */
    public Stanza getStanza() {
        return stanza;
    }

    /**
     * Imposta la stanza per la prenotazione.
     *
     * @param stanza La stanza da associare alla prenotazione
     */
    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    /**
     * Restituisce la lista dei servizi aggiuntivi associati alla prenotazione.
     *
     * @return La lista dei servizi aggiuntivi associati alla prenotazione
     */
    public List<ServizioAggiuntivo> getListaServExtra() {
        return listaServExtra;
    }

    /**
     * Imposta la lista dei servizi aggiuntivi per la prenotazione.
     *
     * @param listaServExtra La lista dei servizi aggiuntivi da associare alla prenotazione
     */
    public void setListaServExtra(List<ServizioAggiuntivo> listaServExtra) {
        this.listaServExtra = listaServExtra;
    }

    /**
     * Restituisce la data di check-in della prenotazione.
     *
     * @return La data di check-in della prenotazione
     */
    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    /**
     * Imposta la data di check-in per la prenotazione.
     *
     * @param dataCheckIn La data di check-in da impostare
     */
    public void setDataCheckIn(LocalDate dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    /**
     * Restituisce la data di check-out della prenotazione.
     *
     * @return La data di check-out della prenotazione
     */
    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }

    /**
     * Imposta la data di check-out per la prenotazione.
     *
     * @param dataCheckOut La data di check-out da impostare
     */
    public void setDataCheckOut(LocalDate dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    /**
     * Restituisce il numero di persone per cui è effettuata la prenotazione.
     *
     * @return Il numero di persone per cui è effettuata la prenotazione
     */
    public int getNumeroPersone() {
        return numeroPersone;
    }

    /**
     * Imposta il numero di persone per la prenotazione.
     *
     * @param numeroPersone Il numero di persone da impostare
     */
    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    /**
     * Restituisce il prezzo totale della prenotazione.
     *
     * @return Il prezzo totale della prenotazione
     */
    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    /**
     * Imposta il prezzo totale per la prenotazione.
     *
     * @param prezzoTotale Il prezzo totale da impostare
     */
    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    /**
     * Converte la prenotazione in una stringa formattata come CSV.
     *
     * @return La prenotazione come stringa in formato CSV
     */
    @Override
    public String convertToCsv() {
        return getId() + ";" + cliente.getId() + ";" + stanza.getId() + ";" + ServizioAggiuntivoUtils.serviziExtraToString(listaServExtra) + ";" + dataCheckIn + ";" + dataCheckOut + ";" + numeroPersone + ";" + prezzoTotale;
    }

    /**
     * Converte la prenotazione in un array di stringhe.
     *
     * @return Un array di stringhe rappresentante i dati della prenotazione
     */
    @Override
    public String[] convertToArray() {
        return new String[]{getId(), cliente.getId(), stanza.getId(), ServizioAggiuntivoUtils.serviziExtraToString(listaServExtra), dataCheckIn.toString(), dataCheckOut.toString(), String.valueOf(numeroPersone), String.valueOf(prezzoTotale)};
    }
}