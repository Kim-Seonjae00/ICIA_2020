package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.BoardDTO;

public class BoardModifyService {
	public BoardDTO boardModify(int bNum) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		BoardDTO board = dao.boardModify(bNum);
		
		close(con);
		return board;
	}

}
