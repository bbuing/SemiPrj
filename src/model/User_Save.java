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

public class User_Save implements Command{
	private MultipartRequest multi;
	
	public User_Save(){}
	public User_Save(MultipartRequest multi) {
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
			// Cards : 본문, Contents : 추가자료들, types : 각 본문의 주제
			String[] Cards = multi.getParameterValues("theme");
			String[] Contents = multi.getParameterValues("card");
			String[] types = multi.getParameterValues("order");
			
			String sql = "";
		
			String user_id = multi.getParameter("user_id");
			String title_img = multi.getParameter("title_img_name");
			String title = multi.getParameter("title");
			String period = multi.getParameter("date");
			// 선택된 체크박스로부터 넘겨 받은 값들을 DB에 저장하기 위해 ','로 구분해서 합쳐준다.
			String region[] = multi.getParameterValues("region");
			String theme[] = multi.getParameterValues("theme");
			String trans[] = multi.getParameterValues("trans");
			String stay[] = multi.getParameterValues("stay");
			
			String db_region = "", db_theme = "", db_trans = "", db_stay = "";
			for(int i=0; i<region.length; i++) {
				db_region += region[i];
				if(i != region.length-1)
					db_region += ",";
			}
			for(int i=0; i<theme.length; i++) {
				db_theme += theme[i];
				if(i != theme.length-1)
					db_theme += ",";
			}
			for(int i=0; i<trans.length; i++) {
				db_trans += trans[i];
				if(i != trans.length-1)
					db_trans += ",";
			}
			for(int i=0; i<stay.length; i++) {
				db_stay += stay[i];
				if(i != stay.length-1)
					db_stay += ",";
			}
			System.out.println(db_region);
			System.out.println(db_theme);
			System.out.println(db_trans);
			System.out.println(db_stay);
			// 1. boardTbl에 지금 작성한 데이터를 입력한다.
			sql = "INSERT into boardTbl(user_id,board_title,board_status,board_date,board_header,board_period,board_tag,board_region,board_theme,board_transport,board_stay,board_click,board_like) values(?,?,'upload',now(),?,?,?,?,?,?,?,0,0)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, user_id);
			stmt.setString(2, title);
			stmt.setString(3, title_img);
			stmt.setString(4, period);
			stmt.setString(5, "");
			stmt.setString(6, db_region);
			stmt.setString(7, db_theme);
			stmt.setString(8, db_trans);
			stmt.setString(9, db_stay);
			
			stmt.executeUpdate();
			// 2. 지금 작성한 글의 게시물 번호를 가져온다.
			sql = "SELECT max(board_num) as board_num from boardTbl where user_id=" + user_id;
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			int board_num = 0;
			if(rs.next())
				board_num = rs.getInt("board_num");
			
			// 3. 검색을 위해 게시글의 일부를 searchTbl에 입력
			sql = "INSERT into searchTbl(board_num,user_id,search_title,search_header,search_card,search_add,search_tag,search_region,search_transport,search_accommodation,search_theme) values(?,?,?,?,?,?,?,?,?,?,?)";
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
			stmt.setString(7, "");
			
			stmt.executeUpdate();
			// 4. cardTbl에 본문 내용을 입력
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
			// 5. addTbl에 각각의 추가자료를 입력
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
