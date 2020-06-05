package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SetStarRateService;

@WebServlet("/setStarRate")
public class SetStarRateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetStarRateController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String mTitle = request.getParameter("mTitle");
    	int rate = Integer.parseInt(request.getParameter("rate"));
    	String address="movieDetail?mTitle="+mTitle;
    	SetStarRateService setStarRateService = new SetStarRateService();
    	int result = setStarRateService.setStarRate(mTitle, rate);
    	if(result==1) {
    		response.sendRedirect("movieList");
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
