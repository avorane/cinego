package modele.entite;

public class ReservationSiegeTarif {
	private Reservation reservation;
	private Siege siege;
	private Tarif tarif;
	
	public ReservationSiegeTarif(Reservation reservation, Siege siege, Tarif tarif) {
		this.reservation = reservation;
		this.siege = siege;
		this.tarif = tarif;
	}
	
	public Reservation getReservation() {
		return this.reservation;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public Siege getSiege() {
		return this.siege;
	}
	
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	
	public Tarif getTarif() {
		return this.tarif;
	}
	
	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
}
