package vue.composant.RenduSeances;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.table.*;

import modele.crud.SeanceCRUD;
import modele.entite.Film;
import modele.entite.Salle;
import modele.entite.Seance;

/**
 *
 * @author nicolas
 */
public class SeancesTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Seance> seances = new ArrayList<>();
	private Boolean admin;
	private ArrayList<ModificationListener> ecouteursModification = new ArrayList<>();
	private ArrayList<ModificationListener> ecouteursSelection = new ArrayList<>();
    
    public SeancesTableModel(Calendar dateSeances, Boolean admin) {
        this.seances = SeanceCRUD.getSingleton().getListByDateSeance(dateSeances);
        this.admin = admin;
    }
    
    public SeancesTableModel(Calendar dateSeances, Salle salle, boolean admin) {
    	if (salle != null && dateSeances != null) {
    		this.seances = SeanceCRUD.getSingleton().getListByDateSeanceAndSalle(dateSeances, salle);
    	}
        this.admin = admin;
    }
    
    public Boolean getAdmin() {
    	return this.admin;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        return this.seances.get(row);
    }
    
    @Override
    public int getColumnCount() {
        if (this.admin) {
        	return 3;
        } else {
        	return 1;
        }
    }
    
    @Override
    public int getRowCount() {
        return this.seances.size();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return "";
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 0:
    		return JPanelSeance.class;
    	case 1:
    		return JButton.class;
    	case 2:
    		return JButton.class;
    	default:
    		return Object.class;
    	}
    }
    
    @Override
    public boolean isCellEditable(int row, int index) {
    	switch(index) {
    	case 0:
    		if (this.admin == true) {
    			return false;
    		} else {
    			return true;
    		}
    		
    	case 1:
    		return true;
    	case 2:
    		return true;
    	default:
    		return false;
    	}
    }

    public void ajouterEcouteurModification(ModificationListener ecouteur) {
    	ecouteursModification.add(ecouteur);
    }
    
    public void ajouterEcouteurSelection(ModificationListener ecouteur) {
    	ecouteursSelection.add(ecouteur);
    }
    
    public void creerSeance(Seance seance) {
    	seances.add(seance);
    	fireTableRowsInserted(seances.size()-1, seances.size()-1);
    }
    
    public void supprimerSeance(int ligne) {
    	seances.remove(ligne);
    	fireTableRowsDeleted(ligne, ligne);
    }
    
    public void modifierSeance(int ligne, Seance seance) {
    	seances.set(ligne, seance);
    	fireTableRowsUpdated(ligne, ligne);
    }
    
    public void modifierDateSeances(Calendar dateSeances) {
    	seances.clear();
    	seances = SeanceCRUD.getSingleton().getListByDateSeance(dateSeances);
    	fireTableDataChanged();
    }
    
    public void modifierDateSeancesEtFilm(Calendar dateSeances, Film film) {
    	seances.clear();
    	seances = SeanceCRUD.getSingleton().getListByDateSeance(dateSeances);
    	fireTableDataChanged();
    }
    
    public void modifierDateSeancesEtSalle(Calendar dateSeances, Salle salle) {
    	try {
	    	seances.clear();
	    	seances.addAll(SeanceCRUD.getSingleton().getListByDateSeanceAndSalle(dateSeances, salle));
	    	fireTableDataChanged();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    public void avertirSeanceEnModification(int ligne) {
    	for (ModificationListener ecouteur : this.ecouteursModification) {
    		ecouteur.SeanceEnCoursModification(this.seances.get(ligne));
    	}
    }
    
    public void avertirSeanceChoisie(Seance seance) {
    	for (ModificationListener ecouteur : this.ecouteursSelection) {
    		ecouteur.SeanceChoisie(seance);
    	}
    }
}