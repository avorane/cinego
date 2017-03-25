package vue.fenetre;

import javax.swing.JPanel;

import vue.composant.AutoCompletionComboBox.AutoCompletionComboBox;
import vue.composant.RenduSeances.CellRendererEditor;
import vue.composant.RenduSeances.CellRendererSeances;
import vue.composant.RenduSeances.JPanelSeance;
import vue.composant.RenduSeances.ModificationListener;
import vue.composant.RenduSeances.SeancesTableModel;

import javax.swing.JLabel;
import javax.swing.JButton;

import controller.session.Session;
import modele.entite.Seance;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.BoxLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class FAccueil extends JPanel implements ModificationListener{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private AutoCompletionComboBox txtChercherFilm;
	private Calendar dateSeance;
	private DateFormat dateFormat;

	public FAccueil(JFrame frame) {
		this.frame = frame;
		setSize(1346, 1200);
		setLayout(new BorderLayout(0, 10));

		dateSeance = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("dd/MM/YYYY");

		SeancesTableModel modelSeances = new SeancesTableModel(dateSeance, false);
		modelSeances.ajouterEcouteurSelection(this);
		JTable table = new JTable(modelSeances);
		table.getColumnModel().getColumn(0).setPreferredWidth(JPanelSeance.getWidthJPanel());
		table.setRowHeight(JPanelSeance.getHeightJPanel());
//		table.setPreferredSize(new Dimension(100, 100));
//		table.setLayout(new FlowLayout());
//		table.setRowHeight(100);
		//		CellRendererButton renderButton = new CellRendererButton();
		//		table.getColumnModel().getColumn(0).setCellRenderer(render);
		//		table.getColumnModel().getColumn(1).setCellRenderer(render);
		//		table.getColumnModel().getColumn(1).setCellEditor(new CellRendererEditor(new JCheckBox()));
		//		table.getColumnModel().getColumn(2).setCellRenderer(render);
		//		table.getColumnModel().getColumn(2).setCellEditor(new CellRendererEditor(new JCheckBox()));
		table.setDefaultRenderer(JComponent.class, new CellRendererSeances());
		table.setDefaultEditor(JComponent.class, new CellRendererEditor());

		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(2, 1, 0, 0));


		JPanel panelControle = new JPanel();
		panelNorth.add(panelControle);
		panelControle.setLayout(new BoxLayout(panelControle, BoxLayout.X_AXIS));

		JPanel panelDate = new JPanel();
		panelDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelControle.add(panelDate);

		JPanel panelSouth = new JPanel();
		add(panelSouth, BorderLayout.CENTER);
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane(table);
		panelSouth.add(scrollPane);
		
		String textLblSeances = "Séances du " + dateFormat.format(dateSeance.getTime());
		JLabel lblSancesLa = new JLabel(textLblSeances);
		panelDate.add(lblSancesLa);

		JButton btnJourPrcdent = new JButton("Jour précédent");
		btnJourPrcdent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateSeance.add(Calendar.DATE, -1);
				lblSancesLa.setText("Séances du " + dateFormat.format(dateSeance.getTime()));
				modelSeances.modifierDateSeances(dateSeance);
			}
		});
		panelDate.add(btnJourPrcdent);

		JButton btnJourSuivant = new JButton("Jour suivant");
		btnJourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateSeance.add(Calendar.DATE, 1);
				lblSancesLa.setText("Séances du " + dateFormat.format(dateSeance.getTime()));
				modelSeances.modifierDateSeances(dateSeance);
			}
		});
		panelDate.add(btnJourSuivant);

		JPanel panelChercher = new JPanel();
		panelChercher.setAlignmentX(1.0f);
		panelControle.add(panelChercher);

		txtChercherFilm = new AutoCompletionComboBox();
		txtChercherFilm.setToolTipText("Chercher un film...");
		panelChercher.add(txtChercherFilm);

		JButton btnChercher = new JButton("Chercher");
		panelChercher.add(btnChercher);

		JPanel panelCommande = new JPanel();
		panelControle.add(panelCommande);

		JButton btnCommandes = new JButton("Ma Commande");
		btnCommandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FCommandesUtilisateur page = new FCommandesUtilisateur(frame);
				//page.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panelCommande.add(btnCommandes);
		//if (Session.getSingleton().administrateurConnecte() == true) {
			JPanel panelAdministration = new JPanel();
			panelNorth.add(panelAdministration, BorderLayout.NORTH);
			panelAdministration.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

			JButton btnGestionSeances = new JButton("Gestion Seances");
			btnGestionSeances.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					FGestionSeance page = new FGestionSeance(frame);
					BorderLayout layout = (BorderLayout)frame.getLayout();
					frame.getLayout().removeLayoutComponent(layout.getLayoutComponent(BorderLayout.CENTER));
					layout.addLayoutComponent(page, BorderLayout.CENTER);
					frame.revalidate();
				}
			});
			panelAdministration.add(btnGestionSeances);

			JButton btnGestionUtilisateurs = new JButton("Gestion Utilisateurs");
			btnGestionUtilisateurs.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					FGestionUtilisateur pageUtilisateur = new FGestionUtilisateur(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(pageUtilisateur);
					frame.revalidate();
					
				}
			});
			panelAdministration.add(btnGestionUtilisateurs);

			JButton btnGestionSalles = new JButton("Gestion Salles");
			btnGestionSalles.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					FGestionSalle pageSalle = new FGestionSalle(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(pageSalle);
					frame.revalidate();
					
				}
			});
			panelAdministration.add(btnGestionSalles);
		}

		//		FSeances seances = new FSeances(false);
		//		seances.setBounds(886, 518, -670, -510);
		//		panel_2.add(seances);
	//}
	
	public void SeanceChoisie(Seance seance) {
		FSelectionSiege pageSelectionSiege = new FSelectionSiege(frame, seance);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(pageSelectionSiege);
		frame.revalidate();
	}

	@Override
	public void SeanceEnCoursModification(Seance seance) {
		// TODO Auto-generated method stub
		
	}
	
}