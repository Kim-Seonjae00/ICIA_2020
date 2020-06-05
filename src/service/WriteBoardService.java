package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.BoardDTO;

public class WriteBoardService {

	public int writeBoard(BoardDTO board) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.writeBoard(board);
		if(result == 1) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return result;
	}

}
