package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcConnection;
import data.Film;

public class Queries {
	public static ArrayList<Film> getFilms(String title) {
		String regex = "^";
		for(int i=0 ; i<title.length() ; i++) {
			regex += "("+title.toLowerCase().charAt(i)+"|"+title.toUpperCase().charAt(i)+")";
		}
		
		ArrayList<Film> films = new ArrayList<Film>();
		try {
			String getFilmsQuery = "SELECT titre, date_sortie, genre, distributeur, budget, revenus_etats_unis, revenus_mondiaux FROM film WHERE titre REGEXP ?;";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(getFilmsQuery);

			preparedStatement.setString(1, regex);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				String titre = result.getString("titre");
				Date dateSortie = result.getDate("date_sortie");
				String genre = result.getString("genre");
				String distributeur = result.getString("distributeur");
				Double budget = result.getDouble("budget");
				Double revenusEtatsUnis = result.getDouble("revenus_etats_unis");
				Double revenusMondiaux = result.getDouble("revenus_mondiaux");
				
				Film film = new Film(titre, dateSortie, genre, distributeur, budget, revenusEtatsUnis, revenusMondiaux);
				
				films.add(film);
			}

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
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
