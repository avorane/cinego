package controller.fenetre;

import modele.crud.UtilisateurCRUD;
import modele.entite.Utilisateur;

public class Connexion {
	
	public Utilisateur seConnecter(String identifiantAbonne, String motDePasseAbonne ) {
		// Type "UtilisateurCRUD"  nom de la variable "utilisateurCRUD" = nouvel objet "UtilisateurCRUD"
		UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD();
		// Type"Utilisateur" apell� "retourUtilsiateur = fonction "recup�re l'indentifiant et le mdp abon�"
		Utilisateur retourUtilisateur = utilisateurCRUD.getByCredentials(identifiantAbonne, motDePasseAbonne);
	
		return retourUtilisateur;
		
	}
	
}
