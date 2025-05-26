package views;

import Constants.Dati;
import Utils.ClienteUtils;
import Utils.WriteFile;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * La classe {@code FormHotelUtils} contiene metodi di utilità per la gestione delle tabelle nel form dell'hotel.
 */
public class FormHotelUtils {

    private static JTable tableClienti;
    private static JTable tableStanze;
    private static JTable tableServiziAggiuntivi;
    private static JTable tablePrenotazioni;

    /**
     * Carica il pulsante di salvataggio per la tabella specificata.
     *
     * @param table la tabella da salvare
     * @param title il titolo della tabella
     */
    public static void caricaPulsanteSalva(JTable table, String title) {
        try {
            switch (title) {
                case "Clienti":
                	salvaClienti(table);
                	break;
                case "Stanze":
                	salvaStanze(table);
                	break;
                case "Servizi Aggiuntivi":
                	salvaServiziAggiuntivi(table);
                	break;
                case "Prenotazioni":
                	salvaPrenotazioni(table);
                	break;
                default:
                	break;
            }
            caricaTabella(table, title);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Salva i dati delle prenotazioni dalla tabella.
     *
     * @param table la tabella delle prenotazioni
     */
    private static void salvaPrenotazioni(JTable table) throws IOException {
        WriteFile.writeMapToCsv("./resources/prenotazioni.csv", Dati.PRENOTAZIONE_MAP);
        JOptionPane.showMessageDialog(null, "Salvataggio Prenotazioni eseguito", "Salva", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Salva i dati dei clienti dalla tabella.
     *
     * @param table la tabella dei clienti
     * @throws Exception se tutte le colonne non sono valorizzate
     */
    private static void salvaClienti(JTable table) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            String[] valuesCliente = new String[columnCount - 1];
            Object idValue = model.getValueAt(row, 0);
            Cliente cliente;

            if (idValue != null && !idValue.toString().trim().isEmpty()) {
                cliente = Dati.CLIENTE_MAP.get(idValue.toString());
            } else {
                cliente = new Cliente();
            }

            for (int col = 1; col < columnCount; col++) { // Salta la colonna ID
                Object cellValue = model.getValueAt(row, col);
                if (cellValue == null || cellValue.toString().trim().isEmpty()) {
                    throw new Exception("Errore: Tutte le colonne devono essere valorizzate.");
                }
                valuesCliente[col - 1] = cellValue.toString();
            }

            cliente.setNome(valuesCliente[0]);
            cliente.setCognome(valuesCliente[1]);
            cliente.setCodiceFiscale(valuesCliente[2]);
            cliente.setTelefono(valuesCliente[3]);
            cliente.setEmail(valuesCliente[4]);
            
            // Verifica se il codice fiscale è valido
           if (!ClienteUtils.isCodiceFiscaleValido(cliente.getCodiceFiscale(), cliente)) {
                throw new Exception("Errore: Il codice fiscale per il cliente " + cliente.getNome() + " " + cliente.getCognome() + " non è valido.");
            }
           
           // Verifica se l'email è valida
           if (!ClienteUtils.isEmailValida(cliente.getEmail(), cliente)) {
                throw new Exception("Errore: Email per per il cliente " + cliente.getNome() + " " + cliente.getCognome() + " non è valida.");
            }
            
            Dati.CLIENTE_MAP.put(cliente.getId(), cliente);
            
        
        }

        WriteFile.writeMapToCsv("./resources/clienti.csv", Dati.CLIENTE_MAP);
        JOptionPane.showMessageDialog(null, "Salvataggio Clienti eseguito", "Salva", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Salva i dati delle stanze dalla tabella.
     *
     * @param table la tabella delle stanze
     * @throws Exception se tutte le colonne non sono valorizzate
     */
    private static void salvaStanze(JTable table) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            String[] valuesStanza = new String[columnCount - 1];
            Object idValue = model.getValueAt(row, 0);
            Stanza stanza;

            if (idValue != null && !idValue.toString().trim().isEmpty()) {
                stanza = Dati.STANZA_MAP.get(idValue.toString());
            } else {
                stanza = new Stanza();
            }

            for (int col = 1; col < columnCount; col++) { // Salta la colonna ID
                Object cellValue = model.getValueAt(row, col);
                if (cellValue == null || cellValue.toString().trim().isEmpty()) {
                    throw new Exception("Errore: Tutte le colonne devono essere valorizzate.");
                }
                valuesStanza[col - 1] = cellValue.toString();
            }

            stanza.setNumeroStanza(Integer.parseInt(valuesStanza[0]));
            stanza.setTipologia(valuesStanza[1]);
            stanza.setPrezzo(Double.parseDouble(valuesStanza[2]));
            stanza.setDescrizione(valuesStanza[3]);
            Dati.STANZA_MAP.put(stanza.getId(), stanza);
        }

        WriteFile.writeMapToCsv("./resources/stanze.csv", Dati.STANZA_MAP);
        JOptionPane.showMessageDialog(null, "Salvataggio Stanze eseguito", "Salva", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Salva i dati dei servizi aggiuntivi dalla tabella.
     *
     * @param table la tabella dei servizi aggiuntivi
     * @throws Exception se tutte le colonne non sono valorizzate
     */
    private static void salvaServiziAggiuntivi(JTable table) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            String[] valuesServizio = new String[columnCount - 1];
            Object idValue = model.getValueAt(row, 0);
            ServizioAggiuntivo servizio;

            if (idValue != null && !idValue.toString().trim().isEmpty()) {
                servizio = Dati.SERVIZIO_EXTRA_MAP.get(idValue.toString());
            } else {
                servizio = new ServizioAggiuntivo();
            }

            for (int col = 1; col < columnCount; col++) { // Salta la colonna ID
                Object cellValue = model.getValueAt(row, col);
                if (cellValue == null || cellValue.toString().trim().isEmpty()) {
                    throw new Exception("Errore: Tutte le colonne devono essere valorizzate.");
                }
                valuesServizio[col - 1] = cellValue.toString();
            }

            servizio.setTitolo(valuesServizio[0]);
            servizio.setDescrizione(valuesServizio[1]);
            servizio.setPrezzo(Double.parseDouble(valuesServizio[2]));
            Dati.SERVIZIO_EXTRA_MAP.put(servizio.getId(), servizio);
        }

        WriteFile.writeMapToCsv("./resources/servizi_aggiuntivi.csv", Dati.SERVIZIO_EXTRA_MAP);
        JOptionPane.showMessageDialog(null, "Salvataggio Servizi Aggiuntivi eseguito", "Salva", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Carica i dati nella tabella specificata.
     *
     * @param table la tabella da caricare
     * @param title il titolo della tabella
     * @param <T>   il tipo di entità base
     */
    public static <T extends EntitaBaseAbstract> void caricaTabella(JTable table, String title) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Pulisce i dati esistenti

        switch (title) {
            case "Clienti":
                tableClienti = table;
                model.setColumnIdentifiers(Dati.HEADER_CLIENTI);
                for (Cliente cliente : Dati.CLIENTE_MAP.values()) {
                    model.addRow(cliente.convertToArray());
                }
                break;
            case "Stanze":
                tableStanze = table;
                model.setColumnIdentifiers(Dati.HEADER_STANZE);
                for (Stanza stanza : Dati.STANZA_MAP.values()) {
                    model.addRow(stanza.convertToArray());
                }
                break;
            case "Servizi Aggiuntivi":
                tableServiziAggiuntivi = table;
                model.setColumnIdentifiers(Dati.HEADER_SERVIZI_AGGIUNTIVI);
                for (ServizioAggiuntivo servizio : Dati.SERVIZIO_EXTRA_MAP.values()) {
                    model.addRow(servizio.convertToArray());
                }
                break;
            case "Prenotazioni":
                tablePrenotazioni = table;
                model.setColumnIdentifiers(Dati.HEADER_PRENOTAZIONI);
                for (Prenotazione prenotazione : Dati.PRENOTAZIONE_MAP.values()) {
                    String cliente = prenotazione.getCliente().getNome() + " " + prenotazione.getCliente().getCognome();
                    String stanza = String.valueOf(prenotazione.getStanza().getNumeroStanza());
                    String servizi = prenotazione.getListaServExtra().stream().map(ServizioAggiuntivo::getTitolo).collect(Collectors.joining(", "));
                    model.addRow(new Object[]{prenotazione.getId(), cliente, stanza, prenotazione.getDataCheckIn().toString(), prenotazione.getDataCheckOut().toString(), servizi, prenotazione.getNumeroPersone(), prenotazione.getPrezzoTotale()});
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo di entità non supportato: " + title);
        }
    }

    /**
     * Carica il pulsante di inserimento per la tabella specificata.
     *
     * @param table la tabella in cui inserire i dati
     * @param title il titolo della tabella
     */
    public static void caricaPulsanteInserisci(JTable table, String title) {
        if ("Prenotazioni".equals(title)) {
            inserisciPrenotazione();
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[model.getColumnCount()]);
    }

    /**
     * Inserisce una nuova prenotazione. Il metodo esegue i seguenti passaggi:
     * <ul>
     *     <li>Verifica se è stato selezionato un cliente valido dalla tabella clienti.</li>
     *     <li>Verifica se è stata selezionata una stanza valida dalla tabella stanze.</li>
     *     <li>Verifica se sono stati selezionati dei servizi aggiuntivi dalla tabella servizi.</li>
     *     <li>Se tutti i dati sono validi, apre una finestra di dialogo per aggiungere la prenotazione.</li>
     *     <li>Aggiorna la tabella delle prenotazioni con i nuovi dati.</li>
     * </ul>
     * Se uno qualsiasi dei dati (cliente, stanza o servizio) non è valido o non è stato selezionato, 
     * viene mostrato un messaggio di errore tramite una finestra di dialogo.
     */
    private static void inserisciPrenotazione() {
        Cliente clienteSelezionato;
        if (tableClienti.getSelectedRow() != -1) {
            Object clienteIdValue = tableClienti.getValueAt(tableClienti.getSelectedRow(), 0);
            if (clienteIdValue == null || clienteIdValue.toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Errore: Cliente ID non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String clienteId = clienteIdValue.toString();
            clienteSelezionato = Dati.CLIENTE_MAP.get(clienteId);
        } else {
            JOptionPane.showMessageDialog(null, "Errore: Selezionare un cliente.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Stanza stanzaSelezionata;
        if (tableStanze.getSelectedRow() != -1) {
            Object stanzaIdValue = tableStanze.getValueAt(tableStanze.getSelectedRow(), 0);
            if (stanzaIdValue == null || stanzaIdValue.toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Errore: Stanza ID non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String stanzaId = stanzaIdValue.toString();
            stanzaSelezionata = Dati.STANZA_MAP.get(stanzaId);
        } else {
            JOptionPane.showMessageDialog(null, "Errore: Selezionare una stanza.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<ServizioAggiuntivo> serviziAggiuntiviSelezionati = new ArrayList<>();
        if (tableServiziAggiuntivi.getSelectedRow() != -1) {
            for (int row : tableServiziAggiuntivi.getSelectedRows()) {
                Object servizioIdValue = tableServiziAggiuntivi.getValueAt(row, 0);
                if (servizioIdValue == null || servizioIdValue.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Errore: Servizio Aggiuntivo ID non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String servizioId = servizioIdValue.toString();
                serviziAggiuntiviSelezionati.add(Dati.SERVIZIO_EXTRA_MAP.get(servizioId));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Errore: Selezionare almeno un servizio aggiuntivo.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DialogAggiungiPrenotazione dialogAggiungiPrenotazione = new DialogAggiungiPrenotazione(clienteSelezionato, stanzaSelezionata, serviziAggiuntiviSelezionati);
        dialogAggiungiPrenotazione.setVisible(true);
        caricaTabella(tablePrenotazioni, "Prenotazioni");
    }

    /**
     * Carica il pulsante di eliminazione per la tabella specificata.
     *
     * @param table la tabella da cui eliminare i dati
     * @param title il titolo della tabella
     */
    public static void caricaPulsanteElimina(JTable table, String title) {
        if (table.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object idValue = model.getValueAt(table.getSelectedRow(), 0);
            if (idValue == null || idValue.toString().trim().isEmpty()) {
                model.removeRow(table.getSelectedRow());
                return;
            }
            String id = idValue.toString();

            int response = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa riga?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                switch (title) {
                    case "Clienti":
                    	eliminaCliente(id);
                    	break;
                    case "Stanze":
                    	eliminaStanza(id);
                    	break;
                    case "Servizi Aggiuntivi":
                    	eliminaServizioAggiuntivo(id);
                    	break;
                    case "Prenotazioni":
                    	eliminaPrenotazione(id);
                    	
                }
                caricaTabella(table, title);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleziona un elemento da eliminare", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina un cliente dalla mappa dei dati.
     *
     * @param id l'ID del cliente da eliminare
     */
    private static void eliminaCliente(String id) {
        if (Dati.PRENOTAZIONE_MAP.values().stream().anyMatch(p -> p.getCliente().getId().equals(id))) {
            JOptionPane.showMessageDialog(null, "Errore: Il cliente è in utilizzo in una prenotazione.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Dati.CLIENTE_MAP.remove(id);
    }

    /**
     * Elimina una stanza dalla mappa dei dati.
     *
     * @param id l'ID della stanza da eliminare
     */
    private static void eliminaStanza(String id) {
        if (Dati.PRENOTAZIONE_MAP.values().stream().anyMatch(p -> p.getStanza().getId().equals(id))) {
            JOptionPane.showMessageDialog(null, "Errore: La stanza è in utilizzo in una prenotazione.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Dati.STANZA_MAP.remove(id);
    }

    /**
     * Elimina un servizio aggiuntivo dalla mappa dei dati.
     *
     * @param id l'ID del servizio aggiuntivo da eliminare
     */
    private static void eliminaServizioAggiuntivo(String id) {
        if (Dati.PRENOTAZIONE_MAP.values().stream().anyMatch(p -> p.getListaServExtra().stream().anyMatch(s -> s.getId().equals(id)))) {
            JOptionPane.showMessageDialog(null, "Errore: Il servizio aggiuntivo è in utilizzo in una prenotazione.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Dati.SERVIZIO_EXTRA_MAP.remove(id);
    }

    /**
     * Elimina una prenotazione dalla mappa dei dati.
     *
     * @param id l'ID della prenotazione da eliminare
     */
    private static void eliminaPrenotazione(String id) {
        Dati.PRENOTAZIONE_MAP.remove(id);
    }
}