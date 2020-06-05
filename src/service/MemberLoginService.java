package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class MemberLoginService {

	public boolean memberLogin(String mId, String pw) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean result = dao.memberLogin(mId, pw);
		
		close(con);
		return result;
	}

}
