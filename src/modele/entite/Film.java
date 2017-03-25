package modele.entite;

public class Film {

	private Integer idFilm;
	private String idFilmWebService;
	private String titreFilm;
	private Integer duree;

	public Film(String idFilmWebService, String titreFilm, Integer duree) {
		this.idFilmWebService = idFilmWebService;
		this.titreFilm = titreFilm;
		this.duree = duree;		
	}
	
	
	public Film(Integer idFilm, String idFilmWebService, String titreFilm, Integer duree) {
		this(idFilmWebService, titreFilm, duree);
		this.idFilm = idFilm;
	}

	public Integer getIdFilm() {
		return idFilm;
	}
	
	public void setIdFilm(Integer idFilm) {
		this.idFilm = idFilm;
	}
	
	public String getTitreFilm() {
		return titreFilm;
	}
	
	public void setTitreFilm(String titreFilm) {
		this.titreFilm = titreFilm;
	}
	
	public String getIdFilmWebService() {
		return this.idFilmWebService;
	}
	
	public void setIdFilmWebService(String idFilmWebService) {
		this.idFilmWebService = idFilmWebService;
	}

	public Integer getDuree() {
		return this.duree;
	}
	
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
}
