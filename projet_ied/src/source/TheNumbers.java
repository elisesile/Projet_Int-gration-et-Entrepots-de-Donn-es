package source;

import java.util.ArrayList;

public class TheNumbers {
	// https://www.the-numbers.com/market/<year>/genre/<genre>
	// genre = Adventure, Comedy, Drama, Action, Thriller-or-Suspence, Romantic-Comedy
	// year = 2000 to 2015
	private ArrayList<String> genres = new ArrayList<String>();
	
	public TheNumbers() {
		genres.add("Adventure");
		genres.add("Comedy");
		genres.add("Drama");
		genres.add("Action");
		genres.add("Thriller-or-Suspence");
		genres.add("Romantic-Comedy");
	}
	
	public void getMoviesInformation() {
		for(String genre : genres) {
			for(int year=2000 ; year<=2015 ; year++) {
				
			}
		}
		//un fichier par genre
	}
	
	private void createCsvFile() {
		
	}
}
