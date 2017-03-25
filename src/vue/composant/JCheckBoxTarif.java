package vue.composant;

import javax.swing.JCheckBox;

import modele.entite.Tarif;

public class JCheckBoxTarif extends JCheckBox {
	private Tarif tarif;
	
	public JCheckBoxTarif(Tarif tarif) {
		super(tarif.getLibelleTarif());
		this.tarif = tarif;
	}
	
	public Tarif getTarif() {
		return this.tarif;
	}
}
