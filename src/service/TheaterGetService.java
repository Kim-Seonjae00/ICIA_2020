package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.TheaterDTO;

public class TheaterGetService {

	public List<TheaterDTO> theaterGet() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<TheaterDTO> theaterList= dao.theaterGet();
		
		close(con);
		return theaterList;
	}

}
