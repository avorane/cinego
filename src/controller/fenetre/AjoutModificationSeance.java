package controller.fenetre;

import java.util.ArrayList;

import modele.crud.SeanceCRUD;
import modele.entite.Seance;

public class AjoutModificationSeance {
	
	public void setSeance(Seance seance) {
		if (seance != null) {
			ArrayList<Seance> listSeancesBloquantes = new ArrayList<>();
			listSeancesBloquantes.addAll(SeanceCRUD.getSingleton().getListSeancesMemeMoment(seance));
			if (listSeancesBloquantes.size() == 0) {
				if (seance.getIdSeance() != null) {
					SeanceCRUD.getSingleton().modifierSeance(seance);
			
				}
			}
		}
	}
}
