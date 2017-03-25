package vue.composant.JTableExtra;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import modele.entite.Extra;
import modele.entite.Reservation;
import modele.entite.ReservationSiegeExtra;

public class ExtraTableModel extends AbstractTableModel {
private static final long serialVersionUID = 1L;
	
	private ArrayList<ReservationSiegeExtra> listReservationExtra;
    
    public ExtraTableModel() {
    }
    
    public ExtraTableModel(ArrayList<ReservationSiegeExtra> listReservationExtra) {
    	this.listReservationExtra = listReservationExtra;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
    	switch(column) {
    	case 0:
    		return this.listReservationExtra.get(row).getExtra().getLibelleExtra();
    	case 1:
    		return this.listReservationExtra.get(row).getExtra().getPrix().toString();
    	case 2:
    		return this.listReservationExtra.get(row).getQuantite().toString();
    	default:
    		return null;
    	}
        
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public int getRowCount() {
        return this.listReservationExtra.size();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
        case 0:
        	return "Extra";
        case 1:
        	return "Prix";
        case 2:
        	return "Quantité";
        default:
        	return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 3:
    		return JButton.class;
    	default:
    		return Object.class;
    	}
    }
    
    @Override
    public boolean isCellEditable(int row, int index) {
    	switch(index) {
    	case 0:
    		return false;
    	case 1:
    		return false;
    	case 2:
    		return false;
    	case 3:
    		return true;
    	default:
    		return false;
    	}
    }
    
    public void ajouterExtra() {
    	fireTableRowsInserted(this.listReservationExtra.size()-1, this.listReservationExtra.size()-1);
    }
    
    public void supprimerExtra(int ligne) {
    	fireTableRowsDeleted(ligne, ligne);
    }
}
