package modele.entite;

public class Propose {
	private Seance seance;
	private Tarif tarif;
	
	public Propose(Seance seance, Tarif tarif) {
		this.seance = seance;
		this.tarif = tarif;
	}
	
	public Seance getSeance() {
		return this.seance;
	}
	
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	
	public Tarif getTarif() {
		return this.tarif;
	}
	
	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
}
