package vue.composant.AutoCompletionComboBox;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.JTextComponent;

import modele.crud.FilmCRUD;
import modele.entite.Film;

public class AutoCompletionComboBox extends JComboBox<String> {
	
	static final long serialVersionUID = 12348761L;
	
	private ArrayList<Film> listFilms;
	private ArrayList<Film> listFilmWithAutocompletion;
	private JTextField saisieField;
	
	public AutoCompletionComboBox() {
		super();
		listFilms = new ArrayList<>();
		saisieField = null;
		FilmCRUD filmCRUD = FilmCRUD.getSingleton();
		listFilms.addAll(filmCRUD.getList());
		afficherTousFilms();
		setEditor(new BasicComboBoxEditor());
		setEditable(true);
	}
	
//	@Override
//	public void setSelectedIndex(int index) {
//		super.setSelectedIndex(index);
//		
//	}
	
	private void afficherTousFilms() {
		for (Film film : listFilms) {
			this.addItem(film.getTitreFilm());
		}
	}
	
	public void setSelectedFilm(Film filmSelected) {
		for(int index = 0; index < listFilms.size(); index++) {
			if(filmSelected.getIdFilm().equals(listFilms.get(index).getIdFilm())) {
				this.setSelectedIndex(index);
			}
		}
	}
	
	public Film getSelectedFilm() {
		int indexFilmSelected = this.getSelectedIndex();
		return listFilms.get(indexFilmSelected);
	}
	
	public void setFilm(Film filmChoisit) {
		for (Film film : listFilms) {
			if (film.getIdFilm() == filmChoisit.getIdFilm()) {
				saisieField.setText(film.getTitreFilm());
			}
		}
	}
	
	@Override
	public void setEditor(ComboBoxEditor editor) {
		super.setEditor(editor);
		if (editor.getEditorComponent() instanceof JTextField) {
			saisieField = (JTextField)editor.getEditorComponent();
			saisieField.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent ev) {
					String stockeSaisie = null;
					Character key = ev.getKeyChar();
					if (!(Character.isLetterOrDigit(key) || ev.getKeyCode() == KeyEvent.VK_SPACE || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
						return;
					}
					stockeSaisie = saisieField.getText();
					listFilmWithAutocompletion = new ArrayList<>();	
					ArrayList<String> listTitresFilms = new ArrayList<>();
					listTitresFilms.add(0, "");
					listFilmWithAutocompletion.add(0, null);
					for(Film film : listFilms) {
						if (film.getTitreFilm().toLowerCase().indexOf(saisieField.getText().toLowerCase()) == 0) {
							listFilmWithAutocompletion.add(film);
							listTitresFilms.add(film.getTitreFilm());
						}
					}
					Collections.sort(listTitresFilms);
					removeAllItems();
					saisieField.setText(stockeSaisie);
					if (!stockeSaisie.equals("")) {
						if(listTitresFilms.size() > 0 && !isPopupVisible()) {
							for (String titre : listTitresFilms) {
								addItem(titre);
							}
							setPopupVisible(true);
						} else {
							setPopupVisible(false);
						}
					} else {
						afficherTousFilms();
						setPopupVisible(false);						
					}
					saisieField.setText(stockeSaisie);
					repaint();
				}
			});
		}
	}
}
