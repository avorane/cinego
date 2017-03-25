package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.TypeUtilisateur;;

public class TypeUtilisateurCRUD {
	private static BDD connexion;
	private static TypeUtilisateurCRUD singleton;
	
	public static TypeUtilisateurCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new TypeUtilisateurCRUD();
		}
		return singleton;
	}

	private TypeUtilisateurCRUD() {
		connexion = BDD.getDbCon();
	}

	public TypeUtilisateur getById(Integer id){
		TypeUtilisateur typeUtilisateur = null;
		String requete = "SELECT * FROM TypeUtilisateur WHERE idTypeUtilisateur="+Integer.toString(id);
		try {
			ResultSet result  = connexion.query(requete);
			while(result.next()) {
				Integer idTypeUtilisateur = result.getInt("idTypeUtilisateur");
				String libelleTypeUtilisateur = result.getString("libelleTypeUtilisateur");
				typeUtilisateur = new TypeUtilisateur(idTypeUtilisateur, libelleTypeUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete TypeUtilisateurCRUD getById");
		}
		return typeUtilisateur;
	}
	
	public ArrayList<TypeUtilisateur> getListAll() {
		String requete = null;
		ArrayList<TypeUtilisateur> listTypeUtilisateur = new ArrayList<>();
		try {
			requete = "SELECT * FROM TypeUtilisateur";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idTypeUtilisateur = resultat.getInt("idTypeUtilisateur");
				String libelleTypeUtilisateur = resultat.getString("libelleTypeUtilisateur");
				TypeUtilisateur typeUtilisateur = new TypeUtilisateur(idTypeUtilisateur, libelleTypeUtilisateur);
				listTypeUtilisateur.add(typeUtilisateur);
			}
		} catch (SQLException ex) {
			System.out.println("Erreur execution requete SiegeCRUD getListAll");
			ex.printStackTrace(); 
		}
		return listTypeUtilisateur;
	}
}
