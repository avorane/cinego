package vue.composant;

import java.util.ArrayList;

import javax.swing.JComboBox;

import modele.crud.TypeUtilisateurCRUD;
import modele.entite.TypeUtilisateur;

public class JComboboxTypeUtilisateur extends JComboBox<String> {
	private static final long serialVersionUID = 178L; 
	private ArrayList<TypeUtilisateur> listTypeUtilisateur;
	
	public JComboboxTypeUtilisateur() {
		this.listTypeUtilisateur = new ArrayList<>();
		this.listTypeUtilisateur.addAll(TypeUtilisateurCRUD.getSingleton().getListAll());
		construireCombobox();
	}
	
	public void construireCombobox() {
		for (TypeUtilisateur typeUtilisateur : listTypeUtilisateur) {
			this.addItem(typeUtilisateur.getLibelleUtilisateur());
		}
	}
	
	public TypeUtilisateur getTypeUtilisateur() {
		return listTypeUtilisateur.get(this.getSelectedIndex());
	}
	
	public void selectTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
		for (int index = 0; index < listTypeUtilisateur.size(); index++) {
			if (listTypeUtilisateur.get(index).getIdTypeUtilisateur() == typeUtilisateur.getIdTypeUtilisateur()) {
				setSelectedIndex(index);
			}
		}
	}
}
