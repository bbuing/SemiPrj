package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Log implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// ������� �α��� ��ư Ŭ���� �α���ȭ������ �̵�
		return "/WEB-INF/views/User/User_Login.jsp";
	}
}
