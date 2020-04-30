package mediator;

import java.util.ArrayList;
import data.Film;
import source.DbpediaClient;
import source.OmdbClient;
import jdbc.Queries;

public class Mediator{

	public static ArrayList<Film> getFilmsFromActor(String actor){
		ArrayList<Film> films = DbpediaClient.searchByActor(actor);
		for (Film film : films) {
			film = Queries.getFilm(film);
			film = OmdbClient.getPlotByTitle(film);
		}
		return films;
	}
	
	public static Film getFilmFromTitle(String title) {
		Film film = new Film(title);
		film = OmdbClient.getPlotByTitle(film);
		film = DbpediaClient.searchByTitle(film);
		film = Queries.getFilm(film);
		return film;
	}
	
}