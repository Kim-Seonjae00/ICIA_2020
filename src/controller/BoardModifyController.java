package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardModifyService;

@WebServlet("/boardModify")
public class BoardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModifyController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	int bNum = Integer.parseInt(request.getParameter("bNum"));
    	
    	BoardModifyService boardModifyService = new BoardModifyService();
    	BoardDTO board = boardModifyService.boardModify(bNum);
    	
    	request.setAttribute("board", board);
    	RequestDispatcher dispatch = request.getRequestDispatcher("BoardModify.jsp");
    	dispatch.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
