package views;

import Constants.Dati;
import Utils.PrenotazioneUtils;
import models.Cliente;
import models.Prenotazione;
import models.ServizioAggiuntivo;
import models.Stanza;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class DialogAggiungiPrenotazione extends JDialog {

    private static final long serialVersionUID = 1L;

    private JLabel lblCliente, lblStanza, lblDataInizio, lblDataFine, lblServiziAggiuntivi, lblNumeroPersone;
    private JTextField txtCliente, txtStanza, txtNumeroPersone;
    private JTextArea txtServiziAggiuntivi;
    private JSpinner datePickerInizio, datePickerFine;

    public DialogAggiungiPrenotazione(Cliente clienteSelezionato, Stanza stanzaSelezionata, ArrayList<ServizioAggiuntivo> serviziAggiuntivi) {
        initComponents(clienteSelezionato, stanzaSelezionata, serviziAggiuntivi);
    }

    private void initComponents(Cliente clienteSelezionato, Stanza stanzaSelezionata, ArrayList<ServizioAggiuntivo> serviziAggiuntivi) {
        setTitle("Aggiungi Prenotazione");
        setSize(600, 500);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        // Personalizzazione della finestra
        getContentPane().setBackground(new Color(238, 238, 238)); // Colore di sfondo chiaro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etichette e Campi di testo
        lblCliente = new JLabel("Cliente:");
        lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
        txtCliente = new JTextField(clienteSelezionato.getNome() + " " + clienteSelezionato.getCognome());
        txtCliente.setEditable(false);

        lblStanza = new JLabel("Stanza:");
        lblStanza.setFont(new Font("Arial", Font.BOLD, 14));
        txtStanza = new JTextField(stanzaSelezionata != null ? stanzaSelezionata.getNumeroStanza() + " - " + stanzaSelezionata.getTipologia() : "");
        txtStanza.setEditable(false);

        lblDataInizio = new JLabel("Data Inizio:");
        lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));
        datePickerInizio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorInizio = new JSpinner.DateEditor(datePickerInizio, "dd/MM/yyyy");
        datePickerInizio.setEditor(dateEditorInizio);

        lblDataFine = new JLabel("Data Fine:");
        lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
        datePickerFine = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorFine = new JSpinner.DateEditor(datePickerFine, "dd/MM/yyyy");
        datePickerFine.setEditor(dateEditorFine);

        lblServiziAggiuntivi = new JLabel("Servizi Aggiuntivi:");
        lblServiziAggiuntivi.setFont(new Font("Arial", Font.BOLD, 14));
        txtServiziAggiuntivi = new JTextArea();
        txtServiziAggiuntivi.setEditable(false);
        txtServiziAggiuntivi.setBackground(new Color(240, 240, 240));
        for (ServizioAggiuntivo servizio : serviziAggiuntivi) {
            txtServiziAggiuntivi.append(servizio.getTitolo() + "\n");
        }

        lblNumeroPersone = new JLabel("Numero Persone:");
        lblNumeroPersone.setFont(new Font("Arial", Font.BOLD, 14));
        txtNumeroPersone = new JTextField();
        txtNumeroPersone.setBackground(new Color(255, 255, 255));

        // Layout dei componenti
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblCliente, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblStanza, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtStanza, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblDataInizio, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(datePickerInizio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(lblDataFine, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(datePickerFine, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(lblServiziAggiuntivi, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(new JScrollPane(txtServiziAggiuntivi), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(lblNumeroPersone, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtNumeroPersone, gbc);

        // Bottoni
        JButton btnSalva = new JButton("Salva");
        JButton btnAnnulla = new JButton("Annulla");
        
        // Personalizzazione dei bottoni
        btnSalva.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalva.setBackground(new Color(148, 0, 211));
        btnSalva.setForeground(Color.WHITE);
        btnSalva.setFocusPainted(false);
        
        btnAnnulla.setFont(new Font("Arial", Font.BOLD, 14));
        btnAnnulla.setBackground(new Color(148, 0, 211));
        btnAnnulla.setForeground(Color.WHITE);
        btnAnnulla.setFocusPainted(false);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(btnSalva, gbc);
        gbc.gridx = 2;
        add(btnAnnulla, gbc);

        // Aggiungere funzionalità ai bottoni
        btnSalva.addActionListener(e -> {
            LocalDate dataInizio = ((Date) datePickerInizio.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataFine = ((Date) datePickerFine.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            boolean isValid = PrenotazioneUtils.isPrenotazioneValida(dataInizio, dataFine, stanzaSelezionata);
            int numeroPersone = 1;
            try {
                numeroPersone = Integer.parseInt(txtNumeroPersone.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numero di persone non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            if (numeroPersone <= 0) {
                JOptionPane.showMessageDialog(this, "Il numero di persone deve essere maggiore di 0", "Errore", JOptionPane.ERROR_MESSAGE);
            } else if (isValid) {
                salvaPrenotazione(clienteSelezionato, stanzaSelezionata, serviziAggiuntivi, dataInizio, dataFine, numeroPersone);
            } else {
                JOptionPane.showMessageDialog(this, "Le date inserite non sono valide", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnulla.addActionListener(e -> dispose());
    }

    private void salvaPrenotazione(Cliente cliente, Stanza stanza, ArrayList<ServizioAggiuntivo> serviziAggiuntivi, LocalDate dataInizio, LocalDate dataFine, int numeroPersone) {
        long numeroNotti = ChronoUnit.DAYS.between(dataInizio, dataFine);
        double prezzoStanza = stanza.getPrezzo() * numeroNotti * numeroPersone;
        double prezzoServizi = serviziAggiuntivi.stream().mapToDouble(ServizioAggiuntivo::getPrezzo).sum() * numeroNotti;
        double prezzoTotale = prezzoStanza + prezzoServizi;

        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setCliente(cliente);
        nuovaPrenotazione.setStanza(stanza);
        nuovaPrenotazione.setListaServExtra(serviziAggiuntivi);
        nuovaPrenotazione.setDataCheckIn(dataInizio);
        nuovaPrenotazione.setDataCheckOut(dataFine);
        nuovaPrenotazione.setNumeroPersone(numeroPersone);
        nuovaPrenotazione.setPrezzoTotale(prezzoTotale);

        Dati.PRENOTAZIONE_MAP.put(nuovaPrenotazione.getId(), nuovaPrenotazione);
        JOptionPane.showMessageDialog(this, "Prenotazione salvata con successo", "Salva", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
