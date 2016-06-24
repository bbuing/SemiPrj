package model;

import java.io.IOException;
import java.sql.*;
import javax.servlet.http.*;
import javax.sql.rowset.serial.SerialException;

import dbcp.DBConnectionMgr;
import model.ReadDto;

public class ReadDao {
	private Connection con;
	private PreparedStatement pstmt, boardStmt, cardStmt, addStmt, replyStmt;
	private ResultSet rs, boardRs, cardRs, addRs, replyRs;
	private DBConnectionMgr pool;
	
	/*
	public ReadDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DB ���ᰴü ���� ���� : " + err);
		}
	}
	*/
	
	public ReadDto getBoard(int board_num) {
		
		/*req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String param = req.getParameter("param");*/

		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DB ���ᰴü ���� ���� : " + err);
		}

		
		String boardSql = "SELECT * FROM boardTbl LEFT JOIN userNickTbl ON boardTbl.user_id=userNickTbl.user_id where board_num=" + board_num;

		ReadDto dto = null;
		try {
			
			con = pool.getConnection();

			// board ��������
			boardStmt = con.prepareStatement(boardSql);
			boardRs = boardStmt.executeQuery();
			while (boardRs.next()) {
				dto = new ReadDto();
				dto.setBoard_num(boardRs.getString("board_num"));
				dto.setUser_id(boardRs.getString("user_id"));
				dto.setUser_nick(boardRs.getString("user_nick"));
				dto.setUser_profile(boardRs.getString("user_profile"));
				dto.setBoard_title(boardRs.getString("board_title"));
				dto.setBoard_status(boardRs.getString("board_status"));
				dto.setBoard_date(boardRs.getString("board_date"));
				dto.setBoard_header(boardRs.getString("board_header"));
				dto.setBoard_period(boardRs.getString("board_period"));
				dto.setBoard_tag(boardRs.getString("board_tag"));
				dto.setBoard_click(boardRs.getString("board_click"));
				dto.setBoard_like(boardRs.getString("board_like"));
				// card ��������
				String cardSql = "select * from cardTbl where board_num=" + board_num;
				cardStmt = con.prepareStatement(cardSql);
				cardRs = cardStmt.executeQuery();
				int board_card_cnt = 0;
				while (cardRs.next()) {
					int card_num = Integer.parseInt(cardRs.getString("card_num"));

					CardDto cardDto = new CardDto();
					cardDto.setCard_num(Integer.toString(card_num));
					cardDto.setCard_index(board_card_cnt);
					cardDto.setCard_type(cardRs.getString("card_type"));
					cardDto.setCard_title(cardRs.getString("card_title"));
					cardDto.setCard_content(cardRs.getString("card_content"));
					dto.setCardList(cardDto);

					// add ��������
					String addSql = "select * from addTbl where board_num=" + board_num + " and card_num=" + card_num;
					addStmt = con.prepareStatement(addSql);
					addRs = addStmt.executeQuery();
					int card_add_cnt = 0;
					while (addRs.next()) {
						AddDto addDto = new AddDto();
						addDto.setAdd_index(card_add_cnt);
						addDto.setAdd_num(addRs.getString("add_num"));
						addDto.setAdd_type(addRs.getString("add_type"));
						addDto.setAdd_title(addRs.getString("add_title"));
						addDto.setAdd_content(addRs.getString("add_content"));
						cardDto.setAddList(addDto);

						card_add_cnt++;
					}
					cardDto.setCard_add_cnt(card_add_cnt);

					// reply ��������
					String replySql = "SELECT * FROM replyTbl LEFT JOIN userNickTbl ON replyTbl.reply_user_id=userNickTbl.user_id where card_num="
							+ card_num;
					replyStmt = con.prepareStatement(replySql);
					replyRs = replyStmt.executeQuery();
					int card_reply_cnt = 0;
					while (replyRs.next()) {
						ReplyDto replyDto = new ReplyDto();
						replyDto.setReply_index(card_reply_cnt);
						replyDto.setReply_num(replyRs.getString("reply_num"));
						replyDto.setReply_user_id(replyRs.getString("reply_user_id"));
						replyDto.setReply_user_nick(replyRs.getString("user_nick"));
						replyDto.setReply_user_profile(replyRs.getString("user_profile"));
						replyDto.setReply_content(replyRs.getString("reply_content"));
						replyDto.setReply_date(replyRs.getString("reply_date"));
						cardDto.setReplyList(replyDto);

						card_reply_cnt++;
					}
					cardDto.setCard_reply_cnt(card_reply_cnt);
				}

				
			}

		} catch (Exception err) {
			System.out.println("read  : " + err);
		} finally {
			pool.freeConnection(con, boardStmt, boardRs);
		}
		return dto;
	}
	
	public ReadDto createReply(ReadDto dto, ReplyDto replyDto, int card_index) {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DB ���ᰴü ���� ���� : " + err);
		}

		String card_num = null;
		
		String boardSql = "INSERT INTO replyTbl (card_num, board_num, user_id, reply_user_id, reply_content, reply_date) VALUES " 
				+ "(?, ?, ?, ?, ?, now())";

		try {
			
			con = pool.getConnection();
			card_num = replyDto.getCard_num();
			//card_index = dto.getCardList().get(card_index);
			// board ��������
			pstmt = con.prepareStatement(boardSql);
			
			pstmt.setString(1, card_num);
			pstmt.setString(2, replyDto.getBoard_num());
			pstmt.setString(3, replyDto.getUser_id());
			pstmt.setString(4, replyDto.getReply_user_id());
			pstmt.setString(5, replyDto.getReply_content());
			
			pstmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("read  : " + err);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		dto.getCardList().get(card_index).setReplyList(replyDto);
		return dto;
	}
	
	public ReadDto clickLike(ReadDto dto) {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DB ���ᰴü ���� ���� : " + err);
		}

		String card_num = null;
		
		String boardSql = "INSERT INTO replyTbl (card_num, board_num, user_id, reply_user_id, reply_content, reply_date) VALUES " 
				+ "(?, ?, ?, ?, ?, now())";
/*
		try {
			
			con = pool.getConnection();
			card_num = replyDto.getCard_num();
			//card_index = dto.getCardList().get(card_index);
			// board ��������
			pstmt = con.prepareStatement(boardSql);
			
			pstmt.setString(1, card_num);
			pstmt.setString(2, replyDto.getBoard_num());
			pstmt.setString(3, replyDto.getUser_id());
			pstmt.setString(4, replyDto.getReply_user_id());
			pstmt.setString(5, replyDto.getReply_content());
			
			pstmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("read  : " + err);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		dto.getCardList().get(card_index).setReplyList(replyDto);*/
		return dto;
	}
}
