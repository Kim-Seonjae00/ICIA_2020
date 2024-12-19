package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberDeleteProcessService;

@WebServlet("/memberDeleteProcess")
public class MemberDeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteProcessController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String mId = request.getParameter("mId");
    	
    	MemberDeleteProcessService memberDeleteProcessService = new MemberDeleteProcessService();
    	int result = memberDeleteProcessService.memberDeleteProcess(mId);
    	if(result == 1) {
    		response.sendRedirect("memberLogout");
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
