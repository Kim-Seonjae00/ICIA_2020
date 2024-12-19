package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.BoardDTO;

public class BoardHistoryService {

	public List<BoardDTO> boardHistory(String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<BoardDTO> boardList = dao.boardHistory(mId);
		
		close(con);
		return boardList;
	}

}
