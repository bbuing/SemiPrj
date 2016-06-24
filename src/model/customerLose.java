package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class customerLose implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 아이디/비밀번호 찾기 페이지로 이동
		return "/WEB-INF/views/customer/Loseidpw.jsp";
	}
}
