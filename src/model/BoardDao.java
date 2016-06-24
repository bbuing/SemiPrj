package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;

import dbcp.DBConnectionMgr;

public class BoardDao {

   private Connection con;
   private PreparedStatement stmt;
   private DBConnectionMgr pool;
   private ResultSet rs;

   public Vector<BoardDto> getBoardList(String keyField, String keyWord, String[] param_region, String[] param_theme,
      String[] param_transport, String[] param_accommodation) {
      
      Vector<BoardDto> board = new Vector<BoardDto>();
      String sql = "";
      StringTokenizer st = new StringTokenizer(keyWord, " ");
      String[] keyWords = new String[st.countTokens()];

      int i = 0;
      String search = "";
      String search2 = "";
      while (st.hasMoreTokens()) {

         keyWords[i] = st.nextToken().trim().toUpperCase();
         i++;
      }
      if (keyWords.length==0) {
         search2 = "like '%%'";
      } else {
         for (i = 0; i < keyWords.length; i++) {
            if (keyWords.length == 1) {
               search += "like '%" + keyWords[i] + "%'";
               search2 += "like '%" + keyWords[i] + "%'";
               //System.out.println(search + search2);
            } else {
               if (i == 0) {
                  search += " like '%" + keyWords[i] + "%'" + "&&";
                  search2 += "like '%" + keyWords[i] + "%'" + "&&";
                  //System.out.println(search + search2);
               } else if (i < keyWords.length - 1) {
                  search += "search_title like '%" + keyWords[i] + "%'" + "&&";
                  search2 += "search_card like '%" + keyWords[i] + "%'" + "&&";
                  //System.out.println(search + search2);
               } else {
                  search += "search_title like '%" + keyWords[i] + "%'";
                  search2 += "search_card like '%" + keyWords[i] + "%'";
                  //System.out.println(search + search2);

               }
            }
         }
      }

      String daoSearch_region = "";
      if (param_region == null) {
         daoSearch_region = "search_region like '%%'";
      } else {
         for (i = 0; i < param_region.length; i++) {
            if (i == param_region.length - 1) {
               daoSearch_region += "search_region like '%" + param_region[i] + "%' ";
               //System.out.println("select * from searchTbl  where " + daoSearch_region);
            } else if (i < param_region.length - 1) {
               daoSearch_region += "search_region like '%" + param_region[i] + "%' ||";
               //System.out.println("select * from searchTbl  where " + daoSearch_region);
            }
         }
      }

      String daoSearch_theme = "";
      if (param_theme == null) {
         daoSearch_theme = "search_theme like '%%'";
      } else {
         for (i = 0; i < param_theme.length; i++) {
            if (i == param_theme.length - 1) {
               daoSearch_theme += "search_theme like '%" + param_theme[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoSearch_theme);
            } else if (i < param_theme.length - 1) {
               daoSearch_theme += "search_theme like '%" + param_theme[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoSearch_theme);
            }

         }
      }

      String daoSearch_transport = "";
      if (param_transport == null) {
         daoSearch_transport = "search_transport like '%%'";
      } else {
         for (i = 0; i < param_transport.length; i++) {
            if (i == param_transport.length - 1) {
               daoSearch_transport += "search_transport like '%" + param_transport[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoSearch_transport);
            } else if (i < param_transport.length - 1) {
               daoSearch_transport += "search_transport like '%" + param_transport[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoSearch_transport);
            }

         }
      }

      String daoSearch_accommodation = "";
      if (param_accommodation == null) {
         daoSearch_accommodation = "search_accommodation like '%%'";
      } else {
         for (i = 0; i < param_accommodation.length; i++) {
            if (i == param_accommodation.length - 1) {
               daoSearch_accommodation += "search_accommodation like '%" + param_accommodation[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoSearch_accommodation);
            } else if (i < param_accommodation.length - 1) {
               daoSearch_accommodation += "search_accommodation like '%" + param_accommodation[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoSearch_accommodation);
            }
         }
      }

      if (keyWord.equals("") || keyWord.isEmpty()) {
         sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id";
         } else if (keyField.equals("title+content")) {
         /*
         sql = "select * from searchTbl LEFT JOIN userNickTbl ON searchTbl.user_id = userNickTbl.user_id where ((replace(search_title, ' ','')" + search + ") || (search_card " + search2
               + ")) && ((" + daoSearch_region + ") && (" + daoSearch_theme + ")&&" + "(" + daoSearch_transport + ")&&"
               + "(" + daoSearch_accommodation + "))";
         //System.out.println(sql);*/
         sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where ((replace(search_title, ' ','')" + search + ") || (search_card " + search2
               + ")) && ((" + daoSearch_region + ") && (" + daoSearch_theme + ")&&" + "(" + daoSearch_transport + ")&&"
               + "(" + daoSearch_accommodation + "))";
         
      } else if (keyField.equals("writer")) {
         sql = "Select * From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where (replace(user_nick, ' ','') like '%" + keyWord + "%') && ((" + daoSearch_region
               + ") && (" + daoSearch_theme + ")&&" + "(" + daoSearch_transport + ")&&" + "(" + daoSearch_accommodation
               + "))";
      }
      else if (keyField.equals("tags")) {
         sql = "Select * From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where (replace(search_tag, ' ','') like '%" + keyWord + "%') && ((" + daoSearch_region
               + ") && (" + daoSearch_theme + ")&&" + "(" + daoSearch_transport + ")&&" + "(" + daoSearch_accommodation
               + "))";
      }

      /*
       * sql = "select * from board  where ((" + daoSearch_region + ") && (" +
       * daoSearch_theme + ")&&" + "(" + daoSearch_transport + ")&&" + "(" +
       * daoSearch_accommodation + "))";
       */
      System.out.println(sql);

      try {
         pool = DBConnectionMgr.getInstance();
         con = pool.getConnection();
         stmt = con.prepareStatement(sql);
         rs = stmt.executeQuery();

         while (rs.next()) {

            BoardDto dto = new BoardDto();

            String board_num = rs.getString("board_num");
            String user_id =  rs.getString("user_id");
            String user_nick =  rs.getString("user_nick");
            String search_title =  rs.getString("search_title");
            String search_card =  rs.getString("search_card");
            String search_add =  rs.getString("search_add");
            String search_region =  rs.getString("search_region");
            String search_transport =  rs.getString("search_transport");
            String search_accommodation =  rs.getString("search_accommodation");
            String search_theme   =  rs.getString("search_theme");
            String search_tag =  rs.getString("search_tag");
            String search_header = rs.getString("search_header");
            int board_click = rs.getInt("board_click");
            int board_like = rs.getInt("board_like");

            dto.setBoard_num(board_num);
            dto.setUser_id(user_id);
            dto.setUser_nick(user_nick);
            dto.setSearch_title(search_title);
            dto.setSearch_card(search_card);
            dto.setSearch_add(search_add);
            dto.setSearch_region(search_region);
            dto.setSearch_transport(search_transport);
            dto.setSearch_accommodation(search_accommodation);
            dto.setSearch_theme(search_theme);
            dto.setSearch_tag(search_tag);
            dto.setSearch_header(search_header);
            dto.setBoard_click(board_click);
            dto.setBoard_like(board_like);
            
            

            board.add(dto);

         }
      } catch (Exception err) {
         System.out.println("½ÇÆÐ :" + err);
      } finally {
         pool.freeConnection(con, stmt, rs);
      }
      return board;
   }
}