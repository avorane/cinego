package controller.fenetre;

import java.util.ArrayList;

import modele.crud.ReservationCRUD;
import modele.crud.ReservationSiegeExtraCRUD;
import modele.crud.ReservationSiegeTarifCRUD;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeExtra;
import modele.entite.ReservationSiegeTarif;

public class RecapCommande {
	private ArrayList<ReservationSiegeTarif> listSiegeTarif;
	private ArrayList<ReservationSiegeExtra> listSiegeExtra;
	
	public RecapCommande(ArrayList<ReservationSiegeTarif> listSiegeTarif, ArrayList<ReservationSiegeExtra> listSiegeExtra) {
		this.listSiegeExtra = listSiegeExtra;
		this.listSiegeTarif = listSiegeTarif;
	}
	
	public RecapCommande(Reservation reservation) {
		this.listSiegeExtra = ReservationSiegeExtraCRUD.getSingleton().getByIdReservation(reservation);
		this.listSiegeTarif = ReservationSiegeTarifCRUD.getSingleton().getByIdReservation(reservation);
	}
	
	public Object[][] getModel() {
		Object[][] model = new Object[this.listSiegeTarif.size() + this.listSiegeTarif.size()][6];
		Integer ligne = 0;
		for(ReservationSiegeTarif siegeTarif : listSiegeTarif) {
			model[ligne][0] = siegeTarif.getSiege().getIdSiege().toString();
			model[ligne][1] = siegeTarif.getTarif().getPrix().toString();
			model[ligne][2] = "";
			model[ligne][3] = "";
			model[ligne][4] = "0";
			model[ligne][5] = "";
			ligne++;
			for (ReservationSiegeExtra siegeExtra : listSiegeExtra) {
				if (siegeExtra.getSiege().getIdSiege() == siegeTarif.getSiege().getIdSiege()) {
					model[ligne][0] = "";
					model[ligne][1] = "";
					model[ligne][2] = siegeExtra.getExtra().getLibelleExtra();
					model[ligne][3] = siegeExtra.getExtra().getPrix().toString();
					model[ligne][4] = "0";
					model[ligne][5] = "";
					ligne++;
				}
			}
		}
		return model;
	}
	
	public void validerCommande() {
			ReservationCRUD.getSingleton().creerReservation(listSiegeTarif.get(0).getReservation());
			ReservationSiegeTarifCRUD.getSingleton().creerReservationSiegeTarif(listSiegeTarif);
			ReservationSiegeExtraCRUD.getSingleton().creerReservationSiegeExtra(listSiegeExtra);
	}
}
