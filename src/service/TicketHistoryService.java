package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;
import dto.TicketDTO;

public class TicketHistoryService {

	public List<TicketDTO> ticketHistory(String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<TicketDTO> ticketList = dao.ticketHistory(mId);
		
		close(con);
		return ticketList;
	}

	public List<TicketDTO> ticketHistory2() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<TicketDTO> ticketList = dao.ticketHistory2();
		
		close(con);
		return ticketList;
		
	}

}
