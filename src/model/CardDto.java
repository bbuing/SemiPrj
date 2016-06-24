package model;

import java.util.Vector;

public class CardDto {
	private String card_num;
	private int card_index;
	private String card_type;
	private String card_title;
	private String card_content;
	private int card_add_cnt;
	private int card_reply_cnt;
	private Vector<AddDto> addList = new Vector<AddDto>();
	private Vector<ReplyDto> replyList = new Vector<ReplyDto>();
	
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	
	public int getCard_index() {
		return card_index;
	}
	public void setCard_index(int card_index) {
		this.card_index = card_index;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getCard_title() {
		return card_title;
	}
	public void setCard_title(String card_title) {
		this.card_title = card_title;
	}
	public String getCard_content() {
		return card_content;
	}
	public void setCard_content(String card_content) {
		this.card_content = card_content;
	}
	public int getCard_add_cnt() {
		return card_add_cnt;
	}
	public void setCard_add_cnt(int card_add_cnt) {
		this.card_add_cnt = card_add_cnt;
	}
	public int getCard_reply_cnt() {
		return card_reply_cnt;
	}
	public void setCard_reply_cnt(int card_reply_cnt) {
		this.card_reply_cnt = card_reply_cnt;
	}

	public Vector<AddDto> getAddList() {
		return addList;
	}
	public void setAddList(AddDto addDto) {
		addList.add(addDto);
	}
	
	
	public Vector<ReplyDto> getReplyList() {
		return replyList;
	}
	public void setReplyList(ReplyDto replyDto) {
		replyList.add(replyDto);
	}
}
