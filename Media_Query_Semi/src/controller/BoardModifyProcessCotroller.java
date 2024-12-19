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
import service.BoardModifyProcessService;
import service.WriteBoardService;

@WebServlet("/boardModifyProcess")
public class BoardModifyProcessCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModifyProcessCotroller() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	int size = 10*1024*1024;
    	String savePath = "C:/Users/4/Desktop/Development/Source/servlet/Project_Movie/WebContent/images/fileUpload";
		//String savePath = "C:\\Users\\Administrator\\Desktop\\프로젝트\\Project_Movie1\\WebContent\\images\\fileUpload";
		
		MultipartRequest multi = new MultipartRequest(request,savePath,size,"UTF-8",new DefaultFileRenamePolicy());
		int bNum = Integer.parseInt(multi.getParameter("bNum"));
		String mTitle = multi.getParameter("mTitle");
		String boTitle = multi.getParameter("boTitle");
		String comments = multi.getParameter("comments");
		String bFiles = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		
		BoardDTO board = new BoardDTO();
		board.setbNum(bNum);
		board.setmTitle(mTitle);
		board.setBoTitle(boTitle);
		board.setComments(comments);
		board.setbFiles(bFiles);
		
		BoardModifyProcessService boardModifyProcessService = new BoardModifyProcessService();
		int result = boardModifyProcessService.boardModifyProcess(board);
		
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
