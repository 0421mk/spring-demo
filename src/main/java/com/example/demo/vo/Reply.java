package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int id;
	private int memberId;
	private int articleId;
	private int replyType;
	private int reReplyId;
	private String body;
	private String regDate;
	private String updateDate;
	// extra
	private String extra_writerName;
	
	public String getForPrintRegDate() {
		return regDate.substring(2, 16);
	}
	
	public String getForPrintUpdateDate() {
		return updateDate.substring(2, 16);
	}
}
