package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class User_Write implements Command{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		User_Dto dto = (User_Dto)req.getSession().getAttribute("list");
		String user_id = dto.getUser_id();
		req.setAttribute("user_id", user_id);
		return "/WEB-INF/views/User/Write.jsp";
	}
}