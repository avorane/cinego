package vue.fenetre;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import controller.session.Session;
import modele.crud.ReservationCRUD;
import modele.entite.Reservation;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class FCommandesUtilisateur extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	public FCommandesUtilisateur(JFrame frame) {
		this.frame = frame;
		setSize(new Dimension(799, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		add(panel);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		
		JLabel lblHistoriqueDesCommandes = new JLabel("Historique des Commandes");
		panel_2.add(lblHistoriqueDesCommandes);
		lblHistoriqueDesCommandes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
			}
		});
		panel_3.add(btnAnnuler);
		
		for(Reservation reservation : ReservationCRUD.getSingleton().getListByUtilisateur(Session.getSingleton().getUtilisateur())) {
			JPanel panelReservation = new JPanel(new GridLayout(1, 4));
			JLabel numReservation = new JLabel(reservation.getIdReservation().toString());
			panelReservation.add(numReservation);
			JLabel titreFilm = new JLabel(reservation.getSeance().getFilm().getTitreFilm());
			panelReservation.add(titreFilm);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			JLabel dateReservation = new JLabel(dateFormat.format(reservation.getDateReservation().getTimeInMillis()));
			panelReservation.add(dateReservation);
			JButton boutonModifier = new JButton("Modifier");
			boutonModifier.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Calendar dateLimite = new GregorianCalendar();
					dateLimite.setTimeInMillis(reservation.getSeance().getDateSeance().getTimeInMillis());
					dateLimite.add(Calendar.DAY_OF_YEAR, -1);
					if(reservation.getDateReservation().compareTo(dateLimite) < 0) {
						JOptionPane.showMessageDialog(null, "Vous ne pouvez plus modifier cette réservation");
					} else {
						FRecapCommande pageRecap = new FRecapCommande(frame, reservation);
						frame.getContentPane().removeAll();
						frame.getContentPane().add(pageRecap);
						frame.revalidate();	
					}
				}
			});
			panelReservation.add(boutonModifier);
			panel.add(panelReservation);
		}
		
	}

}
