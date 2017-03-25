package controller.fenetre;

import modele.crud.UtilisateurCRUD;
import modele.entite.TypeUtilisateur;
import modele.entite.Utilisateur;

public class GestionUtilisateur {
	private Utilisateur utilisateur;
	
	public GestionUtilisateur() {
		
	}
	
	public Utilisateur chercherUtilisateur(String identifiant) {
		Utilisateur utilisateur = null;
		try {
			utilisateur = UtilisateurCRUD.getSingleton().getByNumero(identifiant);
			if (utilisateur != null) {
				this.utilisateur = utilisateur;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return utilisateur;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	public void mettreAJourUtilisateur(Utilisateur utilisateur) {
		UtilisateurCRUD.getSingleton().modifierUtilisateur(utilisateur);
	}
	
	public void creerUtilisateur(String nom, String prenom, String motDePasse, TypeUtilisateur typeUtilisateur) {
		Utilisateur utilisateur = new Utilisateur(motDePasse, nom, prenom, typeUtilisateur);
		Integer idNouvelleUtilisateur = UtilisateurCRUD.getSingleton().creerUtilisateur(utilisateur);
		utilisateur.setId(idNouvelleUtilisateur);
		String identifiant = idNouvelleUtilisateur.toString();
		Integer lengthId = identifiant.length();
		for (int i = lengthId; i <= 10; i++) {
			identifiant = "0" + identifiant;
		}
		utilisateur.setIdentifiantAbonne(identifiant);
		UtilisateurCRUD.getSingleton().modifierUtilisateur(utilisateur);
	}

}
