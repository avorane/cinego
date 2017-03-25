package vue.composant.JTableExtra;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import modele.entite.Seance;
import vue.composant.RenduSeances.JPanelSeance;

public class CellExtraRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 128462L; 

	private JButton boutonSupprimer;
	
	public CellExtraRenderer() {
		super();
		this.boutonSupprimer = new JButton("Supprimer");
	}
	
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       if (column == 3) {
    	   return this.boutonSupprimer;
       }
       return null;
   }
}
