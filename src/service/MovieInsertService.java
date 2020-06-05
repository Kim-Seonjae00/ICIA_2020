package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.MovieDTO;

public class MovieInsertService {

	public int movieInsert(MovieDTO movie) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.movieInsert(movie);
		if(result==1) {
			commit(con);
		}else {
			commit(con);
		}
		close(con);
		
		return result; 
	}

	public int setStarRate(String mTitle) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.setStarRate(mTitle);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
