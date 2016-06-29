package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Login implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//로그인을 하면 입력 받은 이메일,비밀번호를 넘겨서 db에서 검색 후 다시 받아옴
		User_Dao dao = new User_Dao();
		User_Dto emp = new User_Dto();
		emp.setUser_email(req.getParameter("Loginemail"));
		emp.setUser_pw(req.getParameter("Loginpw"));
		User_Dto list = dao.coutomerLogin(emp);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		return "/WEB-INF/views/User/User_Loginmessage.jsp";
	}
}


