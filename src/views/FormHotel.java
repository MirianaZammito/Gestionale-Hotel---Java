package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static views.FormHotelUtils.caricaTabella;

/**
 * La classe FormHotel rappresenta la finestra principale dell'interfaccia utente per la gestione delle prenotazioni, dei clienti, 
 * delle stanze e dei servizi aggiuntivi di un hotel. Fornisce un'interfaccia con schede separate per visualizzare e gestire i dati 
 * dell'hotel in formato tabellare.
 */
public class FormHotel extends JFrame {

	private static final long serialVersionUID = 1L;

    /**
     * Costruttore della classe FormHotel. Inizializza i componenti della finestra principale.
     */
	public FormHotel() {
		initComponents();
	}

	 /**
     * Inizializza i componenti della finestra, inclusi i tab, le tabelle, i pulsanti e il layout.
     * Imposta il titolo, il colore di sfondo, le dimensioni della finestra e il comportamento di chiusura.
     */
	private void initComponents() {
		setTitle("Booking Tool Manager");

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Clienti", createTablePanel("Clienti", false));
		tabbedPane.addTab("Stanze", createTablePanel("Stanze", false));
		tabbedPane.addTab("Servizi Aggiuntivi", createTablePanel("Servizi Aggiuntivi", false));
		tabbedPane.addTab("Prenotazioni", createTablePanel("Prenotazioni", true));

		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);

		getContentPane().setBackground(new Color(230, 230, 250)); // Sfondo lilla chiaro
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	  /**
     * Crea un JPanel che contiene una tabella per una specifica sezione (es. "Clienti", "Stanze", "Prenotazioni").
     * Aggiunge anche una label con il titolo, uno scroll pane per la tabella e un pannello di pulsanti per azioni come salva, inserisci ed elimina.
     *
     * @param title          Il titolo della sezione (es. "Clienti").
     * @param isPrenotazioni Flag che indica se il pannello è relativo alle "Prenotazioni".
     * @return Il JPanel contenente la tabella e i componenti associati.
     */
	private JPanel createTablePanel(String title, boolean isPrenotazioni) {
		JTable table = createTable(isPrenotazioni);
		caricaTabella(table, title);
		JScrollPane scrollPane = new JScrollPane(table);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(230, 230, 250)); // Sfondo lilla chiaro

		JLabel label = new JLabel(title, SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(label, BorderLayout.NORTH);

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = createButtonPanel(table, title);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
     * Crea un pannello contenente i pulsanti per salvare, inserire ed eliminare i record nella tabella.
     * I pulsanti vengono personalizzati con uno sfondo viola e testo bianco.
     *
     * @param table La tabella per cui i pulsanti eseguono le azioni.
     * @param title Il titolo della sezione (es. "Clienti").
     * @return Il JPanel contenente i pulsanti.
     */
	private JPanel createButtonPanel(JTable table, String title) {
		JButton saveButton = new JButton("Salva " + title);
		JButton insertButton = new JButton("Inserisci " + title);
		JButton deleteButton = new JButton("Elimina");

		insertButton.addActionListener(e -> {
			FormHotelUtils.caricaPulsanteInserisci(table, title);
		});

		saveButton.addActionListener(e -> {
			FormHotelUtils.caricaPulsanteSalva(table, title);
		});

		deleteButton.addActionListener(e -> {
			FormHotelUtils.caricaPulsanteElimina(table, title);
		});

		// Personalizzazione dei bottoni con colore viola e testo bianco
		customizeButton(saveButton);
		customizeButton(insertButton);
		customizeButton(deleteButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;

		buttonPanel.add(saveButton, gbc);
		gbc.gridx++;
		buttonPanel.add(insertButton, gbc);
		gbc.gridx++;
		buttonPanel.add(deleteButton, gbc);

		return buttonPanel;
	}

	 /**
     * Personalizza l'aspetto del pulsante dato impostando il suo colore di sfondo, il colore del testo e gli effetti al passaggio del mouse.
     *
     * @param button Il pulsante da personalizzare.
     */
	private void customizeButton(JButton button) {
		Color violet = new Color(148, 0, 211); // Colore viola

		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(violet);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);

		// Effetto al passaggio del mouse (hover)
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(violet.darker()); // Colore viola più scuro al passaggio del mouse
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(violet); // Torna al colore viola originale
			}
		});
	}

	/**
     * Crea una tabella (JTable) con comportamenti specifici. La tabella avrà celle modificabili a seconda della sezione
     * (es. "Prenotazioni" avrà celle non modificabili).
     *
     * @param isPrenotazioni Flag che indica se la tabella è per "Prenotazioni".
     * @return La tabella (JTable) con le proprietà personalizzate.
     */
	private JTable createTable(boolean isPrenotazioni) {
		DefaultTableModel model = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (isPrenotazioni) {
					return false; // Tutte le celle non sono modificabili per la tabella delle prenotazioni
				} else {
					return column != 0; // La prima colonna non è modificabile per le altre tabelle
				}
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (column != 0 || isPrenotazioni) {
					super.setValueAt(aValue, row, column);
				}
			}
		};

		JTable table = new JTable(model) {
		
			private static final long serialVersionUID = 1L;

			@Override
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				if (column == 0 || isPrenotazioni) {
					return false; // Impedisce la modifica per la prima colonna
				}
				return super.editCellAt(row, column, e);
			}
		};

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 14));
		header.setBackground(new Color(148, 0, 211)); // Colore viola
		header.setForeground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(20);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 0 || isPrenotazioni) {
					c.setBackground(new Color(230, 230, 250)); // Colore lilla chiaro per la colonna non modificabile
					c.setForeground(Color.GRAY); // Testo grigio per la colonna non modificabile
				} else if (!isSelected) {
					c.setForeground(Color.BLACK);
					c.setBackground(row % 2 == 0 ? new Color(245, 245, 255) : new Color(230, 230, 250)); // Colori lilla chiaro
				}
				return c;
			}
		});

		return table;
	}
}
