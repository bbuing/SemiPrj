package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Terms implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 회원가입 버튼을 누르면 약관페이지로 이동
		return "/WEB-INF/views/User/User_Terms.jsp";
	}
}
