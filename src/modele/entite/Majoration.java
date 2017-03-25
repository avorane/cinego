package modele.entite;

public class Majoration {
	private Integer idMajoration;
	private String libelleMajoration;
	private Float valeurMajoration;
	
	public Majoration() {
		
	}
	
	public Majoration(String libelleMajoration, Float valeurMajoration) {
		this.libelleMajoration = libelleMajoration;
		this.valeurMajoration = valeurMajoration;
	}
	
	public Majoration(int idMajoration, String libelleMajoration, Float valeurMajoration) {
		this.idMajoration = idMajoration;
		this.libelleMajoration = libelleMajoration;
		this.valeurMajoration = valeurMajoration;
	}
	
	public void setIdMajoration(Integer idMajoration) {
		this.idMajoration = idMajoration;
	}

	public void setValeurMajoration(Float valeurMajoration) {
		this.valeurMajoration = valeurMajoration;
	}

	public int getIdMajoration() {
		return this.idMajoration;
	}
	
	public String getLibelleMajoration() {
		return this.libelleMajoration;
	}
	
	public void setLibelleMajoration(String libelleMajoration) {
		this.libelleMajoration = libelleMajoration;
	}
	
	public float getValeurMajoration() {
		return this.valeurMajoration;
	}
	
	public void setValeurMajoration(float valeurMajoration) {
		this.valeurMajoration = valeurMajoration;
	}

}
