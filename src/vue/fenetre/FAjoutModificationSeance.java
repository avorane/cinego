package vue.fenetre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import vue.composant.JCheckBoxTarif;
import vue.composant.JPanelTarifs;
import vue.composant.RenduSeances.JPanelSeance;
import vue.composant.RenduSeances.SeancesTableModel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import vue.composant.AutoCompletionComboBox.AutoCompletionComboBox;
import modele.crud.ProposeCRUD;
import modele.crud.SeanceCRUD;
import modele.crud.TarifCRUD;
import modele.entite.Film;
import modele.entite.Propose;
import modele.entite.Salle;
import modele.entite.Seance;
import modele.entite.Tarif;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import vue.composant.Salles.JComboboxSalles;

public class FAjoutModificationSeance extends JDialog {
	
	private static final long serialVersionUID = 12348762L;
	
	private JTextField textFieldDate;
	private ArrayList<JCheckBoxTarif> listCheckBoxTarif;
	private Seance seance;
	private AutoCompletionComboBox comboBoxFilm;
	private JComboboxSalles comboboxSalles;
	private JPanelTarifs panelTarifs;
	
	public FAjoutModificationSeance() {
		seance = new Seance();
		init();
	}
	
	public FAjoutModificationSeance(Seance seance) {
		this.seance = seance;
		init();
		afficherInfos();
	}
	
	public void init() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setSize(new Dimension(900, 600));
		
		listCheckBoxTarif = new ArrayList<>();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JPanelSeance panelSeance = new JPanelSeance();
		panel_1.add(panelSeance);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Film :");
		panel_3.add(lblNewLabel);
		
		comboBoxFilm = new AutoCompletionComboBox();
		if (seance.getFilm() != null) {
			comboBoxFilm.setSelectedFilm(seance.getFilm());
		} else {
			comboBoxFilm.setSelectedIndex(0);
		}
		panel_3.add(comboBoxFilm);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Salle :");
		panel_4.add(lblNewLabel_1);
		
		comboboxSalles = new JComboboxSalles();
		panel_4.add(comboboxSalles);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Date Seance (jj/mm/aaaa hh:mm) :");
		panel_5.add(lblNewLabel_2);
		
		textFieldDate = new JTextField();
		panel_5.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JPanel panelTarifsSeance = new JPanel();
		panel_7.add(panelTarifsSeance, BorderLayout.CENTER);
		panelTarifsSeance.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panelTarifsSeance.add(panel_8, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("Tarifs à appliquer à la séance");
		panel_8.add(lblNewLabel_3);
		
		panelTarifs = new JPanelTarifs(true);
		panelTarifsSeance.add(panelTarifs, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Film filmSelected = comboBoxFilm.getSelectedFilm();
					Salle salleSelected = comboboxSalles.getSalle();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String sDateSeance = textFieldDate.getText();
					Calendar dateSeance = new GregorianCalendar();
					dateSeance.setTimeInMillis(formatter.parse(sDateSeance).getTime());
					seance.setFilm(filmSelected);
					seance.setDateSeance(dateSeance);
					seance.setSalle(salleSelected);
					if (seance.getIdSeance() == null) {
						seance.setIdSeance(SeanceCRUD.getSingleton().creerSeance(seance));
					} else {
						SeanceCRUD.getSingleton().modifierSeance(seance);
					}
					ProposeCRUD.getSingleton().supprimerPourSeance(seance);
					for (JCheckBoxTarif checkboxTarif : panelTarifs.getListTarifs()) {
						if (checkboxTarif.isSelected()) {
							Propose propose = new Propose(seance, checkboxTarif.getTarif());
							ProposeCRUD.getSingleton().insertTarifsPourSeance(propose);
						}						
					}					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel_9.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_9.add(btnAnnuler);
		
		setLocationRelativeTo(null);
	}
	
	private void afficherInfos() {
		if (this.seance != null) {
			comboBoxFilm.setFilm(seance.getFilm());
			comboboxSalles.setSalle(seance.getSalle());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			textFieldDate.setText(dateFormat.format(seance.getDateSeance().getTimeInMillis()));
			ArrayList<Propose> listTarifsSeance = ProposeCRUD.getSingleton().getByIdSeance(seance);
			for (Propose propose : listTarifsSeance) {
				for (JCheckBoxTarif tarif : panelTarifs.getListTarifs())
				if (propose.getTarif().getIdTarif() == panelTarifs.getTarif().getIdTarif()) {
					tarif.setSelected(true);
				}
			}
		}
	}
}
