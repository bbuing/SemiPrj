package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class customerLosepw2 implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {	
		//비밀번호 2차 인증 해당 아이디에 
		customerDao dao = new customerDao();
		
		customerDto emp = new customerDto();
		emp.setUser_email(req.getParameter("email"));
		emp.setUser_answer(req.getParameter("Losepwanswer"));
		String inputans = req.getParameter("Losepwanswer");
		customerDto list = dao.coutomerLosepw2(emp, inputans);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/customer/Newpw.jsp";
	}
}
