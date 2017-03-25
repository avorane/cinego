package modele.entite;

public class Porte {
	private Integer idPorte;
	private Integer X;
	private Integer Y;
	private Boolean entree;
	private Boolean sortie;
	private Salle salle;
	
	public Porte(Integer idPorte, Integer X, Integer Y, Boolean entree, Boolean sortie, Salle salle) {
		this.idPorte = idPorte;
		this.X = X;
		this.Y = Y;
		this.entree = entree;
		this.sortie = sortie;
		this.salle = salle;
	}

	public Porte() {

	}
	
	public Integer getIdPorte() {
		return this.idPorte;
	}
	
	public void setIdPorte(Integer idPorte) {
		this.idPorte = idPorte;
	}
	
	public Integer getX() {
		return this.X;
	}
	
	public void setX(Integer X) {
		this.X = X;
	}
	
	public Integer getY() {
		return this.Y;
	}
	
	public void setY(Integer Y) {
		this.Y = Y;
	}
	
	public Boolean getEntree() {
		return this.entree;
	}
	
	public void setEntree(Boolean entree) {
		this.entree = entree;
	}
	
	public Boolean getSortie() {
		return this.sortie;
	}
	
	public void setSortie(Boolean sortie) {
		this.sortie = sortie;
	}
	
	public Salle getSalle() {
		return this.salle;
	}
	
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
}
