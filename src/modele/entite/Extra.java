package modele.entite;

public class Extra {
	private Integer idExtra;
	private String libelleExtra;
	private Float prix;

	public Extra() {
	}
	
	public Extra(Integer idExtra, String libelleExtra, Float prix) {
		super();
		this.idExtra = idExtra;
		this.libelleExtra = libelleExtra;
		this.prix = prix;
	}
	
	public Integer getIdExtra() {
		return idExtra;
	}
	public void setIdExtra(Integer idExtra) {
		this.idExtra = idExtra;
	}
	public String getLibelleExtra() {
		return libelleExtra;
	}
	public void setLibelleExtra(String libelleExtra) {
		this.libelleExtra = libelleExtra;
	}
	public Float getPrix() {
		return prix;
	}
	public void setPrix(Float prix) {
		this.prix = prix;
	}
	
}
