package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Losepw2 implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {	
		//비밀번호 2차 인증 해당 아이디에 
		User_Dao dao = new User_Dao();
		
		User_Dto emp = new User_Dto();
		emp.setUser_email(req.getParameter("email"));
		emp.setUser_answer(req.getParameter("Losepwanswer"));
		String inputans = req.getParameter("Losepwanswer");
		User_Dto list = dao.coutomerLosepw2(emp, inputans);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/User/User_Newpw.jsp";
	}
}
