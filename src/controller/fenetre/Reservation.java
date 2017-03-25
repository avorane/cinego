package controller.fenetre;

import java.util.ArrayList;

import modele.entite.ReservationSiegeExtra;
import modele.entite.ReservationSiegeTarif;
import modele.entite.Tarif;

public class Reservation {
	private ArrayList<ReservationSiegeTarif> listReservationTarif;
	private ArrayList<ReservationSiegeExtra> listReservationExtra;
	private ReservationSiegeTarif choixReservationEnCours;
	
	public Reservation(ArrayList<ReservationSiegeTarif> listReservationTarif) {
		this.listReservationTarif = listReservationTarif;
		this.listReservationExtra = new ArrayList<>();
		choisirReservation();
	}
	
	public Reservation(ArrayList<ReservationSiegeTarif> listReservationTarif, ArrayList<ReservationSiegeExtra> listReservationExtra) {
		this(listReservationTarif);
		this.listReservationExtra = listReservationExtra;
	}
	
	public ReservationSiegeTarif getReservation() {
		return this.choixReservationEnCours;
	}
	
	public void setTarif(Tarif tarif) {
		this.choixReservationEnCours.setTarif(tarif);
	}
	
	public ArrayList<ReservationSiegeExtra> getListExtra() {
		return this.listReservationExtra;
	}
	
	public ArrayList<ReservationSiegeTarif> getListTarif() {
		return this.listReservationTarif;
	}
	
	public void addReservationExtra(ReservationSiegeExtra reservationExtra) {
		listReservationExtra.add(reservationExtra);
	}
	
	public Boolean reservationTerminee() {
		for (ReservationSiegeTarif reservationTarif : listReservationTarif) {
			if (reservationTarif.getTarif() == null) {
				return false;
			}
		}
		return true;
	}
	
	public void choisirReservation() {
		for (ReservationSiegeTarif reservationTarif : listReservationTarif) {
			if (reservationTarif.getTarif() == null) {
				this.choixReservationEnCours = reservationTarif;
				break;
			}
		}
	}
}
