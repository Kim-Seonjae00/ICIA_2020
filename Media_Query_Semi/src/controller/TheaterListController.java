package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TheaterDTO;
import service.TheaterListService;

@WebServlet("/theaterList")
public class TheaterListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TheaterListController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	TheaterListService theaterListService = new TheaterListService();
    	List<TheaterDTO> theaterList = theaterListService.theaterList();
    	
    	request.setAttribute("theaterList", theaterList);
    	RequestDispatcher dispatch = request.getRequestDispatcher("TheaterList.jsp");
    	dispatch.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
