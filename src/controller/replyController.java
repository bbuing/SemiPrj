package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CardDto;
import model.ReadDao;
import model.ReadDto;
import model.ReplyDto;

public class replyController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String param = req.getParameter("param");
		
		if(param.equals("reply")) {
			HttpSession session = req.getSession();
			ReadDao dao = new ReadDao();
			ReadDto dto = (ReadDto) session.getAttribute("ReadDto"); //������ dto�� �޾ƿ�
			
			System.out.println("replyController");
			
			int card_num = Integer.parseInt(req.getParameter("card_num")); //����� �ٷ��� ī�� 
			int card_index = Integer.parseInt(req.getParameter("card_index")); //�� ���忡���� ī�� ���� �ε���
			String board_num = req.getParameter("board_num");
			CardDto cardDto = dto.getCardList().get(card_index);
			System.out.println(card_index);
			ReplyDto replyDto = new ReplyDto();
			
			int card_reply_cnt = Integer.parseInt(req.getParameter("card_reply_cnt"));
			
			replyDto.setReply_index(card_reply_cnt);
			replyDto.setCard_num(req.getParameter("card_num"));
			replyDto.setBoard_num(req.getParameter("board_num"));
			replyDto.setUser_id(req.getParameter("user_id"));
			replyDto.setReply_user_id(req.getParameter("reply_user_id"));
			replyDto.setReply_user_nick(req.getParameter("user_nick"));
			replyDto.setReply_user_profile(req.getParameter("user_profile"));
			replyDto.setReply_content(req.getParameter("reply_content"));
			cardDto.setReplyList(replyDto);
			
			
			
			//DB�� �����Ѵ� 
			dto = dao.createReply(dto, replyDto, card_index);
			
			
			session.setAttribute("ReadDto", dto);
			resp.sendRedirect("http://localhost:8080/semiProject/read.do?param=read&board_num=" + board_num);
			
		}
		
	}

}	
