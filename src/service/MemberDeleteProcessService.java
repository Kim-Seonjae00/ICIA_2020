package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class MemberDeleteProcessService {

	public int memberDeleteProcess(String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.memberDeleteProcess(mId);
		if(result == 1) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
	
}
