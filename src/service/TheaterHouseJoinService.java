package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.TheaterJoinDTO;

public class TheaterHouseJoinService {

	public List<TheaterJoinDTO> theaterHouseJoin() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<TheaterJoinDTO> theaterJoinList = dao.theaterHouseJoin();
		
		close(con);
		return theaterJoinList;
	}

}
