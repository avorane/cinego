package vue.fenetre;

import javax.swing.JPanel;

import modele.entite.Seance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import vue.composant.Salles.JSalle;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FSelectionSiege extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Seance seance;
	private Integer nbrPlaces;
	private Integer nbrPlacesHandicape;

	public FSelectionSiege(JFrame frame, Seance seance) {
		this.frame = frame;
		this.seance = seance;
		this.nbrPlaces = 0;
		this.nbrPlacesHandicape = 0;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selection de la place");
		lblNewLabel.setBounds(61, 13, 118, 16);
		panel.add(lblNewLabel);
		
		JPanel panelSelection = new JPanel();
		add(panelSelection);
		panelSelection.setLayout(new BoxLayout(panelSelection, BoxLayout.X_AXIS));
		
		JSalle salle = new JSalle(false, seance);
		panelSelection.add(salle);
		
		JPanel panelControle = new JPanel();
		FlowLayout fl_panelControle = (FlowLayout) panelControle.getLayout();
		fl_panelControle.setHgap(25);
		add(panelControle, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (salle.nbrePlacesselectionnes() > 0) {
					FReservation pageReservation = new FReservation(frame, salle.faireReservation());
					frame.getContentPane().removeAll();
					frame.getContentPane().add(pageReservation);
					frame.revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "Vous devez choisir au moins une place");
				}
			}
		});
		panelControle.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler ");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panelControle.add(btnAnnuler);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnMoins = new JButton("Moins");
		panel_5.add(btnMoins);
		
		JLabel lblNbrePlace = new JLabel("0");
		panel_5.add(lblNbrePlace);
		
		JButton btnPlus = new JButton("Plus");
		
		panel_5.add(btnPlus);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JButton btnNewButton = new JButton("Moins");
		panel_6.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("0");
		panel_6.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Plus");
		panel_6.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JButton btnSelectionAutomatique = new JButton("Sélection Automatique");
		
		panel_3.add(btnSelectionAutomatique);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Sélection Automatique des Places");
		panel_4.add(lblNewLabel_1);
		
		btnMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nbrPlaces > 0) {
					nbrPlaces--;
					lblNbrePlace.setText(nbrPlaces.toString());
				}
			}
		});
		
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbrPlaces++;
				lblNbrePlace.setText(nbrPlaces.toString());
			}
		});
		
		btnSelectionAutomatique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nbrPlaces > 0) {
					salle.selectionAutomatique(nbrPlaces, nbrPlacesHandicape);
				}
			}
		});
	}
}
