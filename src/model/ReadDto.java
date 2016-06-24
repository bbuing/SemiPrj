package model;

import java.util.Vector;

public class ReadDto {
	private String board_num;
	private String user_id;
	private String user_nick;
	private String user_profile;
	
	private String board_title;
	private String board_status;
	private String board_date;
	private String board_header;
	private String board_period;
	private String board_tag;
	private String board_click;
	private String board_like;
	
	private Vector<CardDto> cardList = new Vector<CardDto>(); 
	
	
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
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_profile() {
		return user_profile;
	}
	public void setUser_profile(String user_profile) {
		this.user_profile = user_profile;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_status() {
		return board_status;
	}
	public void setBoard_status(String board_status) {
		this.board_status = board_status;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_header() {
		return board_header;
	}
	public void setBoard_header(String board_header) {
		this.board_header = board_header;
	}
	public String getBoard_period() {
		return board_period;
	}
	public void setBoard_period(String board_period) {
		this.board_period = board_period;
	}
	public String getBoard_click() {
		return board_click;
	}
	public void setBoard_click(String board_click) {
		this.board_click = board_click;
	}
	public String getBoard_like() {
		return board_like;
	}
	public void setBoard_like(String board_like) {
		this.board_like = board_like;
	}
	public String getBoard_tag() {
		return board_tag;
	}
	public void setBoard_tag(String board_tag) {
		this.board_tag = board_tag;
	}
	
	public Vector<CardDto> getCardList() {
		return cardList;
	}
	public void setCardList(CardDto cardDto) {
		cardList.add(cardDto);
	}
	
	
	
	
	
}


