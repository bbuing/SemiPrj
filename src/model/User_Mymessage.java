package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Mymessage implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// ���������� ���� �˸� ������ �̵�
		return "/WEB-INF/views/User/User_MyPage.jsp";
	}
}
