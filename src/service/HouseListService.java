package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.HouseDTO;

public class HouseListService {

	public List<HouseDTO> houseList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<HouseDTO> houseList = dao.houseList();
		
		close(con);
		return houseList;
	}
	
	
	
	
}
