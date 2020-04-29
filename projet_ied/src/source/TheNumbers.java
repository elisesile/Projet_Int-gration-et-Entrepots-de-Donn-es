package source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


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
	
	public void generateMoviesInformation() {
		ArrayList<ArrayList<String>> moviesInformation = new ArrayList<ArrayList<String>>();
		for(String genre : genres) {
			moviesInformation.clear();
			for(int year=2000 ; year<=2015 ; year++) {
				Document doc = null;
				try {
					doc = Jsoup.connect("https://www.the-numbers.com/market/"+year+"/genre/"+genre).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Element divTable = doc.getElementById("page_filling_chart").nextElementSibling().nextElementSibling();
			    Element table = divTable.select("table").first();
			    Elements rows = table.select("tr");

			    for(int i=1 ; i<(rows.size()-2) ; i++) {
			        Element row = rows.get(i);
			        Elements cols = row.select("td");
			        
			        String title = cols.get(1).text();
			        String distributor = cols.get(3).text();
			        
			        ArrayList<String> currentArray = new ArrayList<String>();
			        currentArray.add(title);
			        currentArray.add(genre);
			        currentArray.add(distributor);
			        
			        moviesInformation.add(currentArray);
			    }
			}
			
			createCsvFile(genre, moviesInformation);
		}
	}
	
	private void createCsvFile(String genre, ArrayList<ArrayList<String>> moviesInformation) {
		//TODO récup en BDD
		String fileName = "movies_"+genre+".csv";
		try {
			FileWriter csvWriter = new FileWriter("C:\\Users\\Anne-Sophie\\Desktop\\Cours M1\\IED - Intégration et Entrepôts de Données\\Projet\\"+fileName);
		
			csvWriter.append("titre,genre,distributeur\n");
			for(ArrayList<String> information : moviesInformation) {
			    csvWriter.append(String.join(",", information));
			    csvWriter.append("\n");
			}
	
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
