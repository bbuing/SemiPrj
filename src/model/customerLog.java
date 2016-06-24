package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class customerLog implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 헤더에서 로그인 버튼 클릭시 로그인화면으로 이동
		return "/WEB-INF/views/customer/Login.jsp";
	}
}
