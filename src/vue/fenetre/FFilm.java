package vue.fenetre;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;


public class FFilm extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public FFilm() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnRetour = new JButton("Retour");
		add(btnRetour);
		
		JButton btnSuivant = new JButton("R\u00E9server");
		add(btnSuivant);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Photo");
		panel_1.add(lblNewLabel, BorderLayout.WEST);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.BLACK);
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Titre :");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblLoremIpsum = new JLabel("Lorem Ipsum");
		panel.add(lblLoremIpsum);
		lblLoremIpsum.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoremIpsum.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblLoremIpsum.setBackground(Color.WHITE);
		lblLoremIpsum.setOpaque(true);
		lblLoremIpsum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDate = new JLabel("Date :");
		panel.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel label = new JLabel("03/03/2017");
		panel.add(label);
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBackground(Color.WHITE);
		
		JLabel lblHeure = new JLabel("Heure :");
		panel.add(lblHeure);
		lblHeure.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("18:16");
		panel.add(label_1);
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_2 = new JLabel("Durée :");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_3.setBackground(Color.WHITE);
		panel.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTextArea textSynopsis = new JTextArea();
		panel_3.add(textSynopsis);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Reserver");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		panel_2.add(btnNewButton_1);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
