package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.MovieDTO;
import service.MovieInsertService;

@WebServlet("/movieInsert")
public class MovieInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieInsertController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int size = 10*1024*1024;
		String savePath = "C:/Users/4/Desktop/Development/Source/servlet/Project_Movie/WebContent/images/poster";
		//String savePath = "C:\\Users\\Administrator\\Desktop\\프로젝트\\Project_Movie1\\WebContent\\images\\poster";
		MultipartRequest multi = new MultipartRequest(request,savePath,size,"UTF-8",new DefaultFileRenamePolicy());
		
		String mTitle = multi.getParameter("mTitle");
		String poster = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		String director = multi.getParameter("director");
		String actors = multi.getParameter("actors");
		int runningTime = Integer.parseInt(multi.getParameter("runningTime"));
		String releaseDate = multi.getParameter("releaseDate");
		String endDate = multi.getParameter("endDate");
		
		MovieDTO movie = new MovieDTO();
		movie.setmTitle(mTitle);
		movie.setPoster(poster);
		movie.setDirector(director);
		movie.setActors(actors);
		movie.setRunningTime(runningTime);
		movie.setReleaseDate(releaseDate);
		movie.setEndDate(endDate);
		
		MovieInsertService movieInsertService = new MovieInsertService();
		
		int result = movieInsertService.movieInsert(movie);
		
		if(result==1) {
			int rResult = movieInsertService.setStarRate(mTitle); 
			if(rResult==1) {
				response.sendRedirect("AdminPage.jsp");
			}
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
