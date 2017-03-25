package vue.composant.JTableExtra;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class CellRendererExtraEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	private JButton boutonModifier;
	private JButton boutonSupprimer;
	private int row;
	private ExtraTableModel modelExtra;

    public CellRendererExtraEditor() {
        super();
        this.boutonSupprimer = new JButton("Supprimer");
        this.boutonSupprimer.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	this.modelExtra = (ExtraTableModel)table.getModel();
    	if (column == 3) {
     	   return this.boutonModifier;
        }
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	this.modelExtra.supprimerExtra(row);	
    }

    @Override
    public Object getCellEditorValue() {
        return this.row;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }
}
