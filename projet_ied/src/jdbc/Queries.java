package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcConnection;
import data.Film;

public class Queries {
	public static ArrayList<Film> getFilms(ArrayList<Film> films) {
		for(int i=0 ; i<films.size() ; i++) {
			String title = films.get(i).getTitre();
			String regex = "^";
			for(int j=0 ; j<title.length() ; j++) {
				regex += "("+title.toLowerCase().charAt(j)+"|"+title.toUpperCase().charAt(j)+")";
			}
			
			try {
				String getFilmsQuery = "SELECT titre, date_sortie, genre, distributeur, budget, revenus_etats_unis, revenus_mondiaux FROM film WHERE titre REGEXP ?;";
	
				Connection dbConnection = JdbcConnection.getConnection();
				PreparedStatement preparedStatement = dbConnection.prepareStatement(getFilmsQuery);
	
				preparedStatement.setString(1, regex);
	
				ResultSet result = preparedStatement.executeQuery();
	
				while (result.next()) {
					films.get(i).setDateSortie(result.getDate("date_sortie"));
					films.get(i).setGenre(result.getString("genre"));
					films.get(i).setDistributeur(result.getString("distributeur"));
					films.get(i).setBudget(result.getDouble("budget"));
					films.get(i).setRevenusEtatsUnis(result.getDouble("revenus_etats_unis"));
					films.get(i).setRevenusMondiaux(result.getDouble("revenus_mondiaux"));
				}
	
				preparedStatement.close();
			} catch (SQLException se) {
				System.err.println(se.getMessage());
			}
		}
		
		return films;
	}
	
	public static ArrayList<String> getCsvInformation() {
		ArrayList<String> csvInformation = new ArrayList<String>();
		try {
			String getCsvInformationQuery = "SELECT value FROM talend_info WHERE `key` REGEXP ? ORDER BY `key`;";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(getCsvInformationQuery);

			preparedStatement.setString(1, "^CSV_");

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				String value = result.getString("value");
				csvInformation.add(value);
			}

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		
		return csvInformation;
	}
}
