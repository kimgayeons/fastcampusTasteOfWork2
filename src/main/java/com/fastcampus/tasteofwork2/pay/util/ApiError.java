package com.fastcampus.tasteofwork2.pay.util;

import com.fastcampus.tasteofwork2.pay.vo.ApiResultVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ApiError {
    public static enum ErrorCode{
        ERROR("E00","오류가 발생했습니다."),
        MY_BATIS_ERROR( "E02", "데이터 작업 도중 오류가 발생했습니다. (TYPE : M)"),
        NEED_PARAM("E03","필수 파라미터가 존재하지 않습니다."),
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
