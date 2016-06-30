package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcp.DBConnectionMgr;

public class User_Emailchk { // 이메일 중복 확인
	 private static User_Emailchk instance = new User_Emailchk();
	    
	    public static User_Emailchk getInstance(){
	        return instance;
	    }
	public int confirmId(String id)throws Exception{
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBConnectionMgr pool = null;
        String sql="";
        int x = -1;
        try{
        	pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            sql="select * from userTbl where user_email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();
            if(rs.next())
                x=1; 
            else
                x=-1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            execClose(rs,pstmt,conn);
        }        
        return x;
    }
    
    public void execClose(ResultSet rs, PreparedStatement pstmt, Connection conn)throws Exception{
        if(rs !=null) try{rs.close();}catch(SQLException sqle){}
        if(pstmt !=null) try{pstmt.close();}catch(SQLException sqle){}
        if(conn !=null) try{conn.close();}catch(SQLException sqle){}
    }
}