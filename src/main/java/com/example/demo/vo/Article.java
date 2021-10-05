package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String title;
	private String body;
	private String regDate;
	private String updateDate;
	// 객체 Article 출력시 public을 써야 하지만 스프링에서는 private 권장
	// 그래서 private 변수 위에 @Getter 를 사용하면 출력 가능
	// 하지만 lombok을 class 위에 @Data로 사용하면 간편하게 사용가능
	// @AllArgsConstructor => 생성자에 인자를 받을 수 있음
	// @NoArgsConstructor => 생성자가 없는 클래스도 받을 수 있음
}