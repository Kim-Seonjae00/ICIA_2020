package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TicketDTO;
import service.TicketReservationService;

@WebServlet("/ticketReservation")
public class TicketReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketReservationController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mId=request.getParameter("mId");
		String mTitle=request.getParameter("mTitle");
		String area=request.getParameter("area");
		String tLocation=request.getParameter("tLocation");
		String house=request.getParameter("house");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		int youth = Integer.parseInt(request.getParameter("youth"));
		int adult = Integer.parseInt(request.getParameter("adult"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		String seat = request.getParameter("seat");
		
		String[] seatList = seat.trim().split(" ");
		System.out.println(seatList.length);
		
		TicketDTO ticket =new TicketDTO();
		ticket.setmId(mId);
		ticket.setmTitle(mTitle);
		ticket.setArea(area);
		ticket.settLocation(tLocation);
		ticket.setHouse(house);
		ticket.setStartTime(startTime);
		ticket.setEndTime(endTime);
		ticket.setYouth(youth);
		ticket.setAdult(adult);
		ticket.setTotalPrice(totalPrice);
		ticket.setSeat(seat);
		TicketReservationService ticketReservationService = new TicketReservationService();
		
		int setAmountResult = ticketReservationService.setTotalAmount(totalPrice, mId);
		
		if(setAmountResult==1) {
			String membership="";
			int totalAmountResult = ticketReservationService.setMemberShip(mId);
			if(totalAmountResult>50000) {
				membership="SILVER";
			}else if(totalAmountResult>100000) {
				membership="GOLD";
			}else if(totalAmountResult>200000) {
				membership="VIP";
			}else {
				membership="BRONZE";
			}
			int result1 = ticketReservationService.setMemberShipProcess(mId,membership);
			if(result1!=1) {
				response.sendRedirect("Fail.jsp");
			}
		}else {
			response.sendRedirect("Fail.jsp");
		}
		
		for(int i=0;i<seatList.length;i++) {
			ticketReservationService.setTotalSeat(ticket);
			ticketReservationService.setMovieHit(ticket);
		}
		
		int result = ticketReservationService.ticketReservation(ticket);
		System.out.println(ticket.getmTitle());
		System.out.println(result);
				
		if(result==1) {
			response.sendRedirect("mainForm");
		}else {
			response.sendRedirect("Fail.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
