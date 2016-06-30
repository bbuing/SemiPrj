package model;

public class BoardDto {
   private String board_num = null;
   private String user_id = null;
   private String user_nick = null;
   private String board_title = null;
   private String search_card = null;
   private String search_add = null;
   private String board_region = null;
   private String board_transport = null;
   private String board_stay = null;
   private String board_theme   = null;
   private String search_tag = null;
   private String board_header = null;
   private int board_click = 0;
   private int board_like = 0;
   
   
   
   public String getBoard_header() {
      return board_header;
   }
   public void setBoard_header(String board_header) {
      this.board_header = board_header;
   }
   public int getBoard_click() {
      return board_click++;
   }
   public void setBoard_click(int board_click) {
      this.board_click = board_click;
   }
   public int getBoard_like() {
      return board_like;
   }
   public void setBoard_like(int board_like) {
      this.board_like = board_like;
   }
   public String getBoard_num() {
      return board_num;
   }
   public void setBoard_num(String board_num) {
      this.board_num = board_num;
   }
   public String getUser_id() {
      return user_id;
   }
   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }
   
   public String getUser_nick() {
      return user_nick.toUpperCase();
   }
   public void setUser_nick(String user_nick) {
      this.user_nick = user_nick.toUpperCase();
   }
   public String getBoard_title() {
      return board_title.toUpperCase();
   }
   public void setBoard_title(String board_title) {
      this.board_title = board_title.toUpperCase();
   }
   
   public String getSearch_card() {
      return search_card.toUpperCase();
   }
   public void setSearch_card(String search_card) {
      this.search_card = search_card.toUpperCase();
   }
   public String getSearch_add() {
      return search_add.toUpperCase();
   }
   public void setSearch_add(String search_add) {
      this.search_add = search_add.toUpperCase();
   }
   public String getBoard_region() {
      return board_region;
   }
   public void setBoard_region(String board_region) {
      this.board_region = board_region;
   }
   public String getBoard_transport() {
      return board_transport;
   }
   public void setBoard_transport(String board_transport) {
      this.board_transport = board_transport;
   }
   public String getBoard_stay() {
      return board_stay;
   }
   public void setBoard_stay(String board_stay) {
      this.board_stay = board_stay;
   }
   public String getBoard_theme() {
      return board_theme;
   }
   public void setBoard_theme(String board_theme) {
      this.board_theme = board_theme;
   }
   public String getSearch_tag() {
      return search_tag.toUpperCase();
   }
   public void setSearch_tag(String search_tag) {
      this.search_tag = search_tag.toUpperCase();
   }

   
}