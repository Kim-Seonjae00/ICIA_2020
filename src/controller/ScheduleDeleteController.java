package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ScheduleDeleteService;

@WebServlet("/scheduleDelete")
public class ScheduleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleDeleteController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String tLocation=request.getParameter("tLocation");
    	String house=request.getParameter("house");
    	String startTime=request.getParameter("startTime");
    	
    	ScheduleDeleteService scheduleDeleteService = new ScheduleDeleteService();
    	int result = scheduleDeleteService.scheduleDelete(tLocation, house,startTime);
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
