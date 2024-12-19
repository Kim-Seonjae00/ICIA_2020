package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.BoardDTO;

public class BoardModifyProcessService {

	public int boardModifyProcess(BoardDTO board) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardModifyProcess(board);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
