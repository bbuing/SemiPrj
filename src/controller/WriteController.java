package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import basic.BasicBean;
import dbcp.DBConnectionMgr;
import model.Command;
import model.FactoryCommand;

public class WriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// ���� �ۼ��� �Խñ��� �̹������� ���� �� ������ ����� ���ؼ� ���� ���� ������ �Խñ��� ��ȣ�� �����´�.
		String sql = "SELECT max(board_num) as board_num FROM boardTbl";
		String board_num = null;
		try {
			con = pool.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				board_num = Integer.toString(rs.getInt("board_num") + 1); // ������ �Խñ��� ��ȣ�� �������̹Ƿ� ���Ƿ� 1�� ���� ���ο� �Խñ� ��ȣ�� �����Ѵ�.
			}
		} catch (Exception err) {System.out.println("WriteControl : " + err);}
		finally { if(pool != null) pool.freeConnection(con, stmt, rs);}
		
		BasicBean basic = BasicBean.newInstance();
		// ���� ������ ��ȣ�� �̹������� ������ ������ �����Ѵ�. �̶� ������ ������ ���� Write���������� �������� �ʰ� ����Ͽ����� �����ϵ��� �Ѵ�.
		basic.setPath(req, "upload/" + board_num);
		String path = basic.getPath();
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdir();
		}
	    basic.setMax(50 * 1024 * 1024);
	    basic.setEncType("UTF-8");
	    
	    String param = req.getParameter("param");
	    if(param == null) {
	    	basic.setMulti(req);
	    	param = basic.getMulti().getParameter("param");
	    }
	    
	    String url ="";
		Command command = null;
		FactoryCommand factory = FactoryCommand.newInstance();
		command = factory.createCommand(param,basic.getMulti());
		
		try {url = (String)command.processCommand(req, resp);} catch (SerialException e) {System.out.println("WriteControl : " + e);}
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
	}
}
