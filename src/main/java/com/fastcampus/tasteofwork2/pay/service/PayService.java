package com.fastcampus.tasteofwork2.pay.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PayService {

    public Map<String, String> paymentRequestParameter(HttpServletRequest request) throws UnsupportedEncodingException {
        String resultCode = request.getParameter("resultCode");//	결과코드 "0000":성공, 이외 실패
        String resultMsg = request.getParameter("resultMsg");//결과메시지
        String mid = request.getParameter("mid");//상점아이디
        //String mid =  null;//결제 오류 테스트
        String orderNumber = request.getParameter("orderNumber");//주문번호 결제요청 시 세팅한 주문번호
        String authToken = request.getParameter("authToken"); //승인요청 검증 토큰
        String authUrl = request.getParameter("authUrl"); //승인요청 Url
        String netCancelUrl = request.getParameter("netCancelUrl"); //망취소요청 Url
        String charset = request.getParameter("charset"); //망취소요청 Url
        String merchantData = request.getParameter("merchantData"); //가맹점 임의 데이터

        Map<String, String> params = new HashMap<>();
        params.put("resultCode", resultCode);
        params.put("resultMsg", resultMsg);
        params.put("mid", mid);
        params.put("orderNumber", orderNumber);
        params.put("authToken", authToken);
        params.put("authUrl", authUrl);
        params.put("netCancelUrl", netCancelUrl);
        params.put("charset", charset);
        params.put("merchantData", merchantData);

        log.info("resultCode = " + resultCode);
        log.info("resultMsg = " + resultMsg);
        log.info("mid = " + mid);
        log.info("orderNumber = " + orderNumber);
        log.info("authToken = " + authToken);
        log.info("authUrl = " + authUrl);
        log.info("netCancelUrl = " + netCancelUrl);
        log.info("charset = " + charset);
        log.info("merchantData = " + merchantData);

        return params;
    }

    public void updatePaymentFail(Map<String, String> requestParam) {
        log.info("updatePaymentFail");
    }
    public void paymentCheck(HttpServletRequest request, Map<String, String> requestParam) {
        log.info("paymentCheck");
    }
    public void payment(HttpServletRequest request, Map<String, String> requestParam) {
        log.info("payment");
    }
    public void paymentEnd(Map<String, String> requestParam) {
        log.info("payment");
    }
}
