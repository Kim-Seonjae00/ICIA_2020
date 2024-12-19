package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.BoardDTO;

public class BoardListService {

	public int listCount() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int listCount = dao.listCount();
		
		close(con);
		return listCount;
	}

	public List<BoardDTO> boardList(int startRow, int endRow) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<BoardDTO> boardList = dao.boardList(startRow, endRow);
		
		close(con);
		return boardList;
	}
	
	public List<BoardDTO> boardHit(int startRow, int endRow) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<BoardDTO> boardList = dao.boardHit(startRow, endRow);
		
		close(con);
		return boardList;
	}

}
