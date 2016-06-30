package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import dbcp.DBConnectionMgr;

public class customerDao {
	//Ŀ��忡�� ���� ��û�� ó�� ����ϴ� ������ ��ü �ʱ�ȭ
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool = null;
	String user_id = null, user_pw = null, user_name = null, user_nick = null,
			user_email = null, user_question = null, user_answer = null, user_phone = null, 
			user_lev = null, user_profile=null, user_hearder = null, user_interest=null, title=null;
	
	// ȸ�� Ż��
	public customerDto coutomerdel(String del) {
		customerDto emp = new customerDto();
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
			emp.setUser_hearder(rs.getString("user_header"));
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���� ���� : " + err);
		}
		return emp;
	}
	//ȸ�� ����
	public customerDto coutomerJoin(customerDto emp) {
			//ó�� ���Խ� ������ ����, ���ȭ��, ������ �������� �ʱ� ������ db�� null�̶� ���ڸ� ����
			String nodata = "null";
			try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "insert into userTbl(user_name, user_nick,user_email,user_pw,user_question,user_answer,user_phone,user_interest, user_profile,user_header, user_lev) values(?,?,?,?,?,?,?,?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getUser_name());
			pstmt.setString(2, emp.getUser_nick());
			pstmt.setString(3, emp.getUser_email());
			pstmt.setString(4, emp.getUser_pw());
			pstmt.setString(5, emp.getUser_question());
			pstmt.setString(6, emp.getUser_answer());
			pstmt.setString(7, emp.getUser_phone());
			pstmt.setString(8, emp.getUser_interest());
			pstmt.setString(9, nodata);
			pstmt.setString(10, nodata);
			pstmt.setString(11, nodata);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���� ���� : " + err);
		} 
		return null;
	}
	//������������ ���� �������� �Ҿ����
	public customerDto coutomerView(String view) {
		customerDto emp = new customerDto();
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
				emp.setUser_hearder(rs.getString("user_header"));
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
	public customerDto coutomerUpdate(customerDto emp) {
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
			pstmt.setString(8, emp.getUser_hearder());
			pstmt.setString(9, emp.getUser_email());
			pstmt.executeUpdate();
			// db�� ������ ���������� ���� �� �α��� ���¿��� ������ ������ �ֽ�ȭ �ϱ� ���� �ٽ� dto�� ��Ƽ� Ŀ���� �ѱ��
			emp.setUser_name(rs.getString("user_name"));
			emp.setUser_email(rs.getString("user_email"));
			emp.setUser_profile(rs.getString("user_profile"));
			emp.setUser_hearder(rs.getString("user_header"));
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("���� ���� ���� : " + err);
		} 
	return emp;
	}
	//id ã��
	public customerDto coutomerLoseid(customerDto emp) {
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
	public customerDto coutomerLosepw1(customerDto emp) {
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
	public customerDto coutomerLosepw2(customerDto emp, String inputans) {
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
	public customerDto coutomerNewpw(customerDto emp) {
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
	public customerDto coutomerLogin(customerDto emp) {
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "select user_name, user_email, user_profile, user_header, user_id from userTbl where user_email=? and user_pw=?";
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getUser_email());
			pstmt.setString(2, emp.getUser_pw());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//�α��� �� ������ �������� ��´�
				emp.setUser_name(rs.getString("user_name"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setUser_profile(rs.getString("user_profile"));
				emp.setUser_hearder(rs.getString("user_header"));
				emp.setUser_id(rs.getString("user_id"));
			}
		} catch (Exception e) {
			System.out.println("�α��� ���� : " + e);
		} finally{if(pool != null) pool.freeConnection(conn, pstmt, rs);}
	return emp;
	}
	// ���� �ۼ��� �� ã��, �ۼ��� ���� ��� ������ �𸣱� ������ ���ͷ� �޾ƿ�
	public Vector<BoardDto> coutomerMywrite(String mywrite) {
		Vector<BoardDto> board = new Vector<BoardDto>();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = "
            		+ "boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where "
            		+ "((replace(search_title, ' ','')) || (search_card like '%%')) && "
            		+ "((search_region like '%����%' ||search_region like '%��õ%' ||search_region like '%��⵵%' ||search_region like '%��û�ϵ�%' ||search_region like '%��û����%' ||search_region like '%���ϵ�%' ||search_region like '%��󳲵�%' ||search_region like '%����ϵ�%' ||search_region like '%���󳲵�%' ||search_region like '%������%' ||search_region like '%���ֵ�%' ) && "
            		+ "(search_theme like '%Ŀ��%' ||search_theme like '%����%' ||search_theme like '%��ü%' ||search_theme like '%��Ȧ��%' ||search_theme like '%����%' ||search_theme like '%��������%' ||search_theme like '%�ٴ�%' ||search_theme like '%���%' ||search_theme like '%���%' ||search_theme like '%�ݷ�����%' )&&"
            		+ "(search_transport like '%�ڵ���%' ||search_transport like '%�����%' ||search_transport like '%��%' ||search_transport like '%����%' ||search_transport like '%����ö%' ||search_transport like '%����%' ||search_transport like '%����%' )&&"
            		+ "(search_accommodation like '%���%' ||search_accommodation like '%ȣ��%' ||search_accommodation like '%����%' ||search_accommodation like '%�Խ�Ʈ�Ͽ콺%' ))"
            		+ " && searchtbl.user_id = (select user_id from userTbl where user_email = ?)";
            		
           
            pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mywrite);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBoard_num(rs.getString("board_num"));
				
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_nick(rs.getString("user_nick"));
				dto.setSearch_title(rs.getString("search_title"));
				dto.setSearch_card(rs.getString("search_card"));
				dto.setSearch_add(rs.getString("search_add"));
				
				dto.setSearch_region(rs.getString("search_region"));
				dto.setSearch_transport(rs.getString("search_transport"));
				dto.setSearch_accommodation(rs.getString("search_accommodation"));
				dto.setSearch_theme(rs.getString("search_theme"));
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
	public customerDto coutomerLogout(String out) {
		customerDto emp = new customerDto();
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
}
