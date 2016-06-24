package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcp.DBConnectionMgr;

public class Nicknamechk { //별명 중복 확인
	 private static Nicknamechk instance = new Nicknamechk();
	    
	    public static Nicknamechk getInstance(){
	        return instance;
	    }
	public int confirmId(String nick)throws Exception{
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBConnectionMgr pool = null;
        String sql="";
        int x= -1;
        try{
        	pool=DBConnectionMgr.getInstance();
            conn= pool.getConnection();
            sql="select * from userTbl where user_nick = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nick);
            rs= pstmt.executeQuery();
            
            if(rs.next())
                x = 1; 
            else
                x = -1;
            
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