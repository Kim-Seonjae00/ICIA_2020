package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class IdOverlapService {

	public boolean idOverlap(String id) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		boolean overlap = dao.idOverlap(id);
		
		close(con);
		return overlap;
	}

}
