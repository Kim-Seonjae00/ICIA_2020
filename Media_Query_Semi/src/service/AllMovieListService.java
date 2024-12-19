package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.MovieDTO;

public class AllMovieListService {
	public List<MovieDTO> allMovieList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<MovieDTO> MovieList = dao.allMovieList();
		
		close(con);
		return MovieList;
	}
}
