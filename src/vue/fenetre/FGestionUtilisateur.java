package vue.fenetre;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.fenetre.GestionUtilisateur;
import modele.entite.Utilisateur;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import vue.composant.JComboboxTypeUtilisateur;

public class FGestionUtilisateur extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFrame frame;
	private GestionUtilisateur controller;
	private JTextField textField_4;
	private JTextField textField_5;
	
	public FGestionUtilisateur(JFrame frame) {
		this.frame = frame;
		this.controller = new GestionUtilisateur();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Créer");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Chercher un utilisateur :");
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Chercher");
		panel_2.add(btnNewButton_3);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setVisible(false);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		panel_3.add(panel_4);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		panel_4.add(lblIdentifiant);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		panel_3.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		panel_6.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		panel_6.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		panel_3.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Prénom :");
		panel_8.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		panel_8.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblDateInscription = new JLabel("Date Inscription :");
		panel_5.add(lblDateInscription);
		
		textField_4 = new JTextField();
		panel_5.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Type Utilisateur :");
		panel_7.add(lblNewLabel_3);
		
		JComboboxTypeUtilisateur comboboxTypeUtilisateur = new JComboboxTypeUtilisateur();
		panel_7.add(comboboxTypeUtilisateur);
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe :");
		panel_13.add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		panel_13.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setVisible(false);
		
		JButton btnNewButton_4 = new JButton("Modifier");
		panel_10.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Annuler");
		panel_10.add(btnNewButton_5);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11);
		panel_11.setVisible(false);;
		
		JButton btnNewButton_6 = new JButton("Supprimer");
		panel_11.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Annuler");
		panel_11.add(btnNewButton_7);
		
		JPanel panel_12 = new JPanel();
		panel_9.add(panel_12);
		
		JButton btnNewButton_8 = new JButton("Créer");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.creerUtilisateur(textField_2.getText(), textField_3.getText(), textField_5.getText(), comboboxTypeUtilisateur.getTypeUtilisateur());
			}
		});
		panel_12.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Annuler");
		panel_12.add(btnNewButton_9);
		panel_12.setVisible(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.setVisible(false);
				panel_3.setVisible(true);
				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.setVisible(true);
				panel_10.setVisible(true);
				panel_12.setVisible(true);
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateur utilisateur = null;
				if ((utilisateur = controller.chercherUtilisateur(textField.getText())) != null) {
					textField_1.setText(utilisateur.getIdentifiantAbonne());
					textField_2.setText(utilisateur.getNomAbonne());
					textField_3.setText(utilisateur.getPrenomAbonne());
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					textField_4.setText(formatter.format(utilisateur.getDateInscription().getTimeInMillis()));
					comboboxTypeUtilisateur.selectTypeUtilisateur(utilisateur.getTypeUtilisateur());
				}
				panel_3.setVisible(true);
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					Utilisateur utilisateur = controller.getUtilisateur();
					utilisateur.setNomAbonne(textField_2.getText());
					utilisateur.setPrenomAbonne(textField_3.getText());
					utilisateur.setTypeUtilisateur(comboboxTypeUtilisateur.getTypeUtilisateur());
					controller.mettreAJourUtilisateur(utilisateur);
			}
		});
	}
}
