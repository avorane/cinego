package vue.composant.Salles;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import controller.session.Session;
import modele.crud.RangeeCRUD;
import modele.crud.ReservationCRUD;
import modele.crud.ReservationSiegeTarifCRUD;
import modele.crud.SalleCRUD;
import modele.crud.SeanceCRUD;
import modele.crud.SiegeCRUD;
import modele.entite.Rangee;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeTarif;
import modele.entite.Salle;
import modele.entite.Seance;
import modele.entite.Siege;
import modele.entite.TypeSalle;

public class JSalle extends JPanel implements ModificationSalleListener {
	private ArrayList<JRangee> listJRangee;
	private ArrayList<JRangee> listJRangeeSupprimee;
	private Boolean admin;
	private Seance seance;
	private Salle salle;
	
	/**
	 * @wbp.parser.constructor
	 */
	public JSalle(Boolean admin) {
		this.admin = admin;
		this.listJRangee = new ArrayList<>();
		this.listJRangeeSupprimee = new ArrayList<>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.BLACK);
	}
	
	public JSalle(Boolean admin, Seance seance) {
		this(admin);
		this.seance = seance;
		this.salle = seance.getSalle();
		afficherSalle();
	}
	
	public JSalle(Boolean admin, Salle salle) {
		this(admin);
		this.salle = salle;
		afficherSalle();
	}
	
	public void ajouterRangee() {
		if (this.admin == true) {
			JRangee nouvelleRangee = null;
			if (salle == null) {
				nouvelleRangee = new JRangee(admin);
			} else {
				nouvelleRangee = new JRangee(salle, admin);
			}
			nouvelleRangee.ajouterEcouteurModification(this);
			this.add(nouvelleRangee);
			this.listJRangee.add(nouvelleRangee);
			revalidate();
			repaint();
			
		}
	}
	
	public Integer nbrePlacesselectionnes() {
		Integer nbrPlaces = 0;
		for (JRangee rangee : listJRangee) {
			for (JPlace place : rangee.getListPlaces()) {
				if(place.getEnCoursReservation()) {
					nbrPlaces++;
				}
			}
		}
		return nbrPlaces;
	}
	
	public void retirerRangee(Integer index) {
		if (this.admin == true) {
			//this.listJRangeeSupprimee.add(e)
			this.remove(this.listJRangee.get(index));
			this.listJRangee.remove(index);
			revalidate();
			repaint();
		}
	}
	
	public void ajouterRangee(ArrayList<JRangee> listJRangee) {
		this.listJRangee = listJRangee;
		for(JRangee rangee : listJRangee) {
			this.add(rangee);
			rangee.redessinerPlaces();
		}
		revalidate();
		repaint();
	}
	
	public ArrayList<JRangee> getListJRangee() {
		return listJRangee;
	}
	
	public void lireXML() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File("XML/" + salle.getIdSalle().toString() + ".xml"));
			Element racine = document.getRootElement();
			List<Element> listRangee = racine.getChildren("rangee");
			Iterator<Element> iteratorRangee = listRangee.iterator();
			while(iteratorRangee.hasNext()) {
				Element elRangee = (Element)iteratorRangee.next();
				Rangee rangee = RangeeCRUD.getSingleton().getById(Integer.parseInt(elRangee.getAttribute("id").getValue()));
				JRangee jrangee = new JRangee(rangee, this.admin);
				List<Element> listSiege = elRangee.getChildren("siege");
				Iterator<Element> iteratorSiege = listSiege.iterator();
				ArrayList<JPlace> listPlace = new ArrayList<>();
				while(iteratorSiege.hasNext()) {					
					Element elSiege = (Element)iteratorSiege.next();
					Siege siege = SiegeCRUD.getSingleton().getById(Integer.parseInt(elSiege.getText()));
					JPlace jplace = new JPlace(siege, this.admin);
					listPlace.add(jplace);
				}
				jrangee.setListPlaces(listPlace);
				listJRangee.add(jrangee);
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void creerOuModifierSalle(String libelleSalle, TypeSalle typeSalle) {
		Boolean creationOK = true;
		Integer idSalle = 0;
		Integer idRangee = 0;
		Integer idSiege = 0;
		try {
			if (salle.getIdSalle() == null) {
				if(this.listJRangee.size() > 0) {
					this.salle.setLibelleSalle(libelleSalle);
					this.salle.setTypeSalle(typeSalle);
					this.salle.setCinema(Session.getSingleton().getCinema());
					if ((idSalle = SalleCRUD.getSingleton().creerSalle(salle)) > 0) {
						salle.setIdSalle(idSalle);
							for (JRangee rangee : listJRangee) {
								rangee.getRangee().setSalle(salle);
								if ((idRangee = RangeeCRUD.getSingleton().creerRangee(rangee.getRangee())) > 0) {
									rangee.getRangee().setIdRangee(idRangee);
									if (rangee.getListPlaces().size() > 0) {
										for (JPlace place : rangee.getListPlaces()) {
											place.getSiege().setRangee(rangee.getRangee());
											if ((idSiege = SiegeCRUD.getSingleton().creerSiege(place.getSiege())) > 0) {
												place.getSiege().setIdSiege(idSiege);
												
											} else {
												creationOK = false;
											}
										}
									}
								} else {
									creationOK = false;
								}
							}
					} else {
						creationOK = false;
					}
				}
			} else {
				salle.setLibelleSalle(libelleSalle);
				salle.setTypeSalle(typeSalle);
				SalleCRUD.getSingleton().modifierSalle(salle);
				for (JRangee rangee : listJRangeeSupprimee) {
					if (rangee.getRangee().getIdRangee() != null) {
						for(JPlace place : rangee.getListPlaces()) {
							if(place.getSiege().getIdSiege() != null) {
								place.getSiege().setActive(false);
								SiegeCRUD.getSingleton().modifierSiege(place.getSiege());
							}
						}
						rangee.getRangee().setActive(false);
						RangeeCRUD.getSingleton().modifierSiege(rangee.getRangee());
					}
				}
				for (JRangee rangee : listJRangee) {
					if (rangee.getRangee().getIdRangee() == null) {
						rangee.getRangee().setIdRangee(RangeeCRUD.getSingleton().creerRangee(rangee.getRangee()));
					}
					for (JPlace place : rangee.getListPlacesSupprimees()) {
						if (place.getSiege().getIdSiege() != null) {
							place.getSiege().setActive(false);
							SiegeCRUD.getSingleton().modifierSiege(place.getSiege());
						}
					}
					for(JPlace place : rangee.getListPlaces()) {
						if(place.getSiege().getIdSiege() == null) {
							place.getSiege().setRangee(rangee.getRangee());
							place.getSiege().setIdSiege(SiegeCRUD.getSingleton().creerSiege(place.getSiege()));
						}
					}
				}
			}
				
				
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		if (creationOK) {
			try {
				genererXML();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void genererXML() {
		try {
			Element racine = new Element("salle");
			Attribute idSalle = new Attribute("id", salle.getIdSalle().toString());
			racine.setAttribute(idSalle);
			Document document = new Document(racine);
			for (JRangee rangee : this.listJRangee) {
				Element newRangee = new Element("rangee");
				Attribute idRangee = new Attribute("id", rangee.getRangee().getIdRangee().toString());
				newRangee.setAttribute(idRangee);
				for (JPlace place : rangee.getListPlaces()) {
					Element newSiege = new Element("siege");
					Attribute typeSiege = new Attribute("type", place.getSiege().getTypeSiege().getLibelleTypeSiege());
					newSiege.setAttribute(typeSiege);
					newSiege.setText(place.getSiege().getIdSiege().toString());
					newRangee.addContent(newSiege);
				}
				racine.addContent(newRangee);
			}
			XMLOutputter fichierXML = new XMLOutputter(Format.getPrettyFormat());
			fichierXML.output(document, new FileOutputStream("./XML/" + salle.getIdSalle().toString() + ".xml"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void afficherSalle() {
		try {
			lireXML();
			for(JRangee rangee : listJRangee) {
				rangee.ajouterEcouteurModification(this);
				add(rangee);
				rangee.afficherPlaces();
			}
			afficherPlacesOccupees();
			revalidate();
			repaint();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void afficherPlacesOccupees() {
		ArrayList<Siege> placesOccupees = SeanceCRUD.getSingleton().getPlacesOccupees(seance);
		for (JRangee jrangee : listJRangee) {
			for (JPlace place : jrangee.getListPlaces()) {
				for (Siege siegeOccupe : placesOccupees) {
					if(place.getSiege().getIdSiege().equals(siegeOccupe.getIdSiege())) {
						place.setLibre(false);
					}
				}
			}
		}
	}
	
	public ArrayList<ReservationSiegeTarif> faireReservation() {
		Reservation reservation = new Reservation(new GregorianCalendar(), Session.getSingleton().getUtilisateur(), seance, null);
		ArrayList<ReservationSiegeTarif> listReservationSiegeTarif = new ArrayList<>();
		for (JRangee rangee : listJRangee) {
			for (JPlace place : rangee.getListPlaces()) {
				if (place.getEnCoursReservation()) {
					listReservationSiegeTarif.add(new ReservationSiegeTarif(reservation, place.getSiege(), null));
				}
			}
		}
		return listReservationSiegeTarif;
	}
	
	public void selectionAutomatique(Integer nbrPlaces, Integer nbrPlacesHandicape) {
		for (JRangee rangee : listJRangee) {
			ArrayList<JPlace> listPlacesRangee = rangee.getListPlaces();
			for(int indexPlace = 0; indexPlace < listPlacesRangee.size(); indexPlace++) {
				JPlace placeEnCours = listPlacesRangee.get(indexPlace);
				ArrayList<JPlace> placesSelectionnees = new ArrayList<>();
				ArrayList<JPlace> placesHandicapeSelectionnees = new ArrayList<>();
				if(placeEnCours.getNormale()) {
					placesSelectionnees.add(placeEnCours);
				} else if (placeEnCours.getHandicape()) {
					placesHandicapeSelectionnees.add(placeEnCours);
				}
				if (listPlacesRangee.get(indexPlace).getLibre()) {
					int indexPlaceSuivante = indexPlace + 1;
					while (indexPlaceSuivante < listPlacesRangee.size()) {
						JPlace placeEtudiee = listPlacesRangee.get(indexPlaceSuivante);
						if (placeEtudiee.getLibre() && placeEtudiee.getNormale()) {
							placesSelectionnees.add(placeEtudiee);
						} else if (placeEtudiee.getLibre() && placeEtudiee.getHandicape()) {
							placesSelectionnees.add(placeEtudiee);
						}
						if ((placesSelectionnees.size() == (nbrPlaces - nbrPlacesHandicape)) && placesHandicapeSelectionnees.size() == nbrPlacesHandicape) {
							for (JPlace place : placesSelectionnees) {
								place.setEnCoursReservation();
							}
							for (JPlace place : placesHandicapeSelectionnees) {
								place.setEnCoursReservation();
							}
							return;
						}
						indexPlaceSuivante++;
					}
				}
			}
		}
	}

	@Override
	public void RangeeSupprimer(JRangee rangee) {
		for(Integer index = 0; index < listJRangee.size(); index++) {
			if(listJRangee.get(index).equals(rangee)) {
				if(rangee.getRangee().getIdRangee() != null) {
					listJRangeeSupprimee.add(rangee);
				}
				retirerRangee(index);
			}
		}		
	}

	@Override
	public void PlaceSupprimee(JPlace place) {
		// TODO Auto-generated method stub
		
	}
}
