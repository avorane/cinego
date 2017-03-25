package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Majoration;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeTarif;
import modele.entite.Seance;
import modele.entite.Siege;
import modele.entite.Tarif;

public class ReservationSiegeTarifCRUD {
	private static BDD connexion;
	private static ReservationSiegeTarifCRUD singleton;
	
    public static ReservationSiegeTarifCRUD getSingleton() {
		if (singleton == null) {
			singleton = new ReservationSiegeTarifCRUD();
		}
        return singleton;
    }
	
	public ReservationSiegeTarifCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public ArrayList<ReservationSiegeTarif> getByIdReservation(Reservation reservation){
		ArrayList<ReservationSiegeTarif> listReservationSiegeTarif = new ArrayList<>();
		String query = "SELECT * FROM reservation_siege_tarif WHERE idReservation=" + reservation.getIdReservation().toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idSiege = result.getInt("idSiege");
				Siege siege = SiegeCRUD.getSingleton().getById(idSiege);
				Integer idTarif = result.getInt("idTarif");
				Tarif tarif = TarifCRUD.getSingleton().getById(idTarif);
				ReservationSiegeTarif reservationSiegeTarif = new ReservationSiegeTarif(reservation, siege, tarif);
				listReservationSiegeTarif.add(reservationSiegeTarif);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listReservationSiegeTarif;
	}
	
	public Boolean creerReservationSiegeTarif(ArrayList<ReservationSiegeTarif> listReservationSiegeTarif) {
		for(ReservationSiegeTarif reservation : listReservationSiegeTarif) {
			String requete = null;
			try {
				requete = "INSERT INTO reservation_siege_tarif "
					+ "(idReservation, idSiege, idTarif) "
					+ "VALUES "
					+ "(" + reservation.getReservation().getIdReservation().toString()
					+ ", " + reservation.getSiege().getIdSiege().toString()
					+ ", 1)";
				connexion.insert(requete);
			} catch (SQLException ex) {
				
			}
		}
		return true;
	}
	
	public void supprimerReservation(Reservation reservation) {
		String requete = null;
		try {
			requete = "DELETE FROM reservation_siege_tarif WHERE idReservation=" + reservation.getIdReservation().toString();
			connexion.insert(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
