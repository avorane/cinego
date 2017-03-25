package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.BDD;
import modele.entite.Majoration;
import modele.entite.Reservation;
import modele.entite.Seance;
import modele.entite.Utilisateur;

public class ReservationCRUD {
	private static BDD connexion;
	private static ReservationCRUD singleton;
	
    public static ReservationCRUD getSingleton() {
		if (singleton == null) {
			singleton = new ReservationCRUD();
		}
        return singleton;
    }
	
	public ReservationCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public Reservation getById(Integer id){
		Reservation reservation = null;
		String query = "SELECT * FROM Reservation WHERE idReservation=" + id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Utilisateur utilisateur = UtilisateurCRUD.getSingleton().getById(result.getInt("idUtilisateur"));
				Calendar dateReservation = new GregorianCalendar();
				dateReservation.setTimeInMillis(result.getTimestamp("dateReservation").getTime());
				Seance seance = SeanceCRUD.getSingleton().getById(result.getInt("idSeance"));
				Majoration majoration = MajorationCRUD.getSingleton().getById(result.getInt("idMajoration"));
				reservation = new Reservation(id, dateReservation, utilisateur, seance, majoration);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservation;
	}
	
	public ArrayList<Reservation> getListAll() {
		String requete = null;
		ArrayList<Reservation> listReservation = new ArrayList<>();
		try {
			requete = "SELECT * FROM Reservation";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idReservation = resultat.getInt("idReservation");
				Reservation reservation = getById(idReservation);
				listReservation.add(reservation);
			}			
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listReservation;
	}
	
	public ArrayList<Reservation> getListByUtilisateur(Utilisateur utilisateur) {
		String requete = null;
		ArrayList<Reservation> listReservation = new ArrayList<>();
		try {
			requete = "SELECT * FROM Reservation"
					+ " WHERE idUtilisateur=" + Integer.toString(utilisateur.getId());
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idReservation = resultat.getInt("idReservation");
				Reservation reservation = getById(idReservation);
				listReservation.add(reservation);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listReservation;
	}
	
	public Reservation creerReservation(Reservation reservation) {
		String requete = null;
		try {
			Integer idReservation = 0;
			requete = "INSERT INTO Reservation "
				+ "(idUtilisateur, idSeance, dateReservation, idMajoration) "
				+ "VALUES "
				+ "(" + reservation.getUtilisateur().getId().toString()
				+ ", " + reservation.getSeance().getIdSeance().toString()
				+ ", '" + new Timestamp(new GregorianCalendar().getTimeInMillis()) + "'"
				+ ", 1)";
			if ((idReservation = connexion.insert(requete)) > 0) {
				reservation.setIdReservation(idReservation);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return reservation;
	}
	
	public boolean modifierReservation(int idReservation, Date dateReservation, Utilisateur utilisateur) {
		boolean modificationOK = false;
		String requete = null;
		try {
			requete = "UPDATE Reservation "
					+ " SET dateReservation=" + dateReservation
					+ ", SET idUtilisateur=" + Integer.toString(utilisateur.getId())
					+ " WHERE idReservation=" + Integer.toString(idReservation);
			if (connexion.insert(requete) == 0) {
				modificationOK = true;
				System.out.println("La modification de la réservation s'est bien effectuée");
			} else {
				System.out.println("La modification de la réservation a échoué");
			}
			return modificationOK;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return modificationOK;
	}
	
	public void supprimerReservation(Reservation reservation) {
		String requete = null;
		try {
			requete = "DELETE FROM Reservation "
					+ " WHERE idReservation=" + reservation.getIdReservation().toString();
			connexion.insert(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
