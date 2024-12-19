package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.TheaterDTO;

public class TheaterInsertService {

	public int theaterInsert(TheaterDTO theater) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.theaterInsert(theater);
		if(result == 1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

}
