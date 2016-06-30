package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Delete implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//ȸ��Ż�� ���� �̸��� �ּҸ� �޾ƿ� �� dao�� ���� �ѱ�
		User_Dao dao = new User_Dao();
		String del = req.getParameter("del");
		User_Dto list = dao.coutomerdel(del);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		return "/WEB-INF/views/User/User_Deletemessage.jsp";
	}
}


