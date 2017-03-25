package vue.composant;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modele.entite.ReservationSiegeExtra;
import modele.entite.ReservationSiegeTarif;

public class ModelTableRecapCommande extends AbstractTableModel {
	private final Object[][] donnees;
	private final String [] titres = {"N° Siège", "Prix Place", "Extra", "Prix Extra", "Majoration", "Total"};

	public ModelTableRecapCommande(Object [][] donnees) {
		this.donnees = donnees; 
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.titres[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		return donnees[row][column];
	}		
}
