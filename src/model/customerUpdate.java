package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import basic.BasicBean;

public class customerUpdate implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//개인정보수정시 프로필 사진과 배경사진을 올릴 수 있기 때문에 BasicBean 생성
		BasicBean basic = BasicBean.newInstance();

	      basic.setPath(req, "upload");
	      basic.setMax(50 * 1024 * 1024);
	      basic.setEncType("UTF-8");
	      basic.setMulti(req);
		customerDao dao = new customerDao();
		
		customerDto emp = new customerDto();
		
		emp.setUser_name(basic.getMulti().getParameter("Viewname"));
		emp.setUser_email(basic.getMulti().getParameter("Viewemail"));
		emp.setUser_nick(basic.getMulti().getParameter("Viewnickname"));
		emp.setUser_pw(basic.getMulti().getParameter("Viewpw"));
		emp.setUser_question(basic.getMulti().getParameter("Viewquestion"));
		emp.setUser_answer(basic.getMulti().getParameter("Viewanswer"));
		emp.setUser_phone(basic.getMulti().getParameter("Viewphone1") + "-" + basic.getMulti().getParameter("Viewphone2") + "-"
				+ basic.getMulti().getParameter("Viewphone3"));
		emp.setUser_interest(basic.getMulti().getParameter("Checkbox1") + "-" + basic.getMulti().getParameter("Checkbox2") + "-"
				+ basic.getMulti().getParameter("Checkbox3")+ "-"
						+ basic.getMulti().getParameter("Checkbox4")+ "-"
								+ basic.getMulti().getParameter("Checkbox5"));
		//BasicBean에서 사진 이름들을 배열로 받아와서 넣음
		ArrayList<String> imagelist = basic.getUpFileInfo();
		emp.setUser_profile(imagelist.get(0));
		emp.setUser_hearder(imagelist.get(1));
		// 사진수정을 하지 않으면 db에서 받아왔던 사진이름을 다시 넣음
		if(emp.getUser_profile() == null){
			emp.setUser_profile(basic.getMulti().getParameter("profile"));
		}
		if(emp.getUser_hearder() == null){
			emp.setUser_hearder(basic.getMulti().getParameter("background"));
		}
		customerDto list = dao.coutomerUpdate(emp);
		
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/customer/Updatemessage.jsp";
	}
}


