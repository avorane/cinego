package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import modele.crud.FilmCRUD;
import modele.entite.Film;
import vue.composant.RenduSeances.InfosFilm;

public class WebService {
	
	private int pageNumber;
	
	public WebService() {
		pageNumber = 1;
	}
	
	public void mettreAJourBaseFilm() {
		
		try{
			Boolean lecture = true;
			while(lecture) {
				FilmCRUD filmCRUD = FilmCRUD.getSingleton();
				URL url = new URL("http://www.omdbapi.com/?s=a&page=" + pageNumber);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/json");
				
				if (connection.getResponseCode() != 200) {
					lecture = false;
					break;
				}else{
					
					BufferedReader br = new BufferedReader(new InputStreamReader(
							(connection.getInputStream())));
					
					String jsonResult = br.readLine();
					connection.disconnect();
					
					ObjectMapper jsonMapper = new ObjectMapper();
					JsonNode rootNode = jsonMapper.readTree(jsonResult);
					JsonNode searchNode = rootNode.path("Search");
					
					for (Iterator<JsonNode> iter = searchNode.elements(); iter.hasNext(); ) {
						JsonNode node = iter.next();
						String idFilmWebService = node.path("imdbID").asText();
						URL urlFilm = new URL("http://www.omdbapi.com/?i=" + idFilmWebService);
						HttpURLConnection connectionFilm = (HttpURLConnection)urlFilm.openConnection();
						connectionFilm.setRequestMethod("GET");
						connectionFilm.setRequestProperty("Accept", "application/json");
						if (connection.getResponseCode() != 200) {
							
						} else {
							BufferedReader bufferFilm = new BufferedReader(new InputStreamReader(
									(connectionFilm.getInputStream())));
							
							String jsonResultFilm = bufferFilm.readLine();
							connectionFilm.disconnect();
							ObjectMapper jsonMapperFilm = new ObjectMapper();
							JsonNode rootNodeFilm = jsonMapperFilm.readTree(jsonResultFilm);
							String titre = rootNodeFilm.path("Title").asText().replace("'", "''");
							String sDuree = rootNodeFilm.path("Runtime").asText();
							Integer duree = Integer.parseInt(sDuree.substring(0, sDuree.indexOf(" ")));					
							Film f = new Film(idFilmWebService, titre, duree);
							filmCRUD.creerFilm(f);		
						}			
					}
				}
				pageNumber++;
			}
		}catch (Exception e){
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	public InfosFilm getInfosFilm(String idIDMB) {
		InfosFilm infos = null;
		try {
			URL url = new URL("http://www.omdbapi.com/?i=" + idIDMB);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if (connection.getResponseCode() != 200) {
				
			} else {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(
						(connection.getInputStream())));
				
				String jsonResult = buffer.readLine();
				connection.disconnect();
				ObjectMapper jsonMapper = new ObjectMapper();
				JsonNode rootNodeFilm = jsonMapper.readTree(jsonResult);
				String titre = rootNodeFilm.path("Title").asText();
				String synopsis = rootNodeFilm.path("Plot").asText();
				String sDuree = rootNodeFilm.path("Runtime").asText();
				Integer duree = Integer.parseInt(sDuree.substring(0, sDuree.indexOf(" ")));
				String urlImage = rootNodeFilm.path("Poster").asText();
				ImageIcon image = new ImageIcon(new URL(urlImage));
				infos = new InfosFilm(titre, image, synopsis, duree);
			}
		} catch(Exception ex) {
			
		}
		return infos;
	}
	
	
		
}
