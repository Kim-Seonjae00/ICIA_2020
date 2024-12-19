package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TheaterDTO;
import service.TheaterInsertService;

@WebServlet("/theaterInsert")
public class TheaterInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TheaterInsertController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String area=request.getParameter("area");
    	String tLocation = request.getParameter("tLocation");
    	
    	TheaterDTO theater = new TheaterDTO();
    	theater.setArea(area);
    	theater.settLocation(tLocation);
    	
    	TheaterInsertService theaterInsertService = new TheaterInsertService();
    	int result = theaterInsertService.theaterInsert(theater);
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
