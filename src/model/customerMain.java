package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class customerMain implements Command{
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// 메인 페이지로 이동
		return "index.jsp";
	}
}
