package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class setReply implements Command {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		System.out.println("setReply called");
		HttpSession session = req.getSession();
		ReadDao dao = new ReadDao();
		ReadDto dto = (ReadDto) session.getAttribute("ReadDto"); //현재의 dto를 받아옴
		
		System.out.println(dto);
		
		int card_num = Integer.parseInt(req.getParameter("card_num")); //댓글을 다려는 카드 
		int card_index = Integer.parseInt(req.getParameter("card_index")); //현 보드에서의 카드 순번 인덱스
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
		
		
		
		//DB�뿉 �꽔
		dto = dao.createReply(dto, replyDto, card_index);
		
		
		session.setAttribute("ReadDto", dto);
		return "read/Read_final.jsp";
	}

}
