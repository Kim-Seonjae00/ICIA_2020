package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.TheaterDTO;

public class TheaterListService {

	public List<TheaterDTO> theaterList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<TheaterDTO> theaterList = dao.theaterList();
		
		close(con);
		return theaterList;
	}

}
