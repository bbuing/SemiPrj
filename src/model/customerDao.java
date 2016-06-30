package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import dbcp.DBConnectionMgr;

public class customerDao {
	//커멘드에서 받은 요청을 처리 사용하는 값을들 전체 초기화
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool = null;
	String user_id = null, user_pw = null, user_name = null, user_nick = null,
			user_email = null, user_question = null, user_answer = null, user_phone = null, 
			user_lev = null, user_profile=null, user_hearder = null, user_interest=null, title=null;
	
	// 회원 탈퇴
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
			System.out.println("삭제 실패 : " + err);
		}
		return emp;
	}
	//회원 가입
	public customerDto coutomerJoin(customerDto emp) {
			//처음 가입시 프로필 사진, 배경화면, 레벨은 지정하지 않기 때문에 db에 null이란 문자를 넣음
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
			System.out.println("가입 실패 : " + err);
		} 
		return null;
	}
	//개인정보수정 위해 개인정보 불어오기
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
			System.out.println("보여주기 뷰 실패 : " + e);
		}
	return emp;
	}
	//개인정보 수정
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
			// db에 수정된 개인정보를 넣은 후 로그인 상태에서 수정된 값들을 최신화 하기 위해 다시 dto에 담아서 커멘드로 넘긴다
			emp.setUser_name(rs.getString("user_name"));
			emp.setUser_email(rs.getString("user_email"));
			emp.setUser_profile(rs.getString("user_profile"));
			emp.setUser_hearder(rs.getString("user_header"));
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("정보 수정 실패 : " + err);
		} 
	return emp;
	}
	//id 찾기
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
			System.out.println("ID 찾기 실패 : " + err);
		} 
	return emp;
	}
	//비밀번호 1차 찾기 이름,별명,id,핸드폰 번호로 찾기
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
			System.out.println("PW 1차 찾기 실패 : " + err);
		} 
	return emp;
	}
	//비밀번호 2차 찾기 입력한 질문&답변으로 검색
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
				//답변이 맞으면 yes 틀리면 no를 넘긴다
				emp.setUser_answer("yes");
			} else{
				emp.setUser_answer("no");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception err) {
			System.out.println("PW 찾기 실패 : " + err);
		} 
		
	return emp;
	}
	// 비밀번호 2차 인증까지 맞았다면 새로운 비밀번호로 변경
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
			System.out.println("새로운 PW 수정 실패 : " + err);
		} 
	return emp;
	}
	//로그인
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
				//로그인 후 보여줄 정보들을 담는다
				emp.setUser_name(rs.getString("user_name"));
				emp.setUser_email(rs.getString("user_email"));
				emp.setUser_profile(rs.getString("user_profile"));
				emp.setUser_hearder(rs.getString("user_header"));
				emp.setUser_id(rs.getString("user_id"));
			}
		} catch (Exception e) {
			System.out.println("로그인 실패 : " + e);
		} finally{if(pool != null) pool.freeConnection(conn, pstmt, rs);}
	return emp;
	}
	// 내가 작성한 글 찾기, 작성한 글이 몇개가 있을지 모르기 때문에 벡터로 받아옴
	public Vector<BoardDto> coutomerMywrite(String mywrite) {
		Vector<BoardDto> board = new Vector<BoardDto>();
		try {
			pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            String sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = "
            		+ "boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where "
            		+ "((replace(search_title, ' ','')) || (search_card like '%%')) && "
            		+ "((search_region like '%서울%' ||search_region like '%인천%' ||search_region like '%경기도%' ||search_region like '%충청북도%' ||search_region like '%충청남도%' ||search_region like '%경상북도%' ||search_region like '%경상남도%' ||search_region like '%전라북도%' ||search_region like '%전라남도%' ||search_region like '%강원도%' ||search_region like '%제주도%' ) && "
            		+ "(search_theme like '%커플%' ||search_theme like '%가족%' ||search_theme like '%단체%' ||search_theme like '%나홀로%' ||search_theme like '%힐링%' ||search_theme like '%저가여행%' ||search_theme like '%바다%' ||search_theme like '%계곡%' ||search_theme like '%등산%' ||search_theme like '%반려동물%' )&&"
            		+ "(search_transport like '%자동차%' ||search_transport like '%비행기%' ||search_transport like '%배%' ||search_transport like '%기차%' ||search_transport like '%지하철%' ||search_transport like '%버스%' ||search_transport like '%도보%' )&&"
            		+ "(search_accommodation like '%펜션%' ||search_accommodation like '%호텔%' ||search_accommodation like '%모텔%' ||search_accommodation like '%게스트하우스%' ))"
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
			System.out.println("보여주기 뷰 실패 : " + e);
		}
	return board;
	}
	//로그아웃
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
			System.out.println("로그아웃 실패 : " + e);
		}
	return emp;
	}
}
