package vue.fenetre;

import javax.swing.JDialog;
import javax.swing.JPanel;

import modele.entite.TypeSiege;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import vue.composant.JComboboxTypeSiege;
import vue.composant.Salles.JPlace;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FChoixTypeSiege extends JDialog {
	private JComboboxTypeSiege comboboxTypeSiege;
	private JPlace place;
	public FChoixTypeSiege(JPlace place) {
		super();
		this.place = place;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblChoisirLeType = new JLabel("Choisir le type de siège :");
		panel_3.add(lblChoisirLeType);
		
		comboboxTypeSiege = new JComboboxTypeSiege();
		panel_3.add(comboboxTypeSiege);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JButton btnNewButton = new JButton("Supprimer Siège");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				place.supprimerPlace();
				dispose();
			}
		});
		panel_4.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnValider = new JButton("Valider");
		
		panel_2.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_2.add(btnAnnuler);
		
		pack();
		
		setLocationRelativeTo(null);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTypeSiegeSelected();
			}
		});
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void setTypeSiegeSelected() {
		place.setTypeSiege(comboboxTypeSiege.getTypeSiege());
		this.dispose();
	}
}
