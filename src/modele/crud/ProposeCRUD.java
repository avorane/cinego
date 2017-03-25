package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.BDD;
import modele.entite.Propose;
import modele.entite.Seance;
import modele.entite.Tarif;

public class ProposeCRUD {
	private static BDD connexion;
	private static ProposeCRUD singleton;
	
	public static ProposeCRUD getSingleton() {
		if ( singleton == null ) {
			singleton = new ProposeCRUD();
		}
		return singleton;
	}

	public ProposeCRUD( ) {
		connexion = BDD.getDbCon();
	}

	public ArrayList<Propose> getByIdSeance(Seance seance){
		ArrayList<Propose> listPropose = new ArrayList<>();
		String query = "SELECT * FROM propose WHERE idSeance=" + seance.getIdSeance().toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Tarif tarif = TarifCRUD.getSingleton().getById(result.getInt("idTarif"));
				Propose propose = new Propose(seance, tarif);
				listPropose.add(propose);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete SiegeCRUD getById");
		}
		return listPropose;
	}
	
	public void insertTarifsPourSeance(Propose propose) {
		String requete = null;
		try {
			requete = "INSERT INTO propose "
					+ "(idSeance, idTarif) VALUES "
					+ "(" + propose.getSeance().getIdSeance().toString()
					+ ", " + propose.getTarif().getIdTarif() + ")";
			connexion.insert(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void supprimerPourSeance(Seance seance) {
		String requete = null;
		try {
			requete = "DELETE FROM propose WHERE idSeance=" + seance.getIdSeance().toString();
			connexion.modifier(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
