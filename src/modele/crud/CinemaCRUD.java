package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.BDD;
import modele.entite.Cinema;
import modele.entite.Utilisateur;

public class CinemaCRUD {

	private static BDD connexion;
	private static CinemaCRUD singleton;

	public static CinemaCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new CinemaCRUD();
		}
		return singleton;
	}

	public CinemaCRUD( ) {
		connexion = BDD.getDbCon();
	}
	
	public Cinema getById(Integer id){
		Cinema cinema = null;
		String query = "SELECT * FROM Cinema WHERE idCinema="+id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idCinema = result.getInt("idCinema");
				String libelleCinema = result.getString("libelleCinema");
				Utilisateur utilisateur = UtilisateurCRUD.getSingleton().getById(result.getInt("idUtilisateur"));
				cinema = new Cinema(idCinema, libelleCinema, utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cinema;
	}
}
