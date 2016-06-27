package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CardDto;
import model.ReadDao;
import model.ReadDto;
import model.ReplyDto;

public class getLikeController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("getlikeController");
		
		 
		 req.setCharacterEncoding("UTF-8");
	      resp.setCharacterEncoding("UTF-8");
	      resp.setContentType("text/plain"); 
	      
	      String board_num = req.getParameter("board_num");
	      
	      /*
	      HttpSession session = req.getSession();
	      ReadDao dao = new ReadDao();
	      ReadDto dto = (ReadDto) session.getAttribute("ReadDto"); //������ dto�� �޾ƿ�
		
	      PrintWriter out = resp.getWriter();
	      String result = dto.getBoard_like();
	      */
	      
	      ReadDao dao = new ReadDao();
	      PrintWriter out = resp.getWriter();
	      String result = dao.getLike(board_num);
	      
	   
	      out.println(result);

	   }
	   
}	
