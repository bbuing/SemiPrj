package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Lose implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// ���̵�/��й�ȣ ã�� �������� �̵�
		return "/WEB-INF/views/User/User_Loseidpw.jsp";
	}
}
