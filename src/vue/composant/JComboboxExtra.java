package vue.composant;

import java.util.ArrayList;

import javax.swing.JComboBox;

import modele.crud.ExtraCRUD;
import modele.entite.Extra;

public class JComboboxExtra extends JComboBox<String> {
	private ArrayList<Extra> listExtra;
	
	public JComboboxExtra() {
		this.listExtra = ExtraCRUD.getSingleton().getAll();
		for (Extra extra : listExtra) {
			addItem(extra.getLibelleExtra());
		}
	}
	
	public Extra getExtra() {
		return this.listExtra.get(this.getSelectedIndex());
	}
}
