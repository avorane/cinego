package modele.entite;

public class Tarif {
	private Integer idTarif;
	private String libelleTarif;
	private Float prix;
	
	public Tarif(Integer idTarif, String libelleTarif, Float prix) {
		this.idTarif = idTarif;
		this.libelleTarif = libelleTarif;
		this.prix = prix;
	}
	
	public Tarif() {

	}

	public Integer getIdTarif() {
		return this.idTarif;
	}
	
	public void setIdTarif(Integer idTarif) {
		this.idTarif = idTarif;
	}
	
	public String getLibelleTarif() {
		return this.libelleTarif;
	}
	
	public void setLibelleTarif(String libelleTarif) {
		this.libelleTarif = libelleTarif;
	}
	
	public Float getPrix() {
		return this.prix;
	}
	
	public void setPrix(Float prix) {
		this.prix = prix;
	}

}
