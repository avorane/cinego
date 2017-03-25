package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import modele.BDD;
import modele.entite.TypeUtilisateur;
import modele.entite.Utilisateur;

public class UtilisateurCRUD {
	private static BDD connexion;
	private static UtilisateurCRUD singleton;

    public static synchronized UtilisateurCRUD getSingleton() {
        if ( connexion == null ) {
        	singleton = new UtilisateurCRUD();
        }
        return singleton;
    }
	
	public UtilisateurCRUD() {
		connexion = BDD.getDbCon();
	}

//	
//	public ArrayList<Utilisateur> getListByAdmin(Boolean admin){
//		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
//		String query = "SELECT FROM Utilisateur t1 JOIN TypeUtilisateur t2 ON t1.idTypeUtilisateur=t2.idTypeUtilisateur WHERE t2.libelleTypeUtilisateur='Administrateur'";
//		try {
//			ResultSet result  = connexion.query(query);
//			while (result.next()){
//				TypeUtilisateur typeUtilisateur = TypeSiegeCRUD.getSingleton().getById(id)
//				Utilisateur utilisateur = getById(result.getInt("idUtilisateur"));
//				utilisateurs.add(utilisateur);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return utilisateurs;
//	}

	public Utilisateur getByCredentials(String identifiant, String motdepasse){
		Utilisateur utilisateur = null;
		String query = "SELECT * FROM Utilisateur WHERE identifiantAbonne='"+identifiant+"' AND motdepasseAbonne='"+motdepasse+"'";
		try {
			ResultSet result  = connexion.query(query);
			while(result.next()) {
				TypeUtilisateur typeUtilisateur = TypeUtilisateurCRUD.getSingleton().getById(result.getInt("idTypeUtilisateur"));
				Integer idUtilisateur = result.getInt("idUtilisateur");
				String identifiantAbonne = result.getString("identifiantAbonne");
				String motDePasseAbonne = result.getString("motDePasseAbonne");
				String nomAbonne = result.getString("nomAbonne");
				String prenomAbonne = result.getString("prenomAbonne");
				Calendar dateInscription = Calendar.getInstance();
				dateInscription.setTimeInMillis(result.getTimestamp("dateInscription").getTime());
				utilisateur = new Utilisateur(idUtilisateur, identifiantAbonne, motDePasseAbonne, nomAbonne, prenomAbonne, dateInscription, typeUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public Utilisateur getById(Integer id){
		Utilisateur utilisateur = null;
		String query = "SELECT * FROM Utilisateur WHERE idUtilisateur="+id.toString();
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()) {
				TypeUtilisateur typeUtilisateur = TypeUtilisateurCRUD.getSingleton().getById(result.getInt("idTypeUtilisateur"));
				Integer idUtilisateur = result.getInt("idUtilisateur");
				String identifiantAbonne = result.getString("identifiantAbonne");
				String motDePasseAbonne = result.getString("motDePasseAbonne");
				String nomAbonne = result.getString("nomAbonne");
				String prenomAbonne = result.getString("prenomAbonne");
				Calendar dateInscription = Calendar.getInstance();
				dateInscription.setTimeInMillis(result.getTimestamp("dateInscription").getTime());
				utilisateur = new Utilisateur(idUtilisateur, identifiantAbonne, motDePasseAbonne, nomAbonne, prenomAbonne, dateInscription, typeUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public Utilisateur getByNumero(String numero){
		Utilisateur utilisateur = null;
		String query = "SELECT * FROM Utilisateur WHERE identifiantAbonne='" + numero + "'";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()) {
				TypeUtilisateur typeUtilisateur = TypeUtilisateurCRUD.getSingleton().getById(result.getInt("idTypeUtilisateur"));
				Integer idUtilisateur = result.getInt("idUtilisateur");
				String identifiantAbonne = result.getString("identifiantAbonne");
				String motDePasseAbonne = result.getString("motDePasseAbonne");
				String nomAbonne = result.getString("nomAbonne");
				String prenomAbonne = result.getString("prenomAbonne");
				Calendar dateInscription = Calendar.getInstance();
				dateInscription.setTimeInMillis(result.getTimestamp("dateInscription").getTime());
				utilisateur = new Utilisateur(idUtilisateur, identifiantAbonne, motDePasseAbonne, nomAbonne, prenomAbonne, dateInscription, typeUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public void modifierUtilisateur(Utilisateur utilisateur) {
		String requete = null;
		try {
			requete = "UPDATE Utilisateur SET nomAbonne='" + utilisateur.getNomAbonne() 
			+ "', prenomAbonne='" + utilisateur.getPrenomAbonne() 
			+ "', idTypeUtilisateur=" + utilisateur.getTypeUtilisateur().getIdTypeUtilisateur().toString()
			+ " WHERE idUtilisateur=" + utilisateur.getId().toString();
			connexion.modifier(requete);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Integer creerUtilisateur(Utilisateur utilisateur) {
		String requete = null;
		Integer idUtilisateur = 0;
		try {
			requete = "INSERT INTO Utilisateur (motDePasseAbonne, nomAbonne, prenomAbonne, dateInscription, active, idTypeUtilisateur) VALUES "
					+ "('" + utilisateur.getMotDePasse() +"'"
					+ ", '" + utilisateur.getNomAbonne() + "'"
					+ ", '" + utilisateur.getPrenomAbonne() + "'"
					+ ", '" + new Timestamp(Calendar.getInstance().getTimeInMillis()) + "'"
					+ ", true" 
					+ ", " + utilisateur.getTypeUtilisateur().getIdTypeUtilisateur().toString() + ")";
			idUtilisateur = connexion.insert(requete);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return idUtilisateur;
	}
}
