package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import dbcp.DBConnectionMgr;

public class GetBoardCommand implements Command {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		BoardDao dao = new BoardDao();
		String keyWord = req.getParameter("keyWord").toUpperCase();
		String keyField = req.getParameter("keyField");
		String[] region = req.getParameterValues("region");
		String[] theme = req.getParameterValues("theme");
		String[] transport = req.getParameterValues("transport");
		String[] accommodation = req.getParameterValues("accommodation");
		Vector<BoardDto> list = dao.getBoardList(keyField, keyWord,region, theme, transport, accommodation);
	

		req.setAttribute("list", list);

		return "index.jsp";

	}
}