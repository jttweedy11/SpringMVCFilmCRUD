package com.skilldistillery.film.dao;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findByID(int filmId);
}
