package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.TypeSalle;

public class TypeSalleCRUD {

	private static BDD connexion;
	private static TypeSalleCRUD singleton;

	public static TypeSalleCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new TypeSalleCRUD();
		}
		return singleton;
	}

	public TypeSalleCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public TypeSalle getById(Integer id){
		TypeSalle typeSalle = null;
		String query = "SELECT * FROM TypeSalle WHERE idTypeSalle="+id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idTypeSalle = result.getInt("idTypeSalle");
				String libelleTypeSalle = result.getString("libelleTypeSalle");
				typeSalle = new TypeSalle(idTypeSalle, libelleTypeSalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeSalle;
	}
	
	public ArrayList<TypeSalle> getListAll() {
		ArrayList<TypeSalle> listTypeSalle = new ArrayList<>();
		String requete = null;
		try {
			requete = "SELECT * FROM TypeSalle";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idTypeSalle = resultat.getInt("idTypeSalle");
				String libelleTypeSalle = resultat.getString("libelleTypeSalle");
				TypeSalle typeSalle = new TypeSalle(idTypeSalle, libelleTypeSalle);
				listTypeSalle.add(typeSalle);
			}
		} catch(Exception ex) {
			
		}
		return listTypeSalle;
	}
}
