package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class SetStarRateService {

	public int setStarRate(String mTitle, int rate) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.setStarRates(mTitle,rate);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
