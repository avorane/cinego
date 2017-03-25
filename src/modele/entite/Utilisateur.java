package modele.entite;

import java.util.Calendar;
import java.util.Date;

public class Utilisateur {

	private Integer idUtilisateur;
	private String identifiantAbonne;
	private String motDePasseAbonne;
	private String nomAbonne;
	private String prenomAbonne;
	private Calendar dateInscription;
	private TypeUtilisateur typeUtilisateur;
	
	public Utilisateur(Integer idUtilisateur, String identifiantAbonne, String motDePasseAbonne, String nomAbonne, String prenomAbonne, Calendar dateInscription, TypeUtilisateur typeUtilisateur) {
		this.idUtilisateur = idUtilisateur;
		this.identifiantAbonne = identifiantAbonne;
		this.nomAbonne = nomAbonne;
		this.motDePasseAbonne = motDePasseAbonne;
		this.prenomAbonne = prenomAbonne;
		this.dateInscription = dateInscription;
		this.typeUtilisateur = typeUtilisateur;
	}
	
	public Utilisateur(String motDePasseAbonne, String nomAbonne, String prenomAbonne, TypeUtilisateur typeUtilisateur) {
		this.nomAbonne = nomAbonne;
		this.motDePasseAbonne = motDePasseAbonne;
		this.prenomAbonne = prenomAbonne;
		this.typeUtilisateur = typeUtilisateur;
	}
	
	public Integer getId() {
		return idUtilisateur;
	}
	
	public void setId(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public String getIdentifiantAbonne() {
		return this.identifiantAbonne;
	}
	
	public void setIdentifiantAbonne(String identifiantAbonne) {
		this.identifiantAbonne = identifiantAbonne;
	}
	
	public String getMotDePasse() {
		return this.motDePasseAbonne;
	}
	
	public void setMotDePasse(String motDePasseAbonne) {
		this.motDePasseAbonne = motDePasseAbonne;
	}
	
	public String getNomAbonne() {
		return this.nomAbonne;
	}
	
	public void setNomAbonne(String nomAbonne) {
		this.nomAbonne = nomAbonne;
	}
	
	public String getPrenomAbonne() {
		return prenomAbonne;
	}
	
	public void setPrenomAbonne(String prenomAbonne) {
		this.prenomAbonne = prenomAbonne;
	}
	
	public Calendar getDateInscription() {
		return dateInscription;
	}
	
	public void setDateInscription(Calendar dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	public TypeUtilisateur getTypeUtilisateur() {
		return this.typeUtilisateur;
	}
	
	public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}
}
