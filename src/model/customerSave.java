package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import com.oreilly.servlet.MultipartRequest;

import dbcp.DBConnectionMgr;

public class customerSave implements Command{
	private MultipartRequest multi;
	
	public customerSave(){}
	public customerSave(MultipartRequest multi) {
		this.multi = multi;
	}
	
	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			
			String[] Cards = multi.getParameterValues("theme");
			String[] Contents = multi.getParameterValues("card");
			String[] types = multi.getParameterValues("order");
			
			String sql = "";
		
			String user_id = multi.getParameter("user_id"); 
			String title_img = multi.getParameter("title_img_name");
			String title = multi.getParameter("title");
			String period = multi.getParameter("date");
			String[] selects = multi.getParameterValues("select");
			
			sql = "INSERT into boardTbl(user_id,board_title,board_status,board_date,board_header,board_period,board_tag,board_click,board_like) values(?,?,'upload',now(),?,?,?,0,0)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, user_id);
			stmt.setString(2, title);
			stmt.setString(3, title_img);
			stmt.setString(4, period);
			stmt.setString(5, "");
			
			stmt.executeUpdate();
			sql = "SELECT max(board_num) as board_num from boardTbl where user_id=" + user_id;
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			int board_num = 0;
			if(rs.next())
				board_num = rs.getInt("board_num");
			
			// 검색을 위해 게시글의 일부를 searchTbl에 입력
			sql = "INSERT into searchTbl(board_num,user_id,search_title,search_header,search_card,search_tag,search_region,search_transport,search_accommodation,search_theme) values(?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, board_num);
			stmt.setString(2, user_id); // user_id
			stmt.setString(3, title);	
			stmt.setString(4, title_img);
			String search_text = "";
			for(int i=0; i<Cards.length; i++) {
				search_text += Cards[i];
			}
			stmt.setString(5, search_text);
			stmt.setString(6, "");
			for(int i=0; i<selects.length; i++) {
				switch(i) {
					case 0:
						stmt.setString(7, selects[i]);
						break;
					case 1:
						stmt.setString(8, selects[i]);
						break;
					case 2:
						stmt.setString(9, selects[i]);
						break;
					case 3:
						stmt.setString(10, selects[i]);
						break;
				}
			}
			stmt.executeUpdate();
			// cardTbl에 본문 내용을 입력
			for(int i=0; i<Cards.length; i++) {
				sql = "INSERT into cardTbl(board_num,user_id,card_type,card_title,card_content) values(?,?,?,?,?)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, board_num);
				stmt.setString(2, user_id); // user_id
				stmt.setString(3, types[i]);
				stmt.setString(4, Integer.toString(i+1));
				stmt.setString(5, Cards[i]);
				stmt.executeUpdate();
			}
			// addTbl에 각각의 추가자료를 입력
			for(int i=0; i<Contents.length; i++) {
				String type = Contents[i];
				int card_num = 0;
				if(type.equals("photo") || type.equals("insta")) {
					 card_num = Integer.parseInt(Contents[++i])+1; 
					 String name = Contents[++i];
					 String desc = Contents[i+1];
					 
					 sql = "INSERT into addTbl(card_num,board_num,user_id,add_type,add_title,add_content) values(?,?,?,?,?,?)";
					 stmt = con.prepareStatement(sql);
					 
					 stmt.setInt(1, card_num);
					 stmt.setInt(2, board_num);
					 stmt.setString(3, user_id); 	// user_id
					 stmt.setString(4, type);
					 stmt.setString(5, name);
					 stmt.setString(6, desc);
					 
					 stmt.executeUpdate();
				}
				else if(type.equals("youtube") || type.equals("map")) {
					card_num = Integer.parseInt(Contents[++i])+1; 
					String name = "";
					if(type.equals("youtube"))
						name = "http://www.youtube.com/v/" + Contents[i+1] + "?version=3&enablejsapi=1";
					else
						name = Contents[i+1];
					
					 sql = "INSERT into addTbl(card_num,board_num,user_id,add_type,add_title,add_content) values(?,?,?,?,?,?)";
					 stmt = con.prepareStatement(sql);
					 
					 stmt.setInt(1, card_num);
					 stmt.setInt(2, board_num);
					 stmt.setString(3, user_id); 	// user_id
					 stmt.setString(4, type);
					 stmt.setString(5, name);
					 stmt.setString(6, "");
					 
					 stmt.executeUpdate();
				}
			}
		} catch(Exception err) {System.out.println("writeDB 에러 : " + err);}
		finally {try {if(pool != null) { pool.freeConnection(con, stmt, rs);}} catch(Exception err) { System.out.println(err);}}
		return "index.jsp";
	}

}
