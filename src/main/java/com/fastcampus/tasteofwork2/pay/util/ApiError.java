package com.fastcampus.tasteofwork2.pay.util;

import com.fastcampus.tasteofwork2.pay.vo.ApiResultVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ApiError {
    public static enum ErrorCode{
        ERROR("E00","결제 오류가 발생했습니다."),
        MY_BATIS_ERROR( "E01", "데이터 작업 도중 오류가 발생했습니다. (TYPE : M)"),
        NEED_PARAM("E02","필수 파라미터가 존재하지 않습니다."),
        NO_ORDER_NUMBER("E03","주문번호값이 없습니다."),
        NOT_AVAILABLE("E04","주문 가능한 회원이 아닙니다."),
        SOLD_OUT_YN("E05","품절된 상품입니다."),
        SHOW_YN("E06","비공개 상품입니다."),
        ALEADY_ORDER_FAIL("E07","이미 결제 실패된 상품입니다."),
        ALEADY_ORDER_SUCCESS("E08","이미 결제 완료된 상품입니다."),
        NO_CHARGE_AMOUNT("E09","잔액이 부족합니다."),
        PAY_FAIL("E10","결제 실패했습니다."),
        NOT_RECOGNIZE("E11","결제 실패했습니다."),
        ;

        private String code = "";
        private String msg = "";

        ErrorCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public String getCode() { return code; }
        public String getMsg() { return msg; }
    }

    public static ApiResultVO ApiError(ErrorCode result_code, ApiResultVO result, HashMap<String, Object> result_data) {
        result.setResult_type("error");
        result.setResult_code(result_code.getCode());
        result.setResult_msg(result_code.getMsg());
        result.setResult_data(result_data);

        return result;
    }
}
