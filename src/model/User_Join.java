package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Join implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//ȸ�� ������ ���� �Է� ���� ���� dto�� �ְ� dao�� �̵�
		User_Dao dao = new User_Dao();
		User_Dto emp = new User_Dto();
		emp.setUser_name(req.getParameter("Joinname"));
		emp.setUser_nick(req.getParameter("Joinnickname"));
		emp.setUser_email(req.getParameter("Joinemail"));
		emp.setUser_pw(req.getParameter("Joinpw"));
		emp.setUser_question(req.getParameter("Joinquestion"));
		emp.setUser_answer(req.getParameter("Joinanswer"));
		emp.setUser_phone(req.getParameter("Joinphone1") + "-" + req.getParameter("Joinphone2") + "-"
				+ req.getParameter("Joinphone3"));
		emp.setUser_interest(req.getParameter("Checkbox1") + "-" + req.getParameter("Checkbox2") + "-"
				+ req.getParameter("Checkbox3")+ "-"
						+ req.getParameter("Checkbox4")+ "-"
								+ req.getParameter("Checkbox5"));
		User_Dto list = dao.coutomerJoin(emp);
	    req.setAttribute("list", list); 
		return "/WEB-INF/views/User/User_Joinmessage.jsp";
	}
}


