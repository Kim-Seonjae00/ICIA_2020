package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetmTitleService;

@WebServlet("/getmTitle")
public class GetmTitleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetmTitleController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	GetmTitleService getmTitleService = new GetmTitleService();
    	List<String> mTitleList = getmTitleService.getmTitle();
    	request.setAttribute("mTitleList", mTitleList);
    	RequestDispatcher dispatch = request.getRequestDispatcher("WriteBoard.jsp");
    	dispatch.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
