package modele.entite;

import java.util.Calendar;

public class Seance {
	private Integer idSeance;
	private Calendar dateSeance;
	private Film film;
	private Salle salle;
	
	public Seance() {

	}
	
	public Seance(Calendar dateSeance, Film film, Salle salle) {
		this.dateSeance = dateSeance;
		this.film = film;
		this.salle = salle;
	}
	
	public Seance(Integer idSeance, Calendar dateSeance, Film film, Salle salle) {
		this(dateSeance, film, salle);
		this.idSeance = idSeance;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	public Integer getIdSeance() {
		return this.idSeance;
	}
	
	public void setIdSeance(Integer idSeance) {
		this.idSeance = idSeance;
	}
	
	public Calendar getDateSeance() {
		return this.dateSeance;
	}
	
	public void setDateSeance(Calendar dateSeance) {
		this.dateSeance = dateSeance;
	}
	
	public Film getFilm() {
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
}
