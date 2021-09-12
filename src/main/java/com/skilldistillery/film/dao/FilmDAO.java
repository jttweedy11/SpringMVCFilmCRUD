package com.skilldistillery.film.dao;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);
	Film deleteFilm(int filmId);
	public Film createFilm (Film film);
//	public Actor findActorById(int actorId);
	public Film updateFilm (Film film);
//	public List<Actor> findActorByFilmId(int filmId);
	
	
}

