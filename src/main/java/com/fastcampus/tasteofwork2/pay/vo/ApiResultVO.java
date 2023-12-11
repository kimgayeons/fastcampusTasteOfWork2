package com.fastcampus.tasteofwork2.pay.vo;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiResultVO {
	private String result_type = "success"; //(success/fail)
	private String result_code = "0"; //결과 코드
	private String result_msg = "성공"; //결과 메시지
	private HashMap<String, Object> result_data = new HashMap<String, Object>(); //결과 데이터
}
