package test;

import java.util.ArrayList;
import java.util.Scanner;

import data.Film;
import jdbc.Queries;

public class TestConsole {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String search;
		do {
			search = readInputs(scanner);
	
			if(!search.equals("q")) {
				String type = search.substring(0, search.indexOf("|"));
				String name = search.substring(search.indexOf("|")+1);
		
				//TODO appels méthodes + utilisation de type et name
				//types: 1=title / 2=actor 
				System.out.println("You entered: + name);
				
				ArrayList<Film> films = new ArrayList<Film>();
				films = Queries.getFilms(name, true);
				
				for(Film film : films) {
					System.out.println(film.getFilmInformationForFilm());
				}
			}
		}while(!search.equals("q"));
		scanner.close();
	}
	
	private static String readInputs(Scanner scanner) {
        int resultType = 0;
        String result;
        
        do {
        	System.out.println("\n\tSearch by movie 'title' or by 'actor' name?\n\tPlease type 'title' or 'actor' and press Enter. Then enter the name you want to search.");
        	
        	String s = scanner.nextLine();
        	resultType = 0;
            if(s.equals("title")) {
            	resultType = 1;
            }
            else if(s.equals("actor")) {
            	resultType = 2;
            }
            else if(s.equals("q")) {
            	return "q";
            }
            
            String name = scanner.nextLine();
            StringBuffer sb = new StringBuffer();
            sb.append(resultType);
            sb.append("|");
            sb.append(name);
            result = sb.toString();
            
            if(resultType == 0) {
            	System.out.println("\tError: please enter an acceptable value :(");
            }
        }while(resultType == 0);
        
        return result;
	}

}
