package vue.composant.RenduSeances;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import modele.entite.Seance;

public class CellRendererEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, MouseListener{
	
	private JButton boutonModifier;
	private JButton boutonSupprimer;
	private int row;
	private SeancesTableModel modelSeances;
	private JPanelSeance panelSeance;
	private Seance seance;
    public CellRendererEditor() {
        super();
        this.panelSeance = new JPanelSeance();
        this.boutonModifier  = new JButton("Modifier");
        this.boutonModifier.setPreferredSize(new Dimension(200, 100));
        this.boutonSupprimer = new JButton("Supprimer");
        this.boutonModifier.addActionListener(this);
        this.boutonSupprimer.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //super.getTableCellEditorComponent(table, value, isSelected, row, column);
    	this.modelSeances = (SeancesTableModel)table.getModel();
    	if (column == 0) {
    		this.row = row;
    		if (!this.modelSeances.getAdmin()) {
    			this.modelSeances.avertirSeanceChoisie((Seance)value);
//    			this.panelSeance = (JPanelSeance)value;
//    			//this.panelSeance.addMouseListener(this);
//    			return this.panelSeance;
    		}
    	} else if (column == 1) {
     	   this.row = row;
     	   return this.boutonModifier;
        } else if (column == 2) {
     	   this.row = row;
     	  return this.boutonSupprimer;
        }
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	this.modelSeances.avertirSeanceEnModification(row);
    	fireEditingStopped();
    	
    }

    @Override
    public Object getCellEditorValue() {
        return this.row;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {		
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