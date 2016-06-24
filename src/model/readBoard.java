package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import dbcp.DBConnectionMgr;

public class readBoard implements Command {
	

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		ReadDao dao = new ReadDao();
		ReadDto dto = new ReadDto();
		int board_num = Integer.parseInt(req.getParameter("board_num"));
		
		dto = dao.getBoard(board_num);
		HttpSession session = req.getSession();
		session.setAttribute("ReadDto", dto);
		return "/read/Read_final.jsp";
	}

}
