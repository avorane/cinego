package vue.fenetre;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import controller.session.Session;
import modele.entite.Salle;
import vue.composant.Salles.JSalle;
import vue.composant.Salles.JComboboxTypeSalle;
import vue.composant.Salles.JRangee;

public class FCreationModificationSalle extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboboxTypeSalle comboBox;
	private JFrame frame;
	private JSalle Jsalle;
	private Salle salle; 

	public FCreationModificationSalle(JFrame frame, Salle salle) {
		this.frame = frame;
		this.salle = salle;
		init();		
		textField.setText(salle.getLibelleSalle());
		comboBox.setTypeSalle(salle.getTypeSalle());
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FCreationModificationSalle(JFrame frame) {
		this.frame = frame;
		this.salle = new Salle();
		init();
	}
	
	public void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nom Salle :");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Type Salle :");
		panel.add(lblNewLabel_1);
		
		comboBox = new JComboboxTypeSalle();
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("Ajouter une Rangee");
		
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		Jsalle = new JSalle(true, salle);
		panel_3.add(Jsalle);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Valider");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("") && comboBox.getTypeSalle() != null) {
					if (salle.getIdSalle() != null) {					
						salle.setLibelleSalle(textField.getText());
						salle.setTypeSalle(comboBox.getTypeSalle());						
					} else {
						salle.setLibelleSalle(textField.getText());
						salle.setTypeSalle(comboBox.getTypeSalle());
					}
					Jsalle.creerOuModifierSalle(textField.getText(), comboBox.getTypeSalle());
					FAccueil page = new FAccueil(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(page);
					frame.revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "Vous devez choisir un nom de salle et un type de salle");
				}
			}
		});
		panel_4.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Annuler");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panel_4.add(btnNewButton_3);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jsalle.ajouterRangee();
			}
		});
	}
	
//	public FCreationModificationSalle(JFrame frame, Salle salle) {
//		this(frame);
//		this.contoller = new GestionSalle(salle);
//		this.salle = salle;
//		this.textField.setText(salle.getLibelleSalle());
//	}
}
