package vue.composant.Salles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.crud.TypeSiegeCRUD;
import modele.entite.Siege;
import modele.entite.TypeSiege;
import vue.composant.RenduSeances.ModificationListener;
import vue.fenetre.FChoixTypeSiege;

public class JPlace extends JPanel implements MouseListener{
	private Siege siege;
	private Boolean libre = true;
	private Boolean enCoursReservation;
	private Boolean admin;
	private JLabel lblIdSiege;
	private JLabel lblHandicape;
	private ArrayList<ModificationSalleListener> ecouteursSuppressionPlace = new ArrayList<>();
	
	public JPlace(Siege siege, Boolean admin) {
		this.siege = siege;
		this.admin = admin;
		this.enCoursReservation = false;
		setPreferredSize(new Dimension(40, 40));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		lblIdSiege = new JLabel(siege.getIdSiege().toString());
		lblIdSiege.setAlignmentX(0.5f);
		lblHandicape = new JLabel();
		lblHandicape.setAlignmentX(0.5f);
		if (siege.getTypeSiege().getLibelleTypeSiege().equals("Handicape")) {
			lblHandicape.setText("H");
		}
		add(lblIdSiege);
		add(lblHandicape);
		this.addMouseListener(this);
		if (libre) {
			setBackground(Color.green);
		} else {
			setBackground(Color.red);
		}
		revalidate();
		repaint();
	}
	
	public JPlace(Boolean admin) {
		this.admin = admin;
		TypeSiege typeSiege = TypeSiegeCRUD.getSingleton().getByLibelle("Normal");
		this.siege = new Siege(typeSiege, true);
		this.libre = null;
		this.enCoursReservation = null;
		setPreferredSize(new Dimension(40, 40));
		setBackground(Color.WHITE);
	}
	
	public void ajouterEcouteurSuppression(ModificationSalleListener ecouteur) {
    	ecouteursSuppressionPlace.add(ecouteur);
    }
	
	public void setLibre(Boolean libre) {
		this.libre = libre;
		if (this.libre) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.RED);
		}
		repaint();
	}
	
	public Boolean getLibre() {
		return this.libre;
	}
	
	public Boolean getEnCoursReservation() {
		return this.enCoursReservation;
	}
	
	
	public Siege getSiege() {
		return this.siege;
	}
	
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	
	public Boolean getHandicape() {
		if (siege.getTypeSiege().getLibelleTypeSiege().equals("Handicape")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setTypeSiege(TypeSiege typeSiege) {
		this.siege.setHandicape(typeSiege);
		dessinerPlace();
		
	}
	
	public void dessinerPlace() {
		if (this.siege.getTypeSiege().getLibelleTypeSiege().equals("Handicape")) {
			lblHandicape.setText("H");
		} else if (this.siege.getTypeSiege().getLibelleTypeSiege().equals("Normal")) {
			lblHandicape.setText("");
		}
		if(!admin) {
			if (libre) {
				if (enCoursReservation) {
					setBackground(Color.blue);
				} else {
					setBackground(Color.green);
				}
			} else {
				setBackground(Color.red);
			}
		} else {
			setBackground(Color.white);
		}
		revalidate();
		repaint();
	}
	
	public Boolean getNormale() {
		if (siege.getTypeSiege().getLibelleTypeSiege().equals("Normal")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setEnCoursReservation() {
		if (this.admin == false) {
			this.enCoursReservation = true;
			setBackground(Color.blue);
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (admin == true) {
			FChoixTypeSiege fenetreChoixTypeSiege = new FChoixTypeSiege(this);
			fenetreChoixTypeSiege.setVisible(true);
		} else {
			if (this.libre) {
				setEnCoursReservation();
			} else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez sélectionner une place déjà réservée");
			}
		}
	}
	
	public void supprimerPlace() {
		for(ModificationSalleListener ecouteur : ecouteursSuppressionPlace) {
			ecouteur.PlaceSupprimee(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
