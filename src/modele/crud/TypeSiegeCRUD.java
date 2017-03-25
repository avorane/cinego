package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Salle;
import modele.entite.Siege;
import modele.entite.TypeSiege;

public class TypeSiegeCRUD {
	private static BDD connexion;
	private static TypeSiegeCRUD singleton;
	
	public static TypeSiegeCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new TypeSiegeCRUD();
		}
		return singleton;
	}

	private TypeSiegeCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public TypeSiege getById(Integer id){
		TypeSiege typeSiege = null;
		String requete = "SELECT * FROM TypeSiege WHERE idTypeSiege=" + id.toString();
		try {
			ResultSet result  = connexion.query(requete);
			while(result.next()) {
				Integer idTypeSiege = result.getInt("idTypeSiege");
				String libelleTypeSiege = result.getString("libelleTypeSiege");
				typeSiege = new TypeSiege(idTypeSiege, libelleTypeSiege);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete SiegeCRUD getById");
		}
		return typeSiege;
	}
	
	public TypeSiege getByLibelle(String libelle) {
		TypeSiege typeSiege = null;
		String requete = "SELECT * FROM TypeSiege WHERE libelleTypeSiege='"+libelle+"'";
		try {
			ResultSet resultat  = connexion.query(requete);
			while(resultat.next()) {
				Integer idTypeSiege = resultat.getInt("idTypeSiege");
				String libelleTypeSiege = resultat.getString("libelleTypeSiege");
				typeSiege = new TypeSiege(idTypeSiege, libelleTypeSiege);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete SiegeCRUD getById");
		}
		return typeSiege;
	}
	
	public ArrayList<TypeSiege> getListAll() {
		String requete = null;
		ArrayList<TypeSiege> listTypeSiege = new ArrayList<>();
		try {
			requete = "SELECT * FROM TypeSiege";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idTypeSiege = resultat.getInt("idTypeSiege");
				String libelleTypeSiege = resultat.getString("libelleTypeSiege");
				TypeSiege typeSiege = new TypeSiege(idTypeSiege, libelleTypeSiege);
				listTypeSiege.add(typeSiege);
			}
		} catch (SQLException ex) {
			System.out.println("Erreur execution requete SiegeCRUD getListAll");
			ex.printStackTrace(); 
		}
		return listTypeSiege;
	}
}
