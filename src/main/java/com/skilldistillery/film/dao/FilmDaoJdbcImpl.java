package com.skilldistillery.film.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
				List<Actor> act = findActorsByFilmId(rs.getInt("id"));
				film.setActors(act);
			}
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return film;
	}

	@Override
	public Film deleteFilm(Film film) {
		String sql = "Delete FROM film where id = ?";
		Film dFilm = film;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement stmt1 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt1.setInt(1, film.getId());
			int uc = stmt1.executeUpdate();
			System.out.println(uc + " film records deleted");
			ResultSet keys = stmt1.getGeneratedKeys();
			while (keys.next()) {
				System.out.println("Delete film ID: " + keys.getInt(1));
			}
			conn.commit();
		} catch (SQLException e) {
			System.err.println("Error during delete");
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
			return null;
		}
		return dFilm;
	}

	@Override
	public Film createFilm(Film film) {
		Film film1 = null;
		try {
			Connection conn = null;

			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sqlst = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) Values(?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sqlst, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setDouble(4, film.getLanguageID());
			stmt.setDouble(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setDouble(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount + " film was created.");
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					film1 = film;

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
	String sqlst = "Update film set title = ?,description = ?,release_year = ?,language_id = ?, rental_duration = ?,rental_rate = ?,length = ?,replacement_cost = ?,rating = ?, special_features = ? WHERE id = ?";
//		return null;
//	}
	@Override
	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			
			PreparedStatement stmt = conn.prepareStatement(sqlst, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setDouble(4, film.getLanguageID());
			stmt.setDouble(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setDouble(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());
			int uc = stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
				System.out.println("Delete film ID: " + keys.getInt(1));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return film;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmID) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select distinct a.id, a.first_name, a.last_name from film f inner join film_actor fa on fa.film_id = f.id inner join actor a on a.id = fa.actor_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, filmID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return actors;
	}
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select Distinct f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, "
					+ "f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where (f.title like ? or f.description like ?)";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			System.out.println();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString(2));
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
				List<Actor> act = findActorsByFilmId(rs.getInt("id"));
				film.setActors(act);
				films.add(film);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Database error:");
			System.err.println(e);
		}

		return films;
	}
}
