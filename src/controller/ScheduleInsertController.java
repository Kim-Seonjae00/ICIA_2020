package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ScheduleDTO;
import service.ScheduleInsertService;

@WebServlet("/scheduleInsert")
public class ScheduleInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleInsertController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String tLocation = request.getParameter("tLocation");
    	String house = request.getParameter("house");
    	String startTime = request.getParameter("startTime").replace("T"," ");
    	String endTime = request.getParameter("endTime").replace("T"," ");
    	String mTitle = request.getParameter("mTitle");
    	int totalSeat = Integer.parseInt(request.getParameter("totalSeat"));
    	
    	ScheduleDTO schedule = new ScheduleDTO();
    	schedule.settLocation(tLocation);
    	schedule.setHouse(house);
    	schedule.setStartTime(startTime);
    	schedule.setEndTime(endTime);
    	schedule.setmTitle(mTitle);
    	schedule.setTotalSeat(totalSeat);
    	
    	ScheduleInsertService scheduleInsertService = new ScheduleInsertService();
    	int result = scheduleInsertService.scheduleInsert(schedule);
    	if(result==1) {
    		response.sendRedirect("AdminPage.jsp");
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
