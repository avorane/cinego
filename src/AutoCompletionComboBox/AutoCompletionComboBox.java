package AutoCompletionComboBox;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import modele.crud.FilmCRUD;
import modele.entite.Film;

public class AutoCompletionComboBox extends JComboBox<String> {
	
	static final long serialVersionUID = 12348761L;
	
	private ArrayList<Film> listFilms;
	
	public AutoCompletionComboBox() {
		super();
		listFilms = new ArrayList<>();
		
		FilmCRUD filmCRUD = FilmCRUD.getSingleton();
		listFilms = filmCRUD.getList();
		setEditable(true);
		Component editorCombobox = getEditor().getEditorComponent();
		if (editorCombobox instanceof JTextComponent) {
			JTextComponent textEditor = (JTextComponent)editorCombobox;
			textEditor.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					update();
					
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					update();
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {

				}
				
				public void update() {
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							ArrayList<String> titresDisponibles = new ArrayList<>();							
							for(Film film : listFilms) {
								if (film.getTitreFilm().indexOf(textEditor.getText()) == 0) {
									titresDisponibles.add(film.getTitreFilm());
								}
							}
							Collections.sort(titresDisponibles);
							removeAllItems();
							for (String titre : titresDisponibles) {
								addItem(titre);
							}
							setEditable(true);
							setPopupVisible(true);
						}
					});
				}
			});
		}
	}
	
	
}
