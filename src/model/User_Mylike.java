package model;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_Mylike implements Command {
	
	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//내가 좋아요 한 글을 검색해서 받아옴
		User_Dao dao = new User_Dao();
		String mywrite = req.getParameter("mywrite");
		Vector<BoardDto> list1 = dao.coutomerMywrite(mywrite);
		
		HttpSession session = req.getSession();
		session.setAttribute("list1", list1);
		
		return "/WEB-INF/views/User/User_MyPage.jsp";
	}

}
