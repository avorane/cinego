package vue.fenetre;

import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.border.LineBorder;

import controller.fenetre.Reservation;
import modele.entite.ReservationSiegeExtra;
import modele.entite.ReservationSiegeTarif;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.BorderLayout;
import vue.composant.JPanelTarifs;
import vue.composant.JTableExtra.CellExtraRenderer;
import vue.composant.JTableExtra.CellRendererExtraEditor;
import vue.composant.JTableExtra.ExtraTableModel;
import vue.composant.RenduSeances.CellRendererEditor;

import javax.swing.BoxLayout;
import vue.composant.JComboboxTypeSiege;
import vue.composant.JComboboxExtra;

public class FReservation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Reservation controllerReservation;
	private JFrame frame;
	
	public FReservation(JFrame frame, ArrayList<ReservationSiegeTarif> listReservationSiegeTarif) {
		this.frame = frame;
		this.controllerReservation = new Reservation(listReservationSiegeTarif);
		init();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FReservation(JFrame frame, Reservation controllerReservation) {
		this.frame = frame;
		this.controllerReservation = controllerReservation;
		this.controllerReservation.choisirReservation();
		init();
	}
	
	public void init() {
		setSize(new Dimension(1038, 678));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		JPanelTarifs panelTarifs = new JPanelTarifs(false, this.controllerReservation.getReservation().getReservation().getSeance());
		panel_3.add(panelTarifs);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Choisir un Extra :");
		panel_5.add(lblNewLabel);
		
		JComboboxExtra comboboxExtra = new JComboboxExtra();
		panel_5.add(comboboxExtra);
		
		JLabel lblNewLabel_1 = new JLabel("Quantité :");
		panel_5.add(lblNewLabel_1);
		
		JButton btnExtraMoins = new JButton("Moins");
		panel_5.add(btnExtraMoins);
		
		JLabel lblNewLabel_2 = new JLabel("0");
		panel_5.add(lblNewLabel_2);
		
		JButton btnExtraPlus = new JButton("Plus");
		panel_5.add(btnExtraPlus);
		
		JButton btnAjouter = new JButton("Ajouter");
		
		panel_5.add(btnAjouter);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		table = new JTable();
		ExtraTableModel extraModel = new ExtraTableModel(this.controllerReservation.getListExtra());
		table.setModel(extraModel);
		table.setDefaultRenderer(JComponent.class, new CellExtraRenderer());
		table.setDefaultEditor(JComponent.class, new CellRendererExtraEditor());
		panel_6.add(table);
		
		JLabel lblSiege = new JLabel("Choix des Extras pour le Siège " + this.controllerReservation.getReservation().getSiege().getIdSiege().toString());
		panel_7.add(lblSiege);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		
		panel_4.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panel_4.add(btnAnnuler);
		
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReservationSiegeExtra extra = new ReservationSiegeExtra(controllerReservation.getReservation().getReservation(), comboboxExtra.getExtra(), controllerReservation.getReservation().getSiege(), 1);
				controllerReservation.addReservationExtra(extra);
				extraModel.ajouterExtra();
			}
		});
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (panelTarifs.getNbreCheckBoxSelected() == 1) {
					controllerReservation.setTarif(panelTarifs.getTarif());
					if (controllerReservation.reservationTerminee()) {
						FRecapCommande pageRecapitulatif = new FRecapCommande(frame, controllerReservation.getListTarif(), controllerReservation.getListExtra());
						frame.getContentPane().removeAll();
						frame.getContentPane().add(pageRecapitulatif);
						frame.revalidate();
					} else {
						FReservation pageReservationSuite = new FReservation(frame, controllerReservation);
						frame.getContentPane().removeAll();
						frame.getContentPane().add(pageReservationSuite);
						frame.revalidate();
					}
				} else if (panelTarifs.getNbreCheckBoxSelected() == 0) {
					JOptionPane.showMessageDialog(null, "Vous devez sélectionner un tarif pour le siège");
				} else if (panelTarifs.getNbreCheckBoxSelected() > 1) {
					JOptionPane.showMessageDialog(null, "Vous devez sélectionner un seul tarif pour le siège");
				}
			}
		});
	}
}
