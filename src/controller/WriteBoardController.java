package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.BoardDTO;
import service.WriteBoardService;

@WebServlet("/writeBoard")
public class WriteBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	int size = 10*1024*1024;
    	String savePath = "C:/Users/4/Desktop/Development/Source/servlet/Project_Movie/WebContent/images/fileUpload";
		//String savePath = "C:\\Users\\Administrator\\Desktop\\프로젝트\\Project_Movie1\\WebContent\\images\\fileUpload";
		
		MultipartRequest multi = new MultipartRequest(request,savePath,size,"UTF-8",new DefaultFileRenamePolicy());
		String mId = multi.getParameter("mId");
		String mTitle = multi.getParameter("mTitle");
		String boTitle = multi.getParameter("boTitle");
		String comments = multi.getParameter("comments");
		String bFiles = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		
		BoardDTO board = new BoardDTO();
		board.setmId(mId);
		board.setmTitle(mTitle);
		board.setBoTitle(boTitle);
		board.setComments(comments);
		board.setbFiles(bFiles);
		
		WriteBoardService writeBoardService = new WriteBoardService();
		int result = writeBoardService.writeBoard(board);
		
		if(result==1) {
			response.sendRedirect("boardList");
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
