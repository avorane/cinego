package vue.composant.RenduSeances;

import javax.swing.*;
import javax.swing.table.*;

import modele.entite.Seance;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
*
* @author nicolas
*/
public class CellRendererSeances extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 128462L; 

	private JButton boutonModifier;
	private JButton boutonSupprimer;
	
	public CellRendererSeances() {
		super();
		this.boutonModifier = new JButton("Modifier");
		this.boutonModifier.setPreferredSize(new Dimension(200, 100));
		this.boutonSupprimer = new JButton("Supprimer");
	}
	
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	   //super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       if (column == 0) {
    	   JPanelSeance seance = new JPanelSeance((Seance)value);
    	   return seance;
       } else if (column == 1) {
    	   return this.boutonModifier;
       } else if (column == 2) {
    	   return this.boutonSupprimer;
       }
       return null;
   }
}