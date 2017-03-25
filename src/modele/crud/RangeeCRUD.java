package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Rangee;
import modele.entite.Salle;

public class RangeeCRUD {
	private static BDD connexion;
	private static RangeeCRUD singleton;
	
	public static RangeeCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new RangeeCRUD();
		}
		return singleton;
	}

	public RangeeCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public Rangee getById(Integer id){
		Rangee rangee = null;
		String query = "SELECT * FROM Rangee WHERE idRangee="+Integer.toString(id);
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Boolean active = result.getBoolean("active");
				Salle salle = SalleCRUD.getSingleton().getById(result.getInt("idSalle"));
				rangee = new Rangee(id, active, salle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete SiegeCRUD getById");
		}
		return rangee;
	}
	
	public ArrayList<Rangee> getListAll() {
		String requete = null;
		ArrayList<Rangee> listRangee = new ArrayList<>();
		try {
			requete = "SELECT * FROM Rangee";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idRangee = resultat.getInt("idRangee");
				Boolean active = resultat.getBoolean("active");
				Salle salle = SalleCRUD.getSingleton().getById(resultat.getInt("idSalle"));
				Rangee rangee = new Rangee(idRangee, active, salle);
				listRangee.add(rangee);
			}
		} catch (SQLException ex) {
			
		}
		return listRangee;
	}
	
	public ArrayList<Rangee> getListByIdSalle(Integer idSalle) {
		String requete = null;
		ArrayList<Rangee> listRangee = new ArrayList<>();
		try {
			requete = "SELECT * FROM Rangee WHERE idSalle=" + Integer.toString(idSalle);
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idRangee = resultat.getInt("idRangee");
				Boolean active = resultat.getBoolean("active");
				Salle salle = SalleCRUD.getSingleton().getById(resultat.getInt("idSalle"));
				Rangee rangee = new Rangee(idRangee, active, salle);
				listRangee.add(rangee);
			}
		} catch (SQLException ex) {
			System.out.println("Erreur execution requete SiegeCRUD getListByIdSalle");
			ex.printStackTrace(); 
		}
		return listRangee;
	}
	
	public Integer creerRangee(Rangee rangee) {
		Integer idRangee = 0;
		String requete = null;
		try {
			requete = "INSERT INTO Rangee (active, idSalle) VALUES (true, " + rangee.getSalle().getIdSalle().toString() + ")";
			idRangee = connexion.insert(requete);
		} catch (SQLException ex) {
			
		}
		return idRangee;
	}
	
	public void modifierSiege(Rangee rangee) {
		String requete = null;
		try {
			requete = "UPDATE Rangee "
					+ " SET active=" + rangee.getActive().toString()
					+ ", idSalle=" + rangee.getSalle().getIdSalle().toString()
					+ " WHERE idRangee=" + rangee.getIdRangee().toString();
			connexion.modifier(requete);
		} catch (SQLException ex) {
			
		}
	}
}
