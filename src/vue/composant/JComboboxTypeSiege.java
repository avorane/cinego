package vue.composant;

import java.util.ArrayList;

import javax.swing.JComboBox;

import modele.crud.TypeSiegeCRUD;
import modele.entite.TypeSiege;

public class JComboboxTypeSiege extends JComboBox<String>{
	private static final long serialVersionUID = 13L; 
	private ArrayList<TypeSiege> listTypeSiege;
	
	public JComboboxTypeSiege() {
		this.listTypeSiege = new ArrayList<>();
		this.listTypeSiege.addAll(TypeSiegeCRUD.getSingleton().getListAll());
		construireCombobox();
	}
	
	public void construireCombobox() {
		for (TypeSiege typeSiege : listTypeSiege) {
			this.addItem(typeSiege.getLibelleTypeSiege());
		}
	}
	
	public TypeSiege getTypeSiege() {
		return listTypeSiege.get(this.getSelectedIndex());
	}
}
