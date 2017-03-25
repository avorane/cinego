package modele.crud;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.entite.Film;
import modele.BDD;

public class FilmCRUD {

	private static BDD connexion;
	private static FilmCRUD singleton;

    public static synchronized FilmCRUD getSingleton() {
        if ( connexion == null ) {
        	singleton = new FilmCRUD();
        }
        return singleton;
    }
	
	public FilmCRUD() {
		connexion = BDD.getDbCon();
	}
	
	public FilmCRUD(BDD connexion) {
		FilmCRUD.connexion = connexion;
	}
	
	public ArrayList<Film> getList() {
		ArrayList<Film> films = new ArrayList<>();
		String requete = "SELECT * FROM Film";
		try {
			ResultSet result = connexion.query(requete);
			while(result.next()) {
				Integer idFilm = result.getInt("idFilm");
				String idFilmWebService = result.getString("idFilmWebService");
				String titreFilm = result.getString("titreFilm");
				Integer duree = result.getInt("dureeFilm");
				Film film = new Film(idFilm, idFilmWebService, titreFilm, duree);
				films.add(film);
			}
		} catch(SQLException ex) {
			
		}
		return films;
	}

	public ArrayList<Film> getListByDate(Date date){
		ArrayList<Film> films = new ArrayList<Film>();
		String query = "SELECT * FROM Film WHERE dateFilm='"+date+"';";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Film film = getById(result.getInt("idFilm"));
				films.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete Film getListByDate");
		}
		return films;
	}
	
	public Film getById(Integer id){
		Film film = null;
		String query = "SELECT * FROM Film WHERE idFilm='"+Integer.toString(id)+"';";
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				String idWebService = result.getString("idFilmWebService");
				String titre = result.getString("titreFilm");
				Integer duree = result.getInt("dureeFilm");
				film = new Film(id, idWebService, titre, duree);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur execution requete Film getById");
		}
		return film;
	}
	
	public boolean creerFilm(Film film) {
		boolean creationOK = false;
		String requete = null;
		if( film.getTitreFilm() == null || film.getTitreFilm().trim().equals("")){
			System.out.println("Le film n a pas de titre");
		}else{
			try {
				requete = "INSERT INTO Film (titreFilm, idFilmWebService, dureeFilm) " + "VALUES ('" + film.getTitreFilm() + "', '" + film.getIdFilmWebService() + "', " + film.getDuree() + ")";
				if (connexion.insert(requete) == 0) {
					creationOK = true;
					System.out.println("Le film a �t� cr��");
				} else {
					System.out.println("La cr�ation du film a �chou�e");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}			
		}
		return creationOK;
	}

//	public boolean modifierFilm(Film film) {
//		boolean modificationOK = false;
//		String requete = null;
//		if( film.getTitreFilm() == null || film.getTitreFilm().trim().equals("")){
//			System.out.println("Le film n'a pas de titre");
//		} else if(film.getIdFilm() == null ){
//			System.out.println("Le film n'a pas d'ID");
//		}else{
//			try {
//				if(film.getDateFilm() == null){
//					requete = "UPDATE Film SET titreFilm=" + film.getTitreFilm() + " WHERE idFilm=" + Integer.toString(film.getIdFilm());		
//				}else{
//					requete = "UPDATE Film SET titreFilm=" + film.getTitreFilm() 
//					+ ", dateFilm=" + film.getDateFilm() 
//					+ " WHERE idFilm=" + Integer.toString(film.getIdFilm());	
//				}
//				if (connexion.insert(requete) == 0) {
//					modificationOK = true;
//					System.out.println("La modification du film s'est bien effectu�");
//				} else {
//					System.out.println("La modification du film a �chou�");
//				}
//				return modificationOK;
//			} catch (SQLException ex) {
//				System.out.println("La modification du film a �chou�");
//			}		
//		}
//		return modificationOK;
//	}
//	
//	public boolean modifierFilm(Integer idFilm, String titre, Date datesortie) {
//		boolean modificationOK = false;
//		if( titre == null || titre.trim().equals("")){
//			System.out.println("Le film n a pas de titre");
//		}else{
//			Film film = new Film();
//			film.setTitreFilm(titre);
//			film.setDateFilm(datesortie);
//			modificationOK = modifierFilm(film);
//		}
//		return modificationOK;
//	}
	
}