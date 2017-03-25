package vue.composant.Salles;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import modele.crud.RangeeCRUD;
import modele.entite.Rangee;
import modele.entite.Salle;
import modele.entite.Siege;
import vue.composant.RenduSeances.ModificationListener;

public class JRangee extends JPanel implements ModificationSalleListener{
	private ArrayList<JPlace> listSiegeDansRangee;
	private ArrayList<JPlace> listJPlaceSupprimee;
	private JButton btnPlus;
	private JButton btnMoins;
	private JPanel panelBtn;
	private JPanel panelRangee;
	private Boolean admin;
	private Rangee rangee;
	private Salle salle;
	private ArrayList<ModificationSalleListener> ecouteursSuppressionRangee = new ArrayList<>();
	
	public JRangee(Boolean admin) {
		this.setLayout(new GridLayout(2, 1));
		this.admin = admin;
		this.rangee = new Rangee(true);
		this.listSiegeDansRangee = new ArrayList<>();
		this.listJPlaceSupprimee = new ArrayList<>();
		this.btnMoins = new JButton("Retirer rangée");
		this.btnPlus = new JButton("Ajouter siège");
		this.panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.panelBtn.setBackground(Color.BLACK);
		this.panelRangee = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		this.panelRangee.setBackground(Color.black);
		if (admin) {
			this.btnMoins.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					supprimerRangee();
				}
			});
			this.btnPlus.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ajouterSiege();	
				}
			});
			this.panelBtn.add(this.btnMoins);
			this.panelBtn.add(this.btnPlus);
			this.add(this.panelBtn);
		}
		this.add(this.panelRangee);
		setBackground(Color.black);;
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	
	public JRangee(Rangee rangee, Boolean admin) {
		this(admin);
		this.rangee = rangee;
	}
	
	public JRangee(Salle salle, Boolean admin) {
		this(admin);
		this.salle = salle;
		this.rangee.setSalle(salle);
	}
	
	public void ajouterEcouteurModification(ModificationSalleListener ecouteur) {
    	ecouteursSuppressionRangee.add(ecouteur);
    }
	
	private void ajouterSiege() {
		if (this.admin == true) {
			JPlace nouvellePlace = new JPlace(this.admin);
			this.panelRangee.add(nouvellePlace);
			listSiegeDansRangee.add(nouvellePlace);
			nouvellePlace.ajouterEcouteurSuppression(this);
			revalidate();
			repaint();
		}
	}
	
	private void retirerSiege(JPlace place) {
		try {
			if (this.admin == true) {
				this.panelRangee.remove(place);
				this.listSiegeDansRangee.remove(place);
				revalidate();
				repaint();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void redessinerPlaces() {
		if (this.admin == true) {
			this.panelRangee.removeAll();
			for(JPlace place : listSiegeDansRangee) {
				this.panelRangee.add(place);
			}
			revalidate();
			repaint();
		}
	}
	
	public void afficherPlaces() {
		for (JPlace place : listSiegeDansRangee) {
			place.ajouterEcouteurSuppression(this);
			this.panelRangee.add(place);
//			place.revalidate();
//			place.repaint();
		}
		revalidate();
		repaint();
	}
	
	public Rangee getRangee() {
		return this.rangee;
	}
	
	public void setRangee(Rangee rangee) {
		this.rangee = rangee;
	}
	
	public ArrayList<JPlace> getListPlaces() {
		return this.listSiegeDansRangee;
	}
	
	public void setListPlaces(ArrayList<JPlace> listPlaces) {
		this.listSiegeDansRangee = listPlaces;
	}
	
	public void supprimerRangee() {
		for(ModificationSalleListener ecouteur : ecouteursSuppressionRangee) {
			ecouteur.RangeeSupprimer(this);
		}
	}
	
	public ArrayList<JPlace> getListPlacesSupprimees() {
		return this.listJPlaceSupprimee;
	}

	@Override
	public void RangeeSupprimer(JRangee rangee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PlaceSupprimee(JPlace place) {
		// TODO Auto-generated method stub
		for(Integer index = 0; index < listSiegeDansRangee.size(); index++) {
			if(listSiegeDansRangee.get(index).equals(place)) {
				if(place.getSiege().getIdSiege() != null) {
					listJPlaceSupprimee.add(place);
				}
				retirerSiege(place);
			}
		}
	}
	
}
