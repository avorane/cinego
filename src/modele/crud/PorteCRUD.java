package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.BDD;
import modele.entite.Porte;
import modele.entite.Salle;

public class PorteCRUD {
	private static BDD connexion;
	private static PorteCRUD singleton;

    public static synchronized PorteCRUD getSingleton() {
		if (singleton == null) {
			singleton = new PorteCRUD();
		}
        return singleton;
    }
	
	public PorteCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public PorteCRUD(BDD connexion) {
		PorteCRUD.connexion = connexion;
	}
	
	public Porte getById(Integer id){
		Porte porte = new Porte();
		String query = "SELECT * FROM porte WHERE idPorte='"+Integer.toString(id)+"';";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				porte.setIdPorte(id);
				porte.setX(result.getInt("X"));
				porte.setY(result.getInt("Y"));
				porte.setEntree(result.getBoolean("entre"));
				porte.setSortie(result.getBoolean("sortie"));
				SalleCRUD salleCRUD = SalleCRUD.getSingleton();
				Salle salle = salleCRUD.getById(result.getInt("idSalle"));
				porte.setSalle(salle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete Porte getById");
		}
		return porte;
	}
	
	public ArrayList<Porte> getListAll() {
		String requete = null;
		ArrayList<Porte> listPorte = new ArrayList<>();
		try {
			requete = "SELECT * FROM Porte";
			ResultSet resultat = connexion.query(requete);
			if (resultat.getFetchSize() > 0) {
				while(resultat.next()) {
					Integer idPorte = resultat.getInt("idPorte");
					Porte porte = getById(idPorte);
					listPorte.add(porte);
				}
			} else {
				System.out.println("Aucune porte");
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
			System.out.println("Erreur execution requete PorteCRUD listAll");
		}
		return listPorte;
	}
	
	public ArrayList<Porte> getListByIdSalle(Integer idSalle) {
		String requete = null;
		ArrayList<Porte> listPorte = new ArrayList<>();
		try {
			requete = "SELECT * FROM Porte" + " WHERE idSalle=" + idSalle;
			ResultSet resultat = connexion.query(requete);
			if (resultat.getFetchSize() > 0) {
				while(resultat.next()) {
					Integer idPorte = resultat.getInt("idPorte");
					Porte porte = getById(idPorte);
					listPorte.add(porte);
				}
			} else {
				System.out.println("Aucune porte pour cette salle");
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
			System.out.println("Erreur execution requete PorteCRUD listByIdSalle");
		}
		return listPorte;
	}
	
	public boolean creerPorte(int X, int Y, boolean entree, boolean sortie, Salle salle) {
		boolean creationOK = false;
		String requete = null;
		try {
			requete = "INSERT INTO Porte (X, Y, entree, sortie, idSalle)"
					+ " VALUES (" + Integer.toString(X)
					+ " ," + Integer.toString(Y)
					+ " ," + entree
					+ " ," + sortie
					+ " ," + salle.getIdSalle()
					+ ")";
			if (connexion.insert(requete) == 0) {
				creationOK = true;;
				System.out.println("La porte a été ajoutée.");
			} else {
				System.out.println("La porte n'a pas été ajoutée.");
			}
		} catch (SQLException ex) {
			System.out.println("La porte n'a pas été ajoutée.");
		}
		return creationOK;
	}

}
