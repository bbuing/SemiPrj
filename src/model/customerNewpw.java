package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class customerNewpw implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {	
		//비밀번호 변경
		customerDao dao = new customerDao();
		customerDto emp = new customerDto();
		emp.setUser_email(req.getParameter("email"));
		emp.setUser_pw(req.getParameter("newpw"));
		customerDto list = dao.coutomerNewpw(emp);

		HttpSession session = req.getSession();
		session.setAttribute("list", list);

		return "/WEB-INF/views/customer/Newpwmessage.jsp";
	}
}
