package com.example.demo.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data1;
	
	//생성자
	private ResultData() {
		
	}
	
	//ResultData 객체를 반환
		public static ResultData from(String resultCode, String msg) {
			return from(resultCode, msg, null);
		}
	
	//ResultData 객체를 반환
	public static ResultData from(String resultCode, String msg, Object data1) {
		ResultData rd = new ResultData();
		
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}
