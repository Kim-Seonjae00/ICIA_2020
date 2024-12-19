package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MovieJoinDTO;
import service.MovieDetailService;

@WebServlet("/movieDetail")
public class MovieDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieDetailController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String mTitle=request.getParameter("mTitle");
    	
    	MovieDetailService movieDetailService = new MovieDetailService();
    	MovieJoinDTO movie = movieDetailService.movieDetail(mTitle);
    	
    	request.setAttribute("movie", movie);
    	RequestDispatcher dispatch = request.getRequestDispatcher("MovieDetail.jsp");
    	dispatch.forward(request, response);
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
