package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.BDD;
import modele.entite.Cinema;
import modele.entite.Salle;
import modele.entite.TypeSalle;

public class SalleCRUD {

	private static BDD connexion;
	private static SalleCRUD singleton;

	public static SalleCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new SalleCRUD();
		}
		return singleton;
	}

	public SalleCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public SalleCRUD(BDD connexion) {
		SalleCRUD.connexion = connexion;
	}

	public Salle getById(Integer id){
		Salle salle = null;
		String query = "SELECT * FROM Salle WHERE idSalle="+id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idSalle = result.getInt("idSalle");
				String libelleSalle = result.getString("libelleSalle");
				Cinema cinema = CinemaCRUD.getSingleton().getById(result.getInt("idCinema"));
				TypeSalle typeSalle = TypeSalleCRUD.getSingleton().getById(result.getInt("idTypeSalle"));
				salle = new Salle(idSalle, libelleSalle, cinema, typeSalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}

	public ArrayList<Salle> getListAll() {
		ArrayList<Salle> listeSalle = new ArrayList<>();
		String requete = null;
		try {
			requete = "SELECT * FROM Salle";
			ResultSet resultat = connexion.query(requete); 
			while(resultat.next()) {
				Salle salle = getById(resultat.getInt("idSalle"));
				listeSalle.add(salle);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listeSalle;
	}

	public Integer creerSalle(Salle salle) {
		Integer idSalle = 0;
		String requeteInsert = null;
		try {
			requeteInsert = "INSERT INTO Salle "
					+ "(libelleSalle, idTypeSalle, idCinema)"
					+ "VALUES "
					+ "('" + salle.getLibelleSalle() + "', "
					+ salle.getTypeSalle().getIdTypeSalle() + ", " +
					+ salle.getCinema().getIdCinema() + ")";
			idSalle = connexion.insert(requeteInsert); 
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}

		return idSalle;
	}
	
	public void modifierSalle(Salle salle) {
		String requeteUpdate = null;
		try {
			requeteUpdate = "UPDATE Salle "
					+ " SET libelleSalle='" + salle.getLibelleSalle() + "', "
					+ " idCinema=" + salle.getCinema().getIdCinema().toString() + ", "
					+ " idTypeSalle=" + salle.getTypeSalle().getIdTypeSalle().toString()
					+ " WHERE idSalle=" + salle.getIdSalle().toString();
			connexion.modifier(requeteUpdate); 
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
	}
	
	
	public Boolean supprimerSalle(Integer idSalle) {
		Boolean suppressionOK = false;
		String requeteSupression = null;
		try {
			requeteSupression = "DELETE FROM Salle " + " WHERE idSalle=" + idSalle;
			connexion.insert(requeteSupression);
				suppressionOK = true;

		} catch (SQLException ex) {
			ex.printStackTrace(); 
			suppressionOK = false;
		}
		return suppressionOK;
	}








}


