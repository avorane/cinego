package modele.entite;

public class TypeSiege {
	private Integer idTypeSiege;
	private String libelleTypeSiege;
	
	public TypeSiege(Integer idTypeSiege, String libelleTypeSiege) {
		this.idTypeSiege = idTypeSiege;
		this.libelleTypeSiege = libelleTypeSiege;
	}
	
	public Integer getIdTypeSiege() {
		return this.idTypeSiege;
	}
	
	public void setIdTypeSiege(Integer idTypeSiege) {
		this.idTypeSiege = idTypeSiege;
	}
	
	public String getLibelleTypeSiege() {
		return this.libelleTypeSiege;
	}
	
	public void setLibelleTypeSiege(String libelleTypeSiege) {
		this.libelleTypeSiege = libelleTypeSiege;
	}
}
