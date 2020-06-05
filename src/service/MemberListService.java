package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.MemberDTO;

public class MemberListService {

	public List<MemberDTO> memberList() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<MemberDTO> memberList = dao.memberList();
		
		close(con);
		return memberList;
	}

}
