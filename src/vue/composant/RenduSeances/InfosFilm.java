package vue.composant.RenduSeances;

import javax.swing.ImageIcon;

public class InfosFilm {
	private ImageIcon image;
	private String titre;
	private String synopsis;
	private Integer duree;
	
	public InfosFilm(String titre, ImageIcon image, String synopsis, Integer duree) {
		this.titre = titre;
		this.image = image;
		this.synopsis = synopsis;
		this.duree = duree;
	}
	
	public ImageIcon getImage() {
		return this.image;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public Integer getDuree() {
		return this.duree;
	}
}
