package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.BoardDTO;

public class BoardViewService {

	public BoardDTO boardView(int bNum) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
				
		BoardDTO board = dao.boardView(bNum);
		
		close(con);
		return board;
	}

	public int setHit(int bNum) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		int result = dao.setHit(bNum);
		if(result == 1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
