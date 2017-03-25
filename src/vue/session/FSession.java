package vue.session;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import controller.fenetre.Connexion;
import controller.session.Session;
import modele.entite.Utilisateur;
import vue.fenetre.FAccueil;
import vue.fenetre.FGestionSeance;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import java.awt.Font;

public class FSession extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdentifiantAbonne;
	private JTextField txtMotDePasseAbonne;

	public FSession(JFrame frame) {
		setSize(new Dimension(273, 201));
		setBounds(new Rectangle(0, 0, 157, 143));
		setMaximumSize(new Dimension(273, 201));
		setPreferredSize(new Dimension(273, 187));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		setBounds(100, 100, 178, 142);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(142, 34, 116, 16);
		add(lblNewLabel);
		
		txtIdentifiantAbonne = new JTextField();
		txtIdentifiantAbonne.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtIdentifiantAbonne.setBounds(142, 56, 116, 22);
		add(txtIdentifiantAbonne);
		txtIdentifiantAbonne.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de Passe");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(142, 91, 116, 16);
		add(lblNewLabel_1);
		
		txtMotDePasseAbonne = new JTextField();
		txtMotDePasseAbonne.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtMotDePasseAbonne.setBounds(142, 120, 116, 22);
		add(txtMotDePasseAbonne);
		txtMotDePasseAbonne.setColumns(10);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setActionCommand("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String identifiantAbonne = txtIdentifiantAbonne.getText();
				String motDePasseAbonne = txtMotDePasseAbonne.getText();			
				Session session = Session.getSingleton();		
				
		        if (session.isUtilisateur(identifiantAbonne, motDePasseAbonne)) {
		        	FAccueil page = new FAccueil(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(page);
					frame.revalidate();
		        } else {
		        	JOptionPane.showMessageDialog(null, "Erreur d'identification");
		        }
		        
		        
				
			}
		});
		btnConnexion.setBounds(142, 155, 116, 25);
		add(btnConnexion);
	}
}