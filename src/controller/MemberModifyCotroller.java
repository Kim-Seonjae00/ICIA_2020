package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.MemberDTO;
import dto.TicketDTO;
import service.BoardHistoryService;
import service.MemberViewService;
import service.TicketHistoryService;

@WebServlet("/memberModify")
public class MemberModifyCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberModifyCotroller() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String mId = request.getParameter("mId");
    	MemberViewService memberViewService = new MemberViewService();
    	MemberDTO member = memberViewService.memberView(mId);
    	String[] addressList= member.getAddress().split("\\.");
    	
    	
    	BoardHistoryService boardHistoryService = new BoardHistoryService();
		List<BoardDTO> boardList = boardHistoryService.boardHistory(mId);
		int bCount = boardList.size();
		
		TicketHistoryService ticketHistoryService = new TicketHistoryService();
		List<TicketDTO> ticketList = ticketHistoryService.ticketHistory(mId);
		int tCount = ticketList.size();
		
		request.setAttribute("bCount", bCount);
		request.setAttribute("tCount", tCount);
		request.setAttribute("membership", member.getMembership());
    	request.setAttribute("addressList", addressList);
    	request.setAttribute("member", member);
    	RequestDispatcher dispatch = request.getRequestDispatcher("MemberModify.jsp");
    	dispatch.forward(request, response);
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
