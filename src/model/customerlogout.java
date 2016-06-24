package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class customerlogout implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 로그아웃을 누르면 절대 없는 0 이라는 값으로 id를 검색해서 null이 되도록 하여 로그아웃함
		// id는 1번부터 시작하기 때문에 0번 id는 절대로 존재하지 않기 때문에 모든 회원이 로그아웃을 할 수 있음
		customerDao dao = new customerDao();
		
		String out = req.getParameter("out");
		customerDto list = dao.coutomerLogout(out);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		return "index.jsp";
	}
}
