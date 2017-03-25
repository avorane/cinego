package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Extra;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeExtra;
import modele.entite.Siege;

public class ReservationSiegeExtraCRUD {
	private static BDD connexion;
	private static ReservationSiegeExtraCRUD singleton;
	
    public static ReservationSiegeExtraCRUD getSingleton() {
		if (singleton == null) {
			singleton = new ReservationSiegeExtraCRUD();
		}
        return singleton;
    }
	
	public ReservationSiegeExtraCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public ArrayList<ReservationSiegeExtra> getByIdReservation(Reservation reservation){
		ArrayList<ReservationSiegeExtra> listReservationSiegeTarif = new ArrayList<>();
		String query = "SELECT * FROM reservation_siege_extra WHERE idReservation=" + reservation.getIdReservation().toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idSiege = result.getInt("idSiege");
				Siege siege = SiegeCRUD.getSingleton().getById(idSiege);
				Integer idExtra = result.getInt("idExtra");
				Extra extra = ExtraCRUD.getSingleton().getById(idExtra);
				Integer quantite = result.getInt("quantite");
				ReservationSiegeExtra reservationSiegeTarif = new ReservationSiegeExtra(reservation, extra, siege, quantite);
				listReservationSiegeTarif.add(reservationSiegeTarif);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listReservationSiegeTarif;
	}
	
	public Boolean creerReservationSiegeExtra(ArrayList<ReservationSiegeExtra> listReservationSiegeExtra) {
		for(ReservationSiegeExtra reservation : listReservationSiegeExtra) {
			String requete = null;
			try {
				requete = "INSERT INTO reservation_siege_extra "
					+ "(idReservation, idSiege, idExtra, quantite) "
					+ "VALUES "
					+ "(" + reservation.getReservation().getIdReservation().toString()
					+ ", " + reservation.getSiege().getIdSiege().toString()
					+ ", " + reservation.getExtra().getIdExtra().toString()
					+ ", " + reservation.getQuantite().toString() + ")";
				connexion.insert(requete);
			} catch (SQLException ex) {
				
			}
		}
		return true;
	}
	
	public void supprimerReservation(Reservation reservation) {
		String requete = null;
		try {
			requete = "DELETE FROM reservation_siege_extra WHERE idReservation=" + reservation.getIdReservation().toString();
			connexion.insert(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
