package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.MemberDTO;

public class MemberViewService {

	public MemberDTO memberView(String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		MemberDTO member = dao.memberView(mId);
		
		close(con);
		return member;
	}

}
