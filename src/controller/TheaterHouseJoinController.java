package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MovieDTO;
import dto.TheaterDTO;
import dto.TheaterJoinDTO;
import service.AllMovieListService;
import service.GetmTitleService;
import service.TheaterGetService;
import service.TheaterHouseJoinService;

@WebServlet("/theaterHouseJoin")
public class TheaterHouseJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TheaterHouseJoinController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	TheaterHouseJoinService theaterHouseJoinService = new TheaterHouseJoinService();
    	List<TheaterJoinDTO> theaterJoinList = theaterHouseJoinService.theaterHouseJoin();
    	
    	TheaterGetService theaterGetService = new TheaterGetService();
    	List<TheaterDTO> theaterList = theaterGetService.theaterGet();
    	
    	AllMovieListService allMovieListService = new AllMovieListService();
    	List<MovieDTO> movieList = allMovieListService.allMovieList();
    	
    	request.setAttribute("theaterJoinList", theaterJoinList);
    	request.setAttribute("theaterList", theaterList);    
    	request.setAttribute("movieList", movieList);
    	
    	RequestDispatcher dispatch = request.getRequestDispatcher("ScheduleInsert.jsp");
    	dispatch.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
