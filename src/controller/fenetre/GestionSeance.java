package controller.fenetre;

import java.util.ArrayList;
import java.util.Calendar;

import modele.crud.SalleCRUD;
import modele.crud.SeanceCRUD;
import modele.entite.Salle;
import modele.entite.Seance;

public class GestionSeance {
	private Seance seanceEnModification;
	private ArrayList<Salle> listSalles;
	private Salle salleChoisie;
	
	public GestionSeance() {
		this.salleChoisie = null;
		this.seanceEnModification = null;
		this.listSalles = new ArrayList<>();		
		SalleCRUD salleCRUD = SalleCRUD.getSingleton();
		this.listSalles.addAll(salleCRUD.getListAll());
	}
	
	public ArrayList<Seance> changerDateSeances(Calendar dateSeances, Salle salle) {
		SeanceCRUD seanceCRUD = SeanceCRUD.getSingleton();
		return seanceCRUD.getListByDateSeanceAndSalle(dateSeances, salle);
	}
	
	public void seanceEnModification(Seance seance) {
		this.seanceEnModification = seance;
	}
	
	public void seanceModifiee(Seance seance) {
		SeanceCRUD.getSingleton().modifierSeance(seance);
	}
	
	public ArrayList<String> getListSalles() {
		ArrayList<String> listSallesString = new ArrayList<>();
		for (Salle salle : this.listSalles) {
			listSallesString.add(salle.getLibelleSalle());
		}
		return listSallesString;
	}
	
	public void setSalleChoisie(int indexSalle) {
		this.salleChoisie = listSalles.get(indexSalle);
		
	}
	
	public Salle getSalleChoisie() {
		return this.salleChoisie;
	}
	
	public void setSeanceEnModification(Seance seance) {
		this.seanceEnModification = seance;
	}
	
}