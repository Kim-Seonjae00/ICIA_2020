package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.MovieDTO;
import dto.MovieJoinDTO;
import dto.StarRateDTO;

public class MovieListService {

	public List<MovieJoinDTO> nowMovieList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<MovieJoinDTO> movieList = dao.nowMovieList();
		close(con);
		
		return movieList;
	}
	
	public List<MovieJoinDTO> soonMovieList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<MovieJoinDTO> movieList = dao.soonMovieList();
		close(con);
		
		return movieList;
	}
	
	public List<MovieJoinDTO> rankMovieList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<MovieJoinDTO> movieList = dao.rankMovieList();
		close(con);
		
		return movieList;
	}

}
