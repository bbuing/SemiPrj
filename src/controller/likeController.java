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

public class likeController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	      System.out.println("likeController");
	      
	      String board_num = req.getParameter("board_num");
	      String user_id = req.getParameter("user_id");
	      String board_like = req.getParameter("board_like");
	      
	      System.out.println("board_like : " + board_like);
	      //board_like ++;
	      
	      ReadDao dao = new ReadDao();
	      
	      dao.setLike(board_num, user_id, board_like);
	      
	      /*
	      String commentContent = req.getParameter("commentContent");
	      
	      int apprNumber = Integer.parseInt(req.getParameter("apprNumber"));
	      
	      HttpSession session = req.getSession();
	      
	      CommentDto dto = new CommentDto();
	      dto.setAppraisalNumber(apprNumber);
	      dto.setCommentContent(commentContent);
	      dto.setUserNumber((Integer)session.getAttribute("userNumber"));
	      CommentDao dao = new CommentDao();
	      dao.writeComment(dto);*/
	   }
	   
}	
