package vue.composant.RenduSeances;

import java.util.EventListener;

import modele.entite.Seance;

public interface ModificationListener extends EventListener {
	public void SeanceEnCoursModification(Seance seance);
	public void SeanceChoisie(Seance seance);
}