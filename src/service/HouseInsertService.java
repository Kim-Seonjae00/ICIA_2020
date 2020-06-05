package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.HouseDTO;

public class HouseInsertService {

	public int houseInsert(HouseDTO houses) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		int result = dao.houseInsert(houses);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
