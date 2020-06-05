package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HouseDTO;
import service.HouseInsertService;

@WebServlet("/houseInsert")
public class HouseInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HouseInsertController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String tLocation = request.getParameter("tLocation");
    	String house = request.getParameter("house");
    	int totalSeat = Integer.parseInt(request.getParameter("totalSeat"));
    	int width = Integer.parseInt(request.getParameter("width"));
    	int height = Integer.parseInt(request.getParameter("height"));
    	
    	System.out.println("장소:"+tLocation);
    	
    	HouseDTO houses = new HouseDTO();
    	houses.settLocation(tLocation);
    	houses.setHouse(house);
    	houses.setTotalSeat(totalSeat);
    	houses.setWidth(width);
    	houses.setHeight(height);
    	
    	HouseInsertService houseInsertService = new HouseInsertService();
    	int result = houseInsertService.houseInsert(houses);
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
