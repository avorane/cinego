package vue.fenetre;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import modele.crud.SalleCRUD;
import modele.crud.SeanceCRUD;
import modele.entite.Salle;
import modele.entite.Seance;
import controller.fenetre.GestionSeance;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import vue.composant.DatePickerFormatter.DateLabelFormatter;
import vue.composant.RenduSeances.CellRendererEditor;
import vue.composant.RenduSeances.CellRendererSeances;
import vue.composant.RenduSeances.JPanelSeance;
import vue.composant.RenduSeances.ModificationListener;
import vue.composant.RenduSeances.SeancesTableModel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Dimension;
import vue.composant.Salles.JComboboxSalles;

public class FGestionSeance extends JPanel implements ModificationListener{

	private static final long serialVersionUID = 185123;
	private GestionSeance controllerSeance;
	private SeancesTableModel seancesModel;
	private JDatePickerImpl datePicker;
	private JComboboxSalles comboBoxSalles;
	
	public FGestionSeance(JFrame frame) {
		controllerSeance = new GestionSeance();
		seancesModel = new SeancesTableModel(new GregorianCalendar(), controllerSeance.getSalleChoisie(), true);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		JLabel lblDate = new JLabel("date");
		panel_1.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		
		UtilDateModel modelDate = new UtilDateModel(new Date());
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel_1.add(datePicker);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSeancesDuJour = new JLabel("Séances du " + datePicker.getJFormattedTextField().getText() + " pour la salle");
		panel_2.add(lblSeancesDuJour);
		lblSeancesDuJour.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBoxSalles = new JComboboxSalles();
		comboBoxSalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changerDateOuSalle();
			}
		});
		panel_2.add(comboBoxSalles);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panel_5.add(btnRetour);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		
		seancesModel.ajouterEcouteurModification(this);
		JTable tableSeances = new JTable(seancesModel);
		tableSeances.setDefaultRenderer(JComponent.class, new CellRendererSeances());
		tableSeances.setDefaultEditor(JComponent.class, new CellRendererEditor());
		tableSeances.getColumnModel().getColumn(0).setWidth(JPanelSeance.getWidthJPanel());
		tableSeances.setRowHeight(JPanelSeance.getHeightJPanel());
		JScrollPane scrollPane = new JScrollPane(tableSeances);
		panel_13.setLayout(new BorderLayout(0, 0));
		panel_13.add(scrollPane);
		
		JPanel panel_14 = new JPanel();
		panel_6.add(panel_14, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FAjoutModificationSeance frameAjoutModificationFilm = new FAjoutModificationSeance();
				frameAjoutModificationFilm.setVisible(true);
			}
		});
		panel_14.add(btnNewButton);
	}
	
	public void changerDateOuSalle() {
		try {
			Salle salleChoisie = comboBoxSalles.getSalle();
//			Date dateSelectionnee = (Date)datePicker.getModel().getValue();
//			Calendar dateSeances = new GregorianCalendar();
//			dateSeances.setTimeInMillis(dateSelectionnee.getTime());
			seancesModel.modifierDateSeancesEtSalle(GregorianCalendar.getInstance(), salleChoisie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void SeanceEnCoursModification(Seance seance) {
		FAjoutModificationSeance fenetreModif = new FAjoutModificationSeance(seance);
		fenetreModif.setVisible(true);
		//controllerSeance.seanceEnModification(seance);
		//setInfosSeanceEnModification(seance);
		//panel_4.setVisible(true);
	}

	@Override
	public void SeanceChoisie(Seance seance) {
		// TODO Auto-generated method stub
		
	}
}