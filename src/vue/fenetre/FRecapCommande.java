package vue.fenetre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;

import controller.fenetre.RecapCommande;
import modele.crud.ReservationCRUD;
import modele.crud.ReservationSiegeExtraCRUD;
import modele.crud.ReservationSiegeTarifCRUD;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeExtra;
import modele.entite.ReservationSiegeTarif;
import vue.composant.ModelTableRecapCommande;

import javax.swing.BoxLayout;

public class FRecapCommande extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private RecapCommande controllerRecapCommande;
	private JFrame frame;
	private Boolean modification;
	private Reservation reservation;

	/**
	 * Create the frame.
	 */
	public FRecapCommande(JFrame frame, ArrayList<ReservationSiegeTarif> listSiegeTarif, ArrayList<ReservationSiegeExtra> listSiegeExtra) {
		this.frame = frame;
		this.controllerRecapCommande = new RecapCommande(listSiegeTarif, listSiegeExtra);
		this.modification = false;
		init();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FRecapCommande(JFrame frame, Reservation reservation) {
		this.frame = frame;
		this.controllerRecapCommande = new RecapCommande(reservation);
		this.modification = true;
		this.reservation = reservation;
		init();
	}
	
	public void init() {
		
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Récapitulatif commande");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		table = new JTable();
		ModelTableRecapCommande model = new ModelTableRecapCommande(controllerRecapCommande.getModel());
		table.setModel(model);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		JButton btnValider = new JButton("Modifier");
		if (modification) {
			btnValider.setText("Modifier");
		} else {
			btnValider.setText("Valider");
		}
		
		panel_2.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FAccueil page = new FAccueil(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(page);
				frame.revalidate();
				
			}
		});
		panel_2.add(btnAnnuler);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (modification) {
					ReservationSiegeTarifCRUD.getSingleton().supprimerReservation(reservation);
					ReservationSiegeExtraCRUD.getSingleton().supprimerReservation(reservation);
					ReservationCRUD.getSingleton().supprimerReservation(reservation);
					FSelectionSiege page = new FSelectionSiege(frame, reservation.getSeance());
					//page.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					frame.getContentPane().removeAll();
					frame.getContentPane().add(page);
					frame.revalidate();
				} else {
					controllerRecapCommande.validerCommande();
					FAccueil pageAccueil = new FAccueil(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(pageAccueil);
					frame.revalidate();
				}
			}
		});
	}
}
