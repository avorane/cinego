package modele.entite;

public class Siege {
	private Integer idSiege;
	private TypeSiege type;
	private Rangee rangee;
	private Boolean active;
	
	public Siege(Integer idSiege, TypeSiege type, Rangee rangee, Boolean active) {
		this.idSiege = idSiege;
		this.type = type;
		this.rangee = rangee;
		this.active = active;
	}
	
	public Siege(TypeSiege type, Rangee rangee, Boolean active) {
		this.type = type;
		this.rangee = rangee;
		this.active = active;
	}
	
	public Siege(TypeSiege type, Boolean active) {
		this.type = type;
		this.active = active;
	}

	public Integer getIdSiege() {
		return this.idSiege;
	}
	
	public void setIdSiege(Integer idSiege) {
		this.idSiege = idSiege;
	}
	
	public TypeSiege getTypeSiege() {
		return this.type;
	}
	
	public void setHandicape(TypeSiege type) {
		this.type = type;
	}
	
	public Rangee getRangee() {
		return this.rangee;
	}
	
	public void setRangee(Rangee rangee) {
		this.rangee = rangee;
	}
	
	public Boolean getActive() {
		return this.active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
}
