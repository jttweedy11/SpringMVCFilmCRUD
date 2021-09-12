package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
//	@RequestMapping(path="DeleteFilm.do", params = "id", method = RequestMethod.POST)
//	public ModelAndView deleteFilm(Int id) {
//		ModelAndView mv = new ModelAndView();
//		return mv;
//	}
	
//	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
//	public ModelAndView updateFilm() {
//		ModelAndView mv = new ModelAndView();
//		Film film = dao.createFilm(filmData);
//		mv.addObject("film", film);
//		mv.setViewName("createFilmResult.jsp");
//		return mv;
//	}
//	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
//	public ModelAndView createFilm(Film film) {
//		ModelAndView mv = new ModelAndView();
//		Film film = dao.updateFilm(film);
//		mv.addObject("Film", film);
//		mv.setViewName("updteFilmResult.jsp");
//		return mv;
//	}
}
