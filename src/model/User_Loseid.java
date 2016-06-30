package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Loseid implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//입력받은 이름,별명,핸드폰 번호를 전달해 해당 회원이 있는지 검색
		User_Dao dao = new User_Dao();
		User_Dto emp = new User_Dto();
		emp.setUser_name(req.getParameter("Loseidname"));
		emp.setUser_nick(req.getParameter("Loseidnickname"));
		emp.setUser_phone(req.getParameter("Loseidphone") + "-" + req.getParameter("Loseidphone2") + "-"
				+ req.getParameter("Loseidphone3"));
		User_Dto list = dao.coutomerLoseid(emp);

		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/User/User_Loseid.jsp";
	}
}
