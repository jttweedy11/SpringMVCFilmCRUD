package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);
	Film deleteFilm(Film film);
	public Film createFilm (Film film);
//	Film createFilm(String title, String description, String releaseYear, String languageID, String rentalDuration, String rentalRate, String length, String replacementCost, String rating, String special_features)
//	public Actor findActorById(int actorId);
	public Film updateFilm (Film film);
	public List<Actor> findActorsByFilmId(int filmId);
	public List<Film> findFilmByKeyword(String keyword);
	
	
}

