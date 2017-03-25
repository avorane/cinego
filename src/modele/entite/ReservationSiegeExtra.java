package modele.entite;

import modele.entite.Reservation;

public class ReservationSiegeExtra {
	private Reservation reservation;
	private Extra extra;
	private Siege siege;
	private Integer quantite;
	
	public ReservationSiegeExtra(Reservation reservation, Extra extra, Siege siege, Integer quantite) {
		this.reservation = reservation;
		this.extra = extra;
		this.siege = siege;
		this.quantite = quantite;
	}
	
	public Reservation getReservation() {
		return this.reservation;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public Extra getExtra() {
		return this.extra;
	}
	
	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	
	public Siege getSiege() {
		return this.siege;
	}
	
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	
	public Integer getQuantite() {
		return this.quantite;
	}
	
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
}
