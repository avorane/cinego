package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Extra;

public class ExtraCRUD {
	private static BDD connexion;
	private static ExtraCRUD singleton;

    public static synchronized ExtraCRUD getSingleton() {
        if ( connexion == null ) {
        	singleton = new ExtraCRUD();
        }
        return singleton;
    }
	
	public ExtraCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public ExtraCRUD(BDD connexion) {
		ExtraCRUD.connexion = connexion;
	}
	
	public Extra getById(Integer id){
		Extra extra = new Extra();
		String query = "SELECT * FROM Extra WHERE idExtra='"+Integer.toString(id)+"';";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				extra.setIdExtra(result.getInt("idExtra"));
				extra.setLibelleExtra(result.getString("libelleExtra"));
				extra.setPrix(result.getFloat("prix"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete Extra getById");
		}
		return extra;
	}
	
	public ArrayList<Extra> getAll() {
		ArrayList<Extra> listExtra = new ArrayList<>();
		String requete = null;
		try {
			requete = "SELECT * FROM Extra";
			ResultSet result = connexion.query(requete);
			while(result.next()) {
				Extra extra = getById(result.getInt("idExtra"));
				listExtra.add(extra);
			}
		} catch (SQLException ex) {
			
		}
		return listExtra;
	}
	
}
