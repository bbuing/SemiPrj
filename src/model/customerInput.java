package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class customerInput implements Command{
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 약관동의 후 가입페이지로 이동
		return "/WEB-INF/views/customer/Join.jsp";
	}
}
