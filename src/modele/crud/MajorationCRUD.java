package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.BDD;
import modele.entite.Majoration;

public class MajorationCRUD {
	private static BDD connexion;
	private static MajorationCRUD singleton;

    public static synchronized MajorationCRUD getSingleton() {
		if (singleton == null) {
			singleton = new MajorationCRUD();
		}
        return singleton;
    }
	
	public MajorationCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public Majoration getById(Integer id){
		Majoration majoration = new Majoration();
		String query = "SELECT * FROM Majoration WHERE idMajoration='"+Integer.toString(id)+"';";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				majoration.setIdMajoration(id);
				majoration.setLibelleMajoration(result.getString("libelleMajoration"));
				majoration.setValeurMajoration(result.getFloat("valeurMajoration"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete Majoration getById");
		}
		return majoration;
	}
	
	public ArrayList<Majoration> getListAll() {
		ArrayList<Majoration> listMajoration = new ArrayList<>();
		String requete = null;
		try {
			requete = "SELECT * FROM Majoration";
			ResultSet resultat = connexion.query(requete);
			if (resultat.getFetchSize() > 0) {
				while(resultat.next()) {
					Integer idMajoration = resultat.getInt("idMajoration");
					Majoration majoration = getById(idMajoration);
					listMajoration.add(majoration);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listMajoration;
	}
	
	public boolean creerMajoration(String libelleMajoration, float valeurMajoration) {
		boolean creationOK = false;
		String requete = null;
		try {
			requete = "INSERT INTO Majoration "
				+ "(libelleMajoration, valeurMajoration) "
				+ "VALUES "
				+ "('" + libelleMajoration + "', "
				+ Float.toString(valeurMajoration) + ")";
			if (connexion.insert(requete) == 0) {
				creationOK = true;
				System.out.println("La majoration a �t� rajout�e");
			} else {
				System.out.println("La majoration n'a pas �t� rajout�e");
			}
		} catch (SQLException ex) {
			System.out.println("La majoration n'a pas �t� rajout�e");
		}
		return creationOK;
	}
}
