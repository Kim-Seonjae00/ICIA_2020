package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IdOverlapService;

@WebServlet("/idOverlap")
public class IdOverlapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdOverlapController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String id = request.getParameter("id");
    	IdOverlapService idOverlapService = new IdOverlapService();
    	boolean overlap = idOverlapService.idOverlap(id);
    	
    	request.setAttribute("overlap", overlap);
    	RequestDispatcher dispatch = request.getRequestDispatcher("IdOverlap.jsp");
    	dispatch.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
