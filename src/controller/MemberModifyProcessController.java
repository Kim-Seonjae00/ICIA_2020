package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberModifyProcessService;

@WebServlet("/memberModifyProcess")
public class MemberModifyProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberModifyProcessController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String mId = request.getParameter("mId");
    	String pw = request.getParameter("pw");
    	String mName = request.getParameter("mName");
    	String tel = request.getParameter("tel");
    	String address= request.getParameter("post") +"."+ request.getParameter("road") +"."+
    			request.getParameter("jibun")+"."+request.getParameter("detail")+"."+request.getParameter("extra");
    	String email = request.getParameter("email");
    	
    	MemberDTO member = new MemberDTO();
    	member.setmId(mId);
    	member.setPw(pw);
    	member.setmName(mName);
    	member.setTel(tel);
    	member.setAddress(address);
    	member.setEmail(email);
    	
    	MemberModifyProcessService memberModifyProcessService = new MemberModifyProcessService();
    	int result = memberModifyProcessService.memberModifyProcess(member);
    	
    	if(result==1) {
    		response.sendRedirect("myPage?mId="+mId);
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
