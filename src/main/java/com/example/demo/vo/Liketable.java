package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liketable {
	private int id;
	private int memberId;
	private int relId;
	private int point;
	private String regDate;
	private String updateDate;
}