package model;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class customerMylike implements Command {
	
	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//내가 좋아요 누른 글 목록을 검색해서 받아옴
		customerDao dao = new customerDao();
		String mywrite = req.getParameter("mylike");
		Vector<BoardDto> list1 = dao.coutomerMylike(mywrite);
		
		HttpSession session = req.getSession();
		session.setAttribute("list1", list1);
		
		return "/WEB-INF/views/customer/MyPage.jsp";
	}

}
