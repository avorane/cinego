package vue.composant;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import modele.crud.ProposeCRUD;
import modele.crud.TarifCRUD;
import modele.entite.Propose;
import modele.entite.Seance;
import modele.entite.Tarif;


public class JPanelTarifs extends JPanel{
	private ArrayList<JCheckBoxTarif> listCheckboxTarifs;
	
	public JPanelTarifs(Boolean admin, Seance seance) {
		super();
		if (admin == false) {
			this.listCheckboxTarifs = new ArrayList<>();
			setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
			ArrayList<Tarif> listTarifs = new ArrayList<>();
			for (Propose propose : ProposeCRUD.getSingleton().getByIdSeance(seance)) {
				listTarifs.add(propose.getTarif());
			}
			for (Tarif tarif : listTarifs) {
				JCheckBoxTarif checkbox = new JCheckBoxTarif(tarif);
				checkbox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent src) {
						controlCheckbox((JCheckBoxTarif)src.getSource());				
					}
				});
				this.listCheckboxTarifs.add(checkbox);
				add(checkbox);
			}
		}
	}
	
	public JPanelTarifs(Boolean admin) {
		super();
		if (admin == true) {
			setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
			this.listCheckboxTarifs = new ArrayList<>();
			for (Tarif tarif : TarifCRUD.getSingleton().getListAll()) {
				JCheckBoxTarif checkbox = new JCheckBoxTarif(tarif);
				this.listCheckboxTarifs.add(checkbox);
				add(checkbox);
			}
		}
	}
	
	public Tarif getTarif() {
		for(JCheckBoxTarif checkboxTarif : this.listCheckboxTarifs) {
			if(checkboxTarif.isSelected()) {
				return checkboxTarif.getTarif();
			}
		}
		return null;
	}
	
	public ArrayList<JCheckBoxTarif> getListTarifs() {
		return this.listCheckboxTarifs;
	}
	
	public void controlCheckbox(JCheckBoxTarif src) {
		for (JCheckBoxTarif checkbox : listCheckboxTarifs) {
			if (!checkbox.equals(src)) {
				if (checkbox.isSelected()) {
					checkbox.setSelected(false);
				}
			}
		}
	}
	
	public Integer getNbreCheckBoxSelected() {
		Integer compteur = 0;
		for(JCheckBoxTarif checkboxTarif : this.listCheckboxTarifs) {
			if(checkboxTarif.isSelected()) {
				compteur++;
			}
		}
		return compteur;
	}
}
