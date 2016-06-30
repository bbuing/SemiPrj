package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import basic.BasicBean;

public class User_Update implements Command {
	
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		//�������������� ������ ������ �������� �ø� �� �ֱ� ������ BasicBean ����
		BasicBean basic = BasicBean.newInstance();

	      basic.setPath(req, "upload");
	      basic.setMax(50 * 1024 * 1024);
	      basic.setEncType("UTF-8");
	      basic.setMulti(req);
		User_Dao dao = new User_Dao();
		
		User_Dto emp = new User_Dto();
		
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
		//BasicBean���� ���� �̸����� �迭�� �޾ƿͼ� ����
		ArrayList<String> imagelist = basic.getUpFileInfo();
		emp.setUser_profile(imagelist.get(0));
		emp.setUser_header(imagelist.get(1));
		// ���������� ���� ������ db���� �޾ƿԴ� �����̸��� �ٽ� ����
		if(emp.getUser_profile() == null){
			emp.setUser_profile(basic.getMulti().getParameter("profile"));
		}
		if(emp.getUser_header() == null){
			emp.setUser_header(basic.getMulti().getParameter("background"));
		}
		User_Dto list = dao.coutomerUpdate(emp);
		
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		
		return "/WEB-INF/views/User/User_Updatemessage.jsp";
	}
}


