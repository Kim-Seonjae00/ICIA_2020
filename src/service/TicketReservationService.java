package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.TicketDTO;

public class TicketReservationService {

	public int ticketReservation(TicketDTO ticket) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		int result = dao.ticketReservation(ticket);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}
	
	public int setTotalSeat(TicketDTO ticket) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		int result = dao.setTotalSeat(ticket);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}
	
	public int setMovieHit(TicketDTO ticket) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		int result = dao.setMovieHit(ticket);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public int setTotalAmount(int totalPrice, String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		int result = dao.setTotalAmount(totalPrice, mId);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public int setMemberShip(String mId) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		int totalAmountResult = dao.setMemberShip(mId);
		
		close(con);
		return totalAmountResult;
	}

	public int setMemberShipProcess(String mId, String membership) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		int result = dao.setMemberShipProcess(mId, membership);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}
}
