package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import basic.BasicBean;
import model.Command;
import model.FactoryCommand;


public class customerController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		BasicBean basic = BasicBean.newInstance();
		
		  basic.setPath(req, "upload");
	      basic.setMax(50 * 1024 * 1024);
	      basic.setEncType("UTF-8");
	      basic.setMulti(req);
	    String url ="";
		Command command = null;
		
		String param = req.getParameter("param");
		
		if(param == null){
			param = basic.getMulti().getParameter("param");
		}  else{
			param = req.getParameter("param");
		}
		
		
		
		FactoryCommand factory = FactoryCommand.newInstance();
		command = factory.createCommand(param,basic.getMulti());
		
		try {
			url = (String)command.processCommand(req, resp);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
		
	}
}
