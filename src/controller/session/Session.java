package controller.session;

import modele.crud.CinemaCRUD;
import modele.crud.UtilisateurCRUD;
import modele.entite.Cinema;
import modele.entite.Utilisateur;

public class Session {
	private Utilisateur utilisateur;
	private static Session singleton;
	
	private Session() {
		this.utilisateur = null;
	}
	
	public static Session getSingleton() {
		if (singleton == null) {
			singleton = new Session();
		}
		return singleton;
	}
	
	public boolean isUtilisateur(String identifiant, String motDePasse) {
		boolean identificationOK = false;
		try {
			UtilisateurCRUD utilisateurCRUD = UtilisateurCRUD.getSingleton();
			Utilisateur utilisateurTest = utilisateurCRUD.getByCredentials(identifiant, motDePasse);
			if (!(utilisateurTest == null)) {
				singleton.utilisateur = utilisateurTest;
				identificationOK = true;
			}
		} catch(NumberFormatException ex) {
			
		}
		return identificationOK;
	}
	
	public boolean utilisateurConnecte() {
		boolean connecte = false;
		if (!(utilisateur == null)) {
			if (!utilisateur.getTypeUtilisateur().getLibelleUtilisateur().equals("Administrateur")) {
				connecte = true;
			}
		}
		return connecte;
	}
	
	public boolean administrateurConnecte() {
		boolean connecte = false;
		if (!(singleton.utilisateur == null)) {
			if (utilisateur.getTypeUtilisateur().getLibelleUtilisateur().equals("Administrateur")) {
				connecte = true;
			}
		}
		return connecte;
	}
	
	public void deconnecteUtilisateur() {
		if (!utilisateur.equals(null)) {
			utilisateur = null;
		}
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	public Cinema getCinema() {
		return CinemaCRUD.getSingleton().getById(1);
	}
	
}