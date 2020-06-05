package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.MovieJoinDTO;

public class MovieDetailService {

	public MovieJoinDTO movieDetail(String mTitle) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MovieJoinDTO movie = dao.movieDetail(mTitle);
		
		close(con);
		return movie;
	}

}
