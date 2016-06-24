package model;

public class BoardDto {
   private String board_num = null;
   private String user_id = null;
   private String user_nick = null;
   private String search_title = null;
   private String search_card = null;
   private String search_add = null;
   private String search_region = null;
   private String search_transport = null;
   private String search_accommodation = null;
   private String search_theme   = null;
   private String search_tag = null;
   private String search_header = null;
   private int board_click = 0;
   private int board_like = 0;
   
   
   
   public String getSearch_header() {
      return search_header;
   }
   public void setSearch_header(String search_header) {
      this.search_header = search_header;
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
   public String getSearch_title() {
      return search_title.toUpperCase();
   }
   public void setSearch_title(String search_title) {
      this.search_title = search_title.toUpperCase();
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
   public String getSearch_region() {
      return search_region;
   }
   public void setSearch_region(String search_region) {
      this.search_region = search_region;
   }
   public String getSearch_transport() {
      return search_transport;
   }
   public void setSearch_transport(String search_transport) {
      this.search_transport = search_transport;
   }
   public String getSearch_accommodation() {
      return search_accommodation;
   }
   public void setSearch_accommodation(String search_accommodation) {
      this.search_accommodation = search_accommodation;
   }
   public String getSearch_theme() {
      return search_theme;
   }
   public void setSearch_theme(String search_theme) {
      this.search_theme = search_theme;
   }
   public String getSearch_tag() {
      return search_tag.toUpperCase();
   }
   public void setSearch_tag(String search_tag) {
      this.search_tag = search_tag.toUpperCase();
   }

   
}