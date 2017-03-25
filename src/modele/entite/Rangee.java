package modele.entite;

public class Rangee {
	private Integer idRangee;
	private Boolean active;
	private Salle salle;
	
	public Rangee(Integer idRangee, Boolean active, Salle salle) {
		this.idRangee = idRangee;
		this.active = active;
		this.salle = salle;
	}
	
	public Rangee(Boolean active, Salle salle) {
		this.active = active;
		this.salle = salle;
	}
	
	public Rangee(Boolean active) {
		this.active = active;
	}
	
	public Integer getIdRangee() {
		return this.idRangee;
	}
	
	public void setIdRangee(Integer idRangee) {
		this.idRangee = idRangee;
	}
	
	public Boolean getActive() {
		return this.active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Salle getSalle() {
		return this.salle;
	}
	
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
}
