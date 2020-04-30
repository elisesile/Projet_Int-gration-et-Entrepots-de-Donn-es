package mediator;

import java.util.ArrayList;
import data.Film;
import source.DbpediaClient;
import source.OmdbClient;
import jdbc.Queries;

public class Mediator{

	public ArrayList<Film> getFilmFromActor(String actor){
		
		ArrayList<Film> films = DbpediaClient.searchByActor(actor);
//		ArrayList<String> strings = new ArrayList<String>();
		films = Queries.getFilms(films);
//		for (Film film : films) {
//			film = OmdbClient.getPlotByTitle(film); //A enlever éventuellement, je me sius aperçue trop tard que j'en avais fait trop :) 
//			strings.add(film.getFilmInformationForFilm());
//		}
		
		return films;
		
	}
	
	public Film getFilmFromTitle(String title) {
		
		Film film = new Film(title);
		film = OmdbClient.getPlotByTitle(film);
		film = DbpediaClient.searchByTitle(title,film);
		film = Queries.getFilm(film);
		return film;
		
	}
	
}