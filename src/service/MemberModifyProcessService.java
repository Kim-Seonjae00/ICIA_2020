package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.MemberDTO;

public class MemberModifyProcessService {

	public int memberModifyProcess(MemberDTO member) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.memberModifyProcess(member);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
