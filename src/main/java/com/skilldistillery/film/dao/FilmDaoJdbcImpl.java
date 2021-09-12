package com.skilldistillery.film.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {

//	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "root";
	private String pass = "root";
	
	public FilmDaoJdbcImpl() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select Distinct f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, "
					+ "f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("Description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setLanguage(rs.getString("name"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
//					List<Actor> act = findActorsByFilmId(rs.getInt("id"));
//					film.setActors(act);
			}
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return film;
	}

	@Override
	public Film deleteFilm(int filmId) {
		Film film = null;
		try {
			Connection conn;
			conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select Distinct f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, "
					+ "f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Film createFilm(Film film) {
		Film film1 = null;
		try {
			Connection conn = null;

			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sqlst = "INSERT INTO film (title, description, release_year, language_id, rental_duration, \"\n"
					+ "	    		   + \"f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where f.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sqlst, Statement.RETURN_GENERATED_KEYS);

//		stmt.setString(1, f.title());
//		stmt.setString(2, "1");
//		stmt.setInt(3, f.rental_duration());
//		stmt.setDouble(4, f.rental_rate());
//		stmt.setDouble(5, f.replacement_cost());
			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount + " film was created.");
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);

				}
			}
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film1;
	}

//	@Override
//	public Actor findActorById(int actorId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public List<Actor> findActorByFilmId(int filmId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
