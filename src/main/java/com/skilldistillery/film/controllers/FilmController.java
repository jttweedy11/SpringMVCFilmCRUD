package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;
	
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "WEB-INF/home.html";
	}
	@RequestMapping(path = "GetMovieData.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmById(int id) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDAO.findFilmById(id);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/views/results.jsp");
		return mv;
	}
	@RequestMapping(path = "keywordSearch.do", method = RequestMethod.GET)
	public ModelAndView keywordSearch(String keyword) {
		List<Film> films = filmDAO.findFilmByKeyword(keyword);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/searchKeyword.jsp");
		mv.addObject("films", films);
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.GET)
	public ModelAndView deleteFilm(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film film1 = null;
		film1 = filmDAO.findFilmById(filmId);
		if(film1 != null) {
			filmDAO.deleteFilm(film1);
			mv.addObject("delete", film1);
		}
		
//		boolean deleteFilm = dao.deleteFilm(film);	
		mv.setViewName("WEB-INF/views/delete.jsp");

		return mv;
	}
	
	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilm(Film filmData) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDAO.createFilm(filmData);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/views/results.jsp");
		return mv;
	}
	
	@RequestMapping(path="filmToUpdate.do", method = RequestMethod.GET)
	public ModelAndView filmUpdating(int filmId) {
		Film film = filmDAO.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
		if(film!=null) {
			mv.addObject(film);
			mv.setViewName("updatingFilm.jsp");
		}
		return mv;
	}
	@RequestMapping(path = "updatingFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(@RequestParam("filmId") int filmId, Film film) {
		ModelAndView mv = new ModelAndView();
//		Film film = filmDAO.updateFilm(filmsData);
		film.setId(filmId);
		Film nFilm = filmDAO.updateFilm(film);
		if(nFilm==null) {
			nFilm = new Film();
			nFilm.setId(-1);
		}
		mv.addObject("Film", nFilm);
		mv.setViewName("updateFinalFilm.jsp");
		return mv;
	}
}
