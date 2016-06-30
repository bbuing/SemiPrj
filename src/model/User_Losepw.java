package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Losepw implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {	
		//비밀번호 1차 찾기 이름,별명,이메일,핸드폰 번호 값을 입력받아 해당 회원이 존재하는지 검사
		User_Dao dao = new User_Dao();
		User_Dto emp = new User_Dto();
		emp.setUser_name(req.getParameter("Losepwname"));
		emp.setUser_nick(req.getParameter("Losepwnickname"));
		emp.setUser_email(req.getParameter("Losepwemail"));
		emp.setUser_phone(req.getParameter("Losepwphone") + "-" + req.getParameter("Losepwphone2") + "-"
				+ req.getParameter("Losepwphone3"));
		User_Dto list = dao.coutomerLosepw1(emp);

		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/User/User_Loseidpw2.jsp";
	}
}
