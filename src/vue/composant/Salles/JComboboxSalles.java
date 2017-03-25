package vue.composant.Salles;

import java.util.ArrayList;

import javax.swing.JComboBox;

import modele.crud.SalleCRUD;
import modele.entite.Salle;

public class JComboboxSalles extends JComboBox<String>{
	private static final long serialVersionUID = 13L; 
	private ArrayList<Salle> listSalles;
	
	public JComboboxSalles() {
		super();
		this.listSalles = new ArrayList<>();
		this.listSalles.addAll(SalleCRUD.getSingleton().getListAll());
		construireCombobox();
	}
	
	public void construireCombobox() {
		for (Salle salle : listSalles) {
			this.addItem(salle.getLibelleSalle());
		}
	}
	
	public void setSalle(Salle salleChoisit) {
		for (int index = 0; index < listSalles.size(); index++) {
			if (listSalles.get(index).getIdSalle() == salleChoisit.getIdSalle()) {
				setSelectedIndex(index);
			}
		}
	}
	
	public Salle getSalle() {
		return listSalles.get(getSelectedIndex());
	}
	
	public void supprimerSalle(Integer index) {
		listSalles.remove(index);
		this.removeAll();
		construireCombobox();
		this.revalidate();
		this.repaint();
	}
}
