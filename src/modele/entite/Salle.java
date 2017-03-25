package modele.entite;

public class Salle {
	
	private Integer idSalle;
	private String libelleSalle;
	private Cinema cinema;
	private TypeSalle typeSalle;
	
	public Salle(Integer idSalle, String libelleSalle, Cinema cinema, TypeSalle typeSalle) {
		this.idSalle = idSalle;
		this.libelleSalle = libelleSalle;
		this.cinema = cinema;
		this.typeSalle = typeSalle;
	}
	
	public Salle(String libelleSalle, Cinema cinema, TypeSalle typeSalle) {
		this.libelleSalle = libelleSalle;
		this.cinema = cinema;
		this.typeSalle = typeSalle;
	}
	public Salle() {
		
	}
	
	public Salle(String libelleSalle) {
		this.libelleSalle = libelleSalle;
	}
	
	public Integer getIdSalle() {
		return idSalle;
	}
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	public String getLibelleSalle() {
		return libelleSalle;
	}
	public void setLibelleSalle(String libelleSalle) {
		this.libelleSalle = libelleSalle;
	}
	
	public Cinema getCinema() {
		return this.cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public TypeSalle getTypeSalle() {
		return this.typeSalle;
	}
	
	public void setTypeSalle(TypeSalle typeSalle) {
		this.typeSalle = typeSalle;
	}
	
}
