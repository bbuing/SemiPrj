package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import dbcp.DBConnectionMgr;

public class User_Dao {
	//Ŀ��忡�� ���� ��û�� ó�� ����ϴ� ������ ��ü �ʱ�ȭ
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool = null;
	String user_id = null, user_pw = null, user_name = null, user_nick = null,
			user_email = null, user_question = null, user_answer = null, user_phone = null, 
			user_lev = null, user_profile=null, user_header = null, user_interest=null, title=null;
	
	// ȸ�� Ż��
	public User_Dto coutomerdel(String del) {
		User_Dto emp = new User_Dto();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "delete from userTbl where user_email=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, del);
			pstmt.executeUpdate();
			emp.setUser_name(rs.getString("user_name"));
			emp.setUser_email(rs.getString("user_email"));
			emp.setUser_profile(rs.getString("user_profile"));
			emp.setUser_header(rs.getString("user_header"));
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���� ���� : " + err);
		}
		return emp;
	}
	//ȸ�� ����
	public User_Dto coutomerJoin(User_Dto emp) {
			//ó�� ���Խ� ������ ����, ���ȭ��, ������ �������� �ʱ� ������ db�� null�̶� ���ڸ� ����
			try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "insert into userTbl(user_name, user_nick,user_email,user_pw,user_question,user_answer,user_phone,user_interest) values(?,?,?,?,?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getUser_name());
			pstmt.setString(2, emp.getUser_nick());
			pstmt.setString(3, emp.getUser_email());
			pstmt.setString(4, emp.getUser_pw());
			pstmt.setString(5, emp.getUser_question());
			pstmt.setString(6, emp.getUser_answer());
			pstmt.setString(7, emp.getUser_phone());
			pstmt.setString(8, emp.getUser_interest());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception err) {
			System.out.println("���� ���� : " + err);
		} 
			try {
				pool=DBConnectionMgr.getInstance();
	            conn= pool.getConnection();
	            String sql = "select user_id from userTbl where user_email=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, emp.getUser_email());
	            ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					emp.setUser_id(rs.getString("user_id"));
				}
				rs.close();
	            String sql2 = "insert into userNickTbl(user_id, user_nick) values(?,?)";
	    		pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, emp.getUser_id());
				pstmt.setString(2, emp.getUser_nick());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				
			} catch (Exception err) {
				System.out.println("�г��� ���̺� ���� ���� : " + err);
			} 
		return null;
	}
	//������������ ���� �������� �Ҿ����
	public User_Dto coutomerView(String view) {
		User_Dto emp = new User_Dto();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select * from userTbl where user_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, view);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_name(rs.getString("user_name"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setUser_nick(rs.getString("user_nick"));
				emp.setUser_pw(rs.getString("user_pw"));
				emp.setUser_question(rs.getString("user_question"));
				emp.setUser_answer(rs.getString("user_answer"));
				emp.setUser_phone(rs.getString("user_phone"));
				emp.setUser_interest(rs.getString("user_interest"));
				emp.setUser_profile(rs.getString("user_profile"));
				emp.setUser_header(rs.getString("user_header"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("�����ֱ� �� ���� : " + e);
		}
	return emp;
	}
	//�������� ����
	public User_Dto coutomerUpdate(User_Dto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "update userTbl set user_nick = ?,user_pw= ?,user_question = ?,user_answer = ?, user_phone = ?, user_interest = ?, user_profile = ?, user_header=? where user_email=?";
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getUser_nick());
			pstmt.setString(2, emp.getUser_pw());
			pstmt.setString(3, emp.getUser_question());
			pstmt.setString(4, emp.getUser_answer());
			pstmt.setString(5, emp.getUser_phone());
			pstmt.setString(6, emp.getUser_interest());
			pstmt.setString(7, emp.getUser_profile());
			pstmt.setString(8, emp.getUser_header());
			pstmt.setString(9, emp.getUser_email());
			pstmt.executeUpdate();
			// db�� ������ ���������� ���� �� �α��� ���¿��� ������ ������ �ֽ�ȭ �ϱ� ���� �ٽ� dto�� ��Ƽ� Ŀ���� �ѱ��
			emp.setUser_name(rs.getString("user_name"));
			emp.setUser_email(rs.getString("user_email"));
			emp.setUser_profile(rs.getString("user_profile"));
			emp.setUser_header(rs.getString("user_header"));
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���� ���� ���� : " + err);
		} 	try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_id from userTbl where user_email=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getUser_email());
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_id(rs.getString("user_id"));
			}
			rs.close();
			String sql2 = "update userNickTbl set user_nick = ?, user_profile = ? where user_id=?";
            pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, emp.getUser_nick());
			pstmt.setString(2, emp.getUser_profile());
			pstmt.setString(3, emp.getUser_id());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception err) {
			System.out.println("�г��� ���̺� ���� ���� : " + err);
		} 
	return emp;
	}
	//id ã��
	public User_Dto coutomerLoseid(User_Dto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_email from userTbl where user_name = ? and user_nick =  ? and user_phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getUser_name());
			pstmt.setString(2, emp.getUser_nick());
			pstmt.setString(3, emp.getUser_phone());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_email(rs.getString("user_email"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("ID ã�� ���� : " + err);
		} 
	return emp;
	}
	//��й�ȣ 1�� ã�� �̸�,����,id,�ڵ��� ��ȣ�� ã��
	public User_Dto coutomerLosepw1(User_Dto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_email,user_question from userTbl where user_name = ? and user_nick =  ? and user_email = ? and user_phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getUser_name());
			pstmt.setString(2, emp.getUser_nick());
			pstmt.setString(3, emp.getUser_email());
			pstmt.setString(4, emp.getUser_phone());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_email(rs.getString("user_email"));
				emp.setUser_question(rs.getString("user_question"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("PW 1�� ã�� ���� : " + err);
		} 
	return emp;
	}
	//��й�ȣ 2�� ã�� �Է��� ����&�亯���� �˻�
	public User_Dto coutomerLosepw2(User_Dto emp, String inputans) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_answer from userTbl where user_email = '"+emp.getUser_email()+"'";
            pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_answer(rs.getString("user_answer"));
			}
			if(inputans.equals(emp.getUser_answer())){
				//�亯�� ������ yes Ʋ���� no�� �ѱ��
				emp.setUser_answer("yes");
			} else{
				emp.setUser_answer("no");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("PW ã�� ���� : " + err);
		} 
		
	return emp;
	}
	// ��й�ȣ 2�� �������� �¾Ҵٸ� ���ο� ��й�ȣ�� ����
	public User_Dto coutomerNewpw(User_Dto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "update userTbl set user_pw = ? where user_email='"+emp.getUser_email()+"'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getUser_pw());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���ο� PW ���� ���� : " + err);
		} 
	return emp;
	}
	//�α���
	public User_Dto coutomerLogin(User_Dto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_name,user_nick, user_email, user_profile, user_header, user_id from userTbl where user_email=? and user_pw=?";
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getUser_email());
			pstmt.setString(2, emp.getUser_pw());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//�α��� �� ������ �������� ��´�
				emp.setUser_name(rs.getString("user_name"));
				emp.setUser_nick(rs.getString("user_nick"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setUser_profile(rs.getString("user_profile"));
				emp.setUser_header(rs.getString("user_header"));
				emp.setUser_id(rs.getString("user_id"));
				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("�α��� ���� : " + e);
		}
	return emp;
	}
	// ���� �ۼ��� �� ã��, �ۼ��� ���� ��� ������ �𸣱� ������ ���ͷ� �޾ƿ�
	public Vector<BoardDto> coutomerMywrite(String mywrite) {
		Vector<BoardDto> board = new Vector<BoardDto>();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "SELECT  A.*, b.user_id, b.user_nick ,c.search_tag, C.search_card , C.search_add FROM boardTbl AS A INNER JOIN userTbl AS B INNER JOIN searchTbl AS C  ON A.user_id = ? && B.user_id = ? && c.user_id = ?;";
            		
           
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mywrite);
			pstmt.setString(2, mywrite);
			pstmt.setString(3, mywrite);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBoard_num(rs.getString("board_num"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_nick(rs.getString("user_nick"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setSearch_card(rs.getString("search_card"));
				dto.setSearch_add(rs.getString("search_add"));
				dto.setBoard_header(rs.getString("board_header"));
				dto.setBoard_region(rs.getString("board_region"));
				dto.setBoard_transport(rs.getString("board_transport"));
				dto.setBoard_stay(rs.getString("board_stay"));
				dto.setBoard_theme(rs.getString("board_theme"));
				dto.setSearch_tag(rs.getString("search_tag"));
				dto.setBoard_click(rs.getInt("board_click"));
				dto.setBoard_like(rs.getInt("board_like"));
				board.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("�����ֱ� �� ���� : " + e);
		}
	return board;
	}
	//�α׾ƿ�
	public User_Dto coutomerLogout(String out) {
		User_Dto emp = new User_Dto();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_email from userTbl where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, out);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setUser_email(rs.getString("user_email"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("�α׾ƿ� ���� : " + e);
		}
	return emp;
	}
	//���� ���ƿ� ���� �� �˻�
	public Vector<BoardDto> coutomerMylike(String mywrite) {
		Vector<BoardDto> board = new Vector<BoardDto>();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "SELECT  A.*, b.user_nick ,c.board_click, C.board_like, D.user_like_board  FROM searchTbl AS A INNER JOIN userTbl AS B INNER JOIN boardTbl AS C INNER JOIN likeTbl AS D ON A.board_num = D.user_like_board = C.board_num && B.user_id = ? && D.user_id = ?";
            
           
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mywrite);
			pstmt.setString(2, mywrite);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBoard_num(rs.getString("board_num"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_nick(rs.getString("user_nick"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setSearch_card(rs.getString("search_card"));
				dto.setSearch_add(rs.getString("search_add"));
				dto.setBoard_header(rs.getString("board_header"));
				dto.setBoard_region(rs.getString("board_region"));
				dto.setBoard_transport(rs.getString("board_transport"));
				dto.setBoard_stay(rs.getString("board_stay"));
				dto.setBoard_theme(rs.getString("board_theme"));
				dto.setSearch_tag(rs.getString("search_tag"));
				dto.setBoard_click(rs.getInt("board_click"));
				dto.setBoard_like(rs.getInt("board_like"));
				board.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("�����ֱ� �� ���� : " + e);
		}
	return board;
	}
}
