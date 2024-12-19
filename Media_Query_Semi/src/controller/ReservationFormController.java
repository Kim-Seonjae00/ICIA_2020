package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HouseDTO;
import dto.MovieDTO;
import dto.ScheduleDTO;
import dto.ScheduleJoinDTO;
import dto.TheaterDTO;
import dto.TicketDTO;
import service.AllMovieListService;
import service.HouseListService;
import service.ScheduleGetService;
import service.ScheduleJoinGetService;
import service.TheaterGetService;
import service.TicketHistoryService;

@WebServlet("/reservationForm")
public class ReservationFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReservationFormController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	ScheduleJoinGetService scheduleJoinGetService = new ScheduleJoinGetService();
    	List<ScheduleJoinDTO> scheduleJoinList = scheduleJoinGetService.scheduleJoinGet();
    	
    	TheaterGetService theaterGetService = new TheaterGetService();
    	List<TheaterDTO> theaterList = theaterGetService.theaterGet();
    	
    	
    	HouseListService houseListService = new HouseListService();
    	List<HouseDTO> houseList = houseListService.houseList(); 
    	
    	TicketHistoryService ticketHistoryService = new TicketHistoryService();
    	List<TicketDTO> ticketList = ticketHistoryService.ticketHistory2();
    	
    	request.setAttribute("theaterList", theaterList);    
    	request.setAttribute("scheduleJoinList", scheduleJoinList);
    	request.setAttribute("houseList", houseList);   
    	request.setAttribute("ticketList", ticketList);   
    	RequestDispatcher dispatch = request.getRequestDispatcher("TicketReservation.jsp");
    	dispatch.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
