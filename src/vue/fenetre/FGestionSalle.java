package vue.fenetre;

import javax.swing.JPanel;

import vue.composant.Salles.JComboboxSalles;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FGestionSalle extends JPanel {
	public FGestionSalle(JFrame frame) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControle = new JPanel();
		panelPrincipal.add(panelControle, BorderLayout.NORTH);
		
		JButton btnCreer = new JButton("Créer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FCreationModificationSalle pageCreationModificationSalle = new FCreationModificationSalle(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(pageCreationModificationSalle);
				frame.revalidate();
			}
		});
		panelControle.add(btnCreer);
		
		JButton btnModifier = new JButton("Modifier");
		
		panelControle.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		panelControle.add(btnSupprimer);
		
		JPanel panelSelection = new JPanel();
		panelPrincipal.add(panelSelection, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panel_1.add(btnAnnuler);
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelection.removeAll();
				JComboboxSalles comboboxSalles = new JComboboxSalles();
				JButton btnChoisir = new JButton("Choisir");
				btnChoisir.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						FCreationModificationSalle pageCreationModificationSalle = new FCreationModificationSalle(frame, comboboxSalles.getSalle());
						frame.getContentPane().removeAll();
						frame.getContentPane().add(pageCreationModificationSalle);
						frame.revalidate();						
					}
				});
				panelSelection.add(comboboxSalles);
				panelSelection.add(btnChoisir);
				panelSelection.revalidate();
				panelSelection.repaint();
			}
		});
	}

}
