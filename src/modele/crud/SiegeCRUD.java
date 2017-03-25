package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.BDD;
import modele.entite.Rangee;
import modele.entite.Salle;
import modele.entite.Siege;
import modele.entite.TypeSiege;

public class SiegeCRUD {
	private static BDD connexion;
	private static SiegeCRUD singleton;
	
	public static SiegeCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new SiegeCRUD();
		}
		return singleton;
	}

	public SiegeCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public SiegeCRUD(BDD connexion) {
		SiegeCRUD.connexion = connexion;
	}

	public Siege getById(Integer id){
		Siege siege = null;
		String query = "SELECT * FROM Siege WHERE idSiege=" + id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				TypeSiege typeSiege = TypeSiegeCRUD.getSingleton().getById(result.getInt("idTypeSiege"));
				Rangee rangee = RangeeCRUD.getSingleton().getById(result.getInt("idRangee"));
				Boolean active = result.getBoolean("active");
				siege = new Siege(id, typeSiege, rangee, active);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete SiegeCRUD getById");
		}
		return siege;
	}
	
	public ArrayList<Siege> getListAll() {
		String requete = null;
		ArrayList<Siege> listSiege = new ArrayList<>();
		try {
			requete = "SELECT * FROM Siege";
			ResultSet resultat = connexion.query(requete);
			if (resultat.getFetchSize() > 0) {
				while(resultat.next()) {
					Integer idSiege = resultat.getInt("idSiege");
					Siege siege = getById(idSiege);
					listSiege.add(siege);
				}
			} else {
				System.out.println("Aucune porte");
			}
		} catch (SQLException ex) {
			System.out.println("Erreur execution requete SiegeCRUD getListAll");
			ex.printStackTrace(); 
		}
		return listSiege;
	}
	
	public ArrayList<Siege> getListByIdRangee(Rangee rangee) {
		String requete = null;
		ArrayList<Siege> listSiege = new ArrayList<>();
		try {
			requete = "SELECT * FROM Siege WHERE idRangee=" + rangee.getIdRangee().toString();
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idSiege = resultat.getInt("idSiege");
				Siege siege = getById(idSiege);
				listSiege.add(siege);
			}
		} catch (SQLException ex) {
			System.out.println("Erreur execution requete SiegeCRUD getListByIdSalle");
			ex.printStackTrace(); 
		}
		return listSiege;
	}
	
	public Integer creerSiege(Siege siege) {
		Integer idSiege = 0;
		String requete = null;
		try {
			requete = "INSERT INTO Siege (idTypeSiege, idRangee, active) VALUES ('" 
					+ siege.getTypeSiege().getIdTypeSiege().toString() + "', "
					+ siege.getRangee().getIdRangee().toString() + ", "
					+ siege.getActive().toString() +")";
			idSiege = connexion.insert(requete);
		} catch (SQLException ex) {
			
		}
		return idSiege;
	}
	
	public void modifierSiege(Siege siege) {
		String requete = null;
		try {
			requete = "UPDATE Siege"
					+ " SET idTypeSiege=" + siege.getTypeSiege().getIdTypeSiege().toString()
					+ ", idRangee=" + siege.getRangee().getIdRangee().toString()
					+ ", active=" + siege.getActive().toString()
					+ " WHERE idSiege=" + siege.getIdSiege().toString();
			connexion.modifier(requete);
		} catch (SQLException ex) {
			
		}
	}
}
