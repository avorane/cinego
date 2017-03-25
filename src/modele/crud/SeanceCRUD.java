package modele.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.BDD;
import modele.entite.Salle;
import modele.entite.Seance;
import modele.entite.Siege;
import modele.entite.Tarif;
import modele.entite.TypeSiege;
import modele.crud.FilmCRUD;
import modele.entite.Film;
import modele.entite.Rangee;

public class SeanceCRUD {
	
	private static BDD connexion;
	private static SeanceCRUD singleton;
	
    public static synchronized SeanceCRUD getSingleton() {
        if ( singleton == null ) {
        	singleton = new SeanceCRUD();
        }
        return singleton;
    }

	private SeanceCRUD( ) {
		connexion = BDD.getDbCon();
	}
	
	public Seance getById(Integer id){
		Seance seance = new Seance();
		String query = "SELECT * FROM Seance WHERE idSeance=" + id;
		try {
			ResultSet result  = connexion.query(query);
			while (result.next()){
				Integer idSeance = result.getInt("idSeance");
				Calendar dateSeance = GregorianCalendar.getInstance();
				dateSeance.setTimeInMillis(result.getTimestamp("dateSeance").getTime());
				FilmCRUD filmCRUD = FilmCRUD.getSingleton();
				Film film = filmCRUD.getById(result.getInt("idFilm"));
				SalleCRUD salleCRUD = SalleCRUD.getSingleton();
				Salle salle = salleCRUD.getById(result.getInt("idSalle"));
				seance = new Seance(idSeance, dateSeance, film, salle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}
	
	public ArrayList<Seance> getListAll() {
		String requete = null;
		ArrayList<Seance> listSeance = new ArrayList<>();
		try {
			requete = "SELECT * FROM Seance";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Integer idSeance = resultat.getInt("idSeance");
				Seance seance = getById(idSeance);
				listSeance.add(seance);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listSeance;
	}
	
	public ArrayList<Seance> getListByDateSeance(Calendar dateSeance) {
		String requete = null;
		ArrayList<Seance> listSeance = new ArrayList<>();
		try {
			Timestamp dateSeanceSQL = new Timestamp(dateSeance.getTimeInMillis());
			Timestamp limiteDate = new java.sql.Timestamp(new GregorianCalendar(dateSeance.get(Calendar.YEAR), dateSeance.get(Calendar.MONTH), dateSeance.get(Calendar.DAY_OF_MONTH), 23, 59).getTimeInMillis());
			requete = "SELECT * FROM Seance WHERE dateSeance BETWEEN '" + dateSeanceSQL.toString()+ "' AND '" + limiteDate.toString() + "'"; 
			ResultSet resultat = connexion.query(requete);
			while (resultat.next()) {
				Integer idSeance = resultat.getInt("idSeance");
				Seance seance = getById(idSeance);
				listSeance.add(seance);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listSeance;
	}
	
	public ArrayList<Seance> getListByDateSeanceAndSalle(Calendar dateSeance, Salle salle) {
		String requete = null;
		ArrayList<Seance> listSeance = new ArrayList<>();
		try {
			Calendar dateDebut = dateSeance;
			System.out.println(dateDebut.toString());
			Calendar dateFin = new GregorianCalendar(dateSeance.getTimeZone());
			dateFin.set(Calendar.HOUR, 23);
			dateFin.set(Calendar.MINUTE, 59);
			Timestamp dateDebutSQL = new Timestamp(dateDebut.getTimeInMillis());
			Timestamp dateFinSQL = new Timestamp(dateFin.getTimeInMillis());
			requete = "SELECT * FROM Seance"
					+ " WHERE (dateSeance BETWEEN '" + dateDebutSQL.toString() + "'" 
					+ " AND '" + dateFinSQL.toString() + "')"
					+ " AND idSalle=" + salle.getIdSalle().toString();
			System.out.println(requete);
			ResultSet resultat = connexion.query(requete);
			while (resultat.next()) {
				Integer idSeance = resultat.getInt("idSeance");
				Seance seance = getById(idSeance);
				listSeance.add(seance);
			}
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
		return listSeance;
	}
	
	public Integer creerSeance(Seance seance) {
		String requete = null;
		Integer idSeanceCree = null;
		try {
			requete = "INSERT INTO Seance "
				+ "(dateSeance, idFilm, idSalle) "
				+ "VALUES "
				+ "('" 
				+ new java.sql.Timestamp(seance.getDateSeance().getTimeInMillis()) + "', "
				+ Integer.toString(seance.getFilm().getIdFilm()) + ", "
				+ Integer.toString(seance.getSalle().getIdSalle())
				+ ")";
			idSeanceCree = connexion.insert(requete);
		} catch (SQLException ex) {
			
		}
		return idSeanceCree;
	}
	
	public void modifierSeance(Seance seance) {
		String requete = null;
		try {
			requete = "UPDATE Seance "
					+ "SET dateSeance='" + new java.sql.Timestamp(seance.getDateSeance().getTimeInMillis()) + "'"
					+ ", idFilm=" + seance.getFilm().getIdFilm()
					+ ", idSalle=" + seance.getSalle().getIdSalle().toString()
					+ " WHERE idSeance=" + seance.getIdSeance().toString();
			connexion.modifier(requete);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean supprimerSeance(Seance seance) {
		Boolean suppressionOK = false;
		String requete = null;
		try {
			requete = "DELETE FROM Seance "
					+ " WHERE idSeance=" + Integer.toString(seance.getIdSeance());
			if (connexion.insert(requete) == 0) {
				suppressionOK = true;
				System.out.println("La suppression de la s�ance s'est bien effectu�e");
			} else {
				System.out.println("La supression de la s�ance a �chou�e");
			}
		} catch (SQLException ex) {

		}
		return suppressionOK;
	}
	
	public ArrayList<Siege> getPlacesOccupees(Seance seance) {
		ArrayList<Siege> listSiegesOccupes = new ArrayList<>();
		String requete = null;
		try {
			requete = "SELECT t1.idSiege, t1.idTypeSiege, t1.idRangee, t1.active FROM Siege t1 "
					+ "JOIN reservation_siege_tarif t2 ON t2.idSiege=t1.idSiege "
					+ "JOIN Reservation t3 ON t2.idReservation=t3.idReservation "
					+ "WHERE t3.idSeance=" + seance.getIdSeance().toString();
			ResultSet resultat = connexion.query(requete);
			while (resultat.next()) {
				Integer idSiege = resultat.getInt("t1.idSiege");
				Integer idTypeSiege = resultat.getInt("t1.idTypeSiege");
				Integer idRangee = resultat.getInt("t1.idRangee");
				Boolean active = resultat.getBoolean("t1.active");
				TypeSiege typeSiege = TypeSiegeCRUD.getSingleton().getById(idTypeSiege);
				Rangee rangee = RangeeCRUD.getSingleton().getById(idRangee);
				Siege siege = new Siege(idSiege, typeSiege, rangee, active);
				listSiegesOccupes.add(siege);
			}
		} catch(Exception ex) {
			
		}
		return listSiegesOccupes;
	}
	
	public ArrayList<Seance> getListSeancesMemeMoment(Seance seance) {
		ArrayList<Seance> listSeancesBloquantes = new ArrayList<>();
		String requete = null;
		try {
			Timestamp debutSQL = new java.sql.Timestamp(seance.getDateSeance().getTimeInMillis());
			Integer duree = seance.getFilm().getDuree();
			Calendar dateFin = seance.getDateSeance();
			dateFin.add(Calendar.MINUTE, duree);
			Timestamp finSQL = new java.sql.Timestamp(dateFin.getTimeInMillis());
			requete = "SELECT * FROM Seance WHERE idSalle=" + seance.getDateSeance().toString() + " AND (dateSeance BETWEEN '" + debutSQL + "' AND '" + finSQL  + "')";
			ResultSet resultat = connexion.query(requete);
			while(resultat.next()) {
				Seance seanceBloquante = SeanceCRUD.getSingleton().getById(resultat.getInt("idSeance"));
				listSeancesBloquantes.add(seanceBloquante);
			}
		} catch(Exception ex) {
			
		}
		return listSeancesBloquantes;
	}
}