package modele.entite;

public class Cinema {
	private Integer idCinema;
	private String libelleCinema;
	private Utilisateur utilisateur;
	
	public Cinema(Integer idCinema, String libelleCinema, Utilisateur utilisateur) {
		this.idCinema = idCinema;
		this.libelleCinema = libelleCinema;
		this.utilisateur = utilisateur;
	}
	
	public Integer getIdCinema() {
		return this.idCinema;
	}
	
	public void setIdCinema(Integer idCinema) {
		this.idCinema = idCinema;
	}
	
	public String getLibelleCinema() {
		return this.libelleCinema;
	}
	
	public void setLibelleCinema(String libelleCinema) {
		this.libelleCinema = libelleCinema;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
