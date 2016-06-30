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
      String[] param_transport, String[] param_stay) {
      
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
                  search += "board_title like '%" + keyWords[i] + "%'" + "&&";
                  search2 += "search_card like '%" + keyWords[i] + "%'" + "&&";
                  //System.out.println(search + search2);
               } else {
                  search += "board_title like '%" + keyWords[i] + "%'";
                  search2 += "search_card like '%" + keyWords[i] + "%'";
                  //System.out.println(search + search2);

               }
            }
         }
      }

      String daoboard_region = "";
      if (param_region == null) {
         daoboard_region = "board_region like '%%'";
      } else {
         for (i = 0; i < param_region.length; i++) {
            if (i == param_region.length - 1) {
               daoboard_region += "board_region like '%" + param_region[i] + "%' ";
               //System.out.println("select * from searchTbl  where " + daoboard_region);
            } else if (i < param_region.length - 1) {
               daoboard_region += "board_region like '%" + param_region[i] + "%' ||";
               //System.out.println("select * from searchTbl  where " + daoboard_region);
            }
         }
      }

      String daoboard_theme = "";
      if (param_theme == null) {
         daoboard_theme = "board_theme like '%%'";
      } else {
         for (i = 0; i < param_theme.length; i++) {
            if (i == param_theme.length - 1) {
               daoboard_theme += "board_theme like '%" + param_theme[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoboard_theme);
            } else if (i < param_theme.length - 1) {
               daoboard_theme += "board_theme like '%" + param_theme[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoboard_theme);
            }

         }
      }

      String daoboard_transport = "";
      if (param_transport == null) {
         daoboard_transport = "board_transport like '%%'";
      } else {
         for (i = 0; i < param_transport.length; i++) {
            if (i == param_transport.length - 1) {
               daoboard_transport += "board_transport like '%" + param_transport[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoboard_transport);
            } else if (i < param_transport.length - 1) {
               daoboard_transport += "board_transport like '%" + param_transport[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoboard_transport);
            }

         }
      }

      String daoboard_stay = "";
      if (param_stay == null) {
         daoboard_stay = "board_stay like '%%'";
      } else {
         for (i = 0; i < param_stay.length; i++) {
            if (i == param_stay.length - 1) {
               daoboard_stay += "board_stay like '%" + param_stay[i] + "%' ";
               //System.out.println("select * from searchTbl where " + daoboard_stay);
            } else if (i < param_stay.length - 1) {
               daoboard_stay += "board_stay like '%" + param_stay[i] + "%' ||";
               //System.out.println("select * from searchTbl where " + daoboard_stay);
            }
         }
      }

      if (keyWord.equals("") || keyWord.isEmpty()) {
         sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id";
         } else if (keyField.equals("title+content")) {
         /*
         sql = "select * from searchTbl LEFT JOIN userNickTbl ON searchTbl.user_id = userNickTbl.user_id where ((replace(board_title, ' ','')" + search + ") || (search_card " + search2
               + ")) && ((" + daoboard_region + ") && (" + daoboard_theme + ")&&" + "(" + daoboard_transport + ")&&"
               + "(" + daoboard_stay + "))";
         //System.out.println(sql);*/
         sql = "Select *  From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where ((replace(board_title, ' ','')" + search + ") || (search_card " + search2
               + ")) && ((" + daoboard_region + ") && (" + daoboard_theme + ")&&" + "(" + daoboard_transport + ")&&"
               + "(" + daoboard_stay + "))";
         
      } else if (keyField.equals("writer")) {
         sql = "Select * From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where (replace(user_nick, ' ','') like '%" + keyWord + "%') && ((" + daoboard_region
               + ") && (" + daoboard_theme + ")&&" + "(" + daoboard_transport + ")&&" + "(" + daoboard_stay
               + "))";
      }
      else if (keyField.equals("tags")) {
         sql = "Select * From searchtbl JOIN boardtbl ON searchtbl.board_num = boardtbl.board_num JOIN usernicktbl ON searchtbl.user_id = usernicktbl.user_id where (replace(search_tag, ' ','') like '%" + keyWord + "%') && ((" + daoboard_region
               + ") && (" + daoboard_theme + ")&&" + "(" + daoboard_transport + ")&&" + "(" + daoboard_stay
               + "))";
      }
      System.out.println(sql);

      /*
       * sql = "select * from board  where ((" + daoboard_region + ") && (" +
       * daoboard_theme + ")&&" + "(" + daoboard_transport + ")&&" + "(" +
       * daoboard_stay + "))";
       */
      //System.out.println(sql); // 최종 선택된 sql문 출력

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
            String board_title =  rs.getString("board_title");
            String search_card =  rs.getString("search_card");
            String search_add =  rs.getString("search_add");
            String board_region =  rs.getString("board_region");
            String board_transport =  rs.getString("board_transport");
            String board_stay =  rs.getString("board_stay");
            String board_theme   =  rs.getString("board_theme");
            String search_tag =  rs.getString("search_tag");
            String board_header = rs.getString("board_header");
            int board_click = rs.getInt("board_click");
            int board_like = rs.getInt("board_like");

            dto.setBoard_num(board_num);
            dto.setUser_id(user_id);
            dto.setUser_nick(user_nick);
            dto.setBoard_title(board_title);
            dto.setSearch_card(search_card);
            dto.setSearch_add(search_add);
            dto.setBoard_region(board_region);
            dto.setBoard_transport(board_transport);
            dto.setBoard_stay(board_stay);
            dto.setBoard_theme(board_theme);
            dto.setSearch_tag(search_tag);
            dto.setBoard_header(board_header);
            dto.setBoard_click(board_click);
            dto.setBoard_like(board_like);

            board.add(dto);
         }
      } catch (Exception err) {
         System.out.println("실패 :" + err);
      } finally {
         pool.freeConnection(con, stmt, rs);
      }
      return board;
   }
}