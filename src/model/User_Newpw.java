package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Newpw implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {	
		//비밀번호 변경
		User_Dao dao = new User_Dao();
		User_Dto emp = new User_Dto();
		emp.setUser_email(req.getParameter("email"));
		emp.setUser_pw(req.getParameter("newpw"));
		User_Dto list = dao.coutomerNewpw(emp);

		HttpSession session = req.getSession();
		session.setAttribute("list", list);

		return "/WEB-INF/views/User/User_Newpwmessage.jsp";
	}
}
