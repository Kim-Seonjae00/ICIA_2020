package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class BoardDeleteService {

	public int boardDelete(int bNum) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		int result = dao.boardDelete(bNum);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return result;
	}

}
