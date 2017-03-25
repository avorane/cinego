package vue.composant.Salles;

import java.util.ArrayList;

import javax.swing.JComboBox;

import modele.crud.TypeSalleCRUD;
import modele.entite.TypeSalle;

public class JComboboxTypeSalle extends JComboBox<String> {
	private static final long serialVersionUID = 12L; 
	private ArrayList<TypeSalle> listTypeSalle;
	
	public JComboboxTypeSalle() {
		this.listTypeSalle = new ArrayList<>();
		this.listTypeSalle.addAll(TypeSalleCRUD.getSingleton().getListAll());
		for (TypeSalle typeSalle : listTypeSalle) {
			this.addItem(typeSalle.getLibelleTypeSalle());
		}
	}
	
	public TypeSalle getTypeSalle() {
		return listTypeSalle.get(this.getSelectedIndex());
	}
	
	public void setTypeSalle(TypeSalle typeSalle) {
		for (int index = 0; index < listTypeSalle.size(); index++) {
			if (typeSalle.getIdTypeSalle() == listTypeSalle.get(index).getIdTypeSalle()) {
				setSelectedIndex(index);
			}
		}
	}
}
