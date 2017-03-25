package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.BDD;
import modele.entite.Tarif;

public class TarifCRUD {
	
	private static BDD connexion;
	private static TarifCRUD singleton;

    public static synchronized TarifCRUD getSingleton() {
        if ( singleton == null ) {
        	singleton = new TarifCRUD();
        }
        return singleton;
    }

	private TarifCRUD( ) {
		connexion = BDD.getDbCon();
	}
	
	public Tarif getById(Integer id){
		Tarif tarif = new Tarif();
		String query = "SELECT * FROM Tarif WHERE idTarif=" + id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				tarif.setIdTarif(id);
				tarif.setLibelleTarif(result.getString("libelleTarif"));
				tarif.setPrix(result.getFloat("prix"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete TarifCRUD getById");
		}
		return tarif;
	}
	
	public ArrayList<Tarif> getListAll() {
		String requete = null;
		ArrayList<Tarif> listTarif = new ArrayList<>();
		try {
			requete = "SELECT * FROM Tarif";
			ResultSet resultat = connexion.query(requete);
			while (resultat.next()) {
				Integer idTarif = resultat.getInt("idTarif");
				String libelleTarif = resultat.getString("libelleTarif");
				Float prix = resultat.getFloat("prix");
				Tarif tarif = new Tarif(idTarif, libelleTarif, prix);
				listTarif.add(tarif);
			}
		} catch (SQLException ex) {
			System.out.println("Erreur dans la m�thode listAll de TarifCRUD ");
		}
		return listTarif;
	}
	
	public boolean creerTarif(String libelleTarif, Float prix) {
		boolean creationOK = false;
		String requete = null;
		try {
			requete = "INSERT INTO Tarif (libelleTarif, prix) VALUES ('" 
					+ libelleTarif + "', " 
					+ Float.toString(prix) + ")";
			if (connexion.insert(requete) == 0) {
				creationOK = true;;
				System.out.println("Le tarif a �t� ajout�");
			} else {
				System.out.println("l'ajout du tarif a �chou�");
			}
		} catch (SQLException ex) {
			System.out.println("l'ajout du tarif a �chou�");
		}	
		return creationOK;
	}
}
