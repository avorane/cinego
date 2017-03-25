package modele.entite;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
	private Integer idReservation;
	private Calendar dateReservation;
	private Utilisateur utilisateur;
	private Seance seance;
	private Majoration majoration;
	
	public Reservation() {
	}
	
	public Reservation(Integer idReservation) {
		this.idReservation = idReservation;
	}
	
	public Reservation(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public Reservation(Integer idReservation, Calendar dateReservation, Utilisateur utilisateur, Seance seance, Majoration majoration) {
		this.idReservation = idReservation;
		this.dateReservation = dateReservation;
		this.majoration = majoration;
		this.seance = seance;
		this.utilisateur = utilisateur;
	}
	
	public Reservation(Calendar dateReservation, Utilisateur utilisateur, Seance seance, Majoration majoration) {
		this.dateReservation = dateReservation;
		this.majoration = majoration;
		this.seance = seance;
		this.utilisateur = utilisateur;
	}
	
	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Majoration getMajoration() {
		return majoration;
	}

	public void setMajoration(Majoration majoration) {
		this.majoration = majoration;
	}

	public Integer getIdReservation() {
		return this.idReservation;
	}
	
	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}
	
	public Calendar getDateReservation() {
		return this.dateReservation;
	}

	public void setDateReservation(Calendar dateReservation) {
		this.dateReservation = dateReservation;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
}
