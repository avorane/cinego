package modele.entite;

public class TypeUtilisateur {
	private Integer idTypeUtilisateur;
	private String libelleTypeUtilisateur;
	
	public TypeUtilisateur(Integer idTypeUtilisateur, String libelleTypeUtilisateur) {
		this.idTypeUtilisateur = idTypeUtilisateur;
		this.libelleTypeUtilisateur = libelleTypeUtilisateur;
	}
	
	public Integer getIdTypeUtilisateur() {
		return this.idTypeUtilisateur;
	}
	
	public void setIdTypeUtilisateur(Integer idTypeUtilisateur) {
		this.idTypeUtilisateur = idTypeUtilisateur;
	}
	
	public String getLibelleUtilisateur() {
		return this.libelleTypeUtilisateur;
	}
	
	public void setLibelleTypeUtilisateur(String libelleTypeUtilisateur) {
		this.libelleTypeUtilisateur = libelleTypeUtilisateur;
	}
}
