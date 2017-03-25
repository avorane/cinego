package modele.entite;

public class TypeSalle {
	private Integer idTypeSalle;
	private String libelleTypeSalle;
	
	public TypeSalle(Integer idTypeSalle, String libelleTypeSalle) {
		this.idTypeSalle = idTypeSalle;
		this.libelleTypeSalle = libelleTypeSalle;
	}
	
	public Integer getIdTypeSalle() {
		return this.idTypeSalle;
	}
	
	public void setIdTypeSalle(Integer idTypeSalle) {
		this.idTypeSalle = idTypeSalle;
	}
	
	public String getLibelleTypeSalle() {
		return this.libelleTypeSalle;
	}
	
	public void setLibelleTypeSalle(String libelleTypeSalle) {
		this.libelleTypeSalle = libelleTypeSalle;
	}
}
