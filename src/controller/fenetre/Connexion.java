package controller.fenetre;

import modele.crud.UtilisateurCRUD;
import modele.entite.Utilisateur;

public class Connexion {
	
	public Utilisateur seConnecter(String identifiantAbonne, String motDePasseAbonne ) {
		// Type "UtilisateurCRUD"  nom de la variable "utilisateurCRUD" = nouvel objet "UtilisateurCRUD"
		UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD();
		// Type"Utilisateur" apellé "retourUtilsiateur = fonction "recupére l'indentifiant et le mdp aboné"
		Utilisateur retourUtilisateur = utilisateurCRUD.getByCredentials(identifiantAbonne, motDePasseAbonne);
	
		return retourUtilisateur;
		
	}
	
}
