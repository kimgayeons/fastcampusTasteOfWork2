package com.fastcampus.tasteofwork2.pay.controller;

import com.fastcampus.tasteofwork2.pay.service.PayService;
import com.fastcampus.tasteofwork2.pay.util.ApiError;
import com.fastcampus.tasteofwork2.pay.vo.ApiResultVO;
import com.fastcampus.tasteofwork2.pay.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/fastcampus")
public class PayRestController {

    @Value("${shop.payment.signKey}")
    private String signKey;

    @Autowired
    private PayService payService;

    @Autowired
    ApiError apiError;

    @PostMapping("/payment")
    public ApiResultVO pay(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("totalOrderPrice") Integer totalOrderPrice) throws UnsupportedEncodingException {
        log.info("totalOrderPrice:"+totalOrderPrice);
        /*임의로 session에 데이터 저장*/
        fakeSession(session, response);
        /*리턴값 초기 세팅*/
        ApiResultVO apiResultVO = new ApiResultVO();
        HashMap<String, Object> result_data = new HashMap<>();

        String orderNumber = "";
        /*STEP2 인증결과*/
        Map<String, String> requestParam = payService.paymentRequestParameter(request);
        
        if (Objects.isNull(requestParam.get("orderNumber"))) {
            return apiError.ApiError(ApiError.ErrorCode.NO_ORDER_NUMBER, apiResultVO, result_data);
        }
        /*DB에 결제 실패 처리하기
        * PAY_TID, PAY_RESULT, PAY_RTN_CD, PAY_DATE
        * UPDATE 하기
        * */
        payService.updatePaymentFail(requestParam);
        orderNumber = requestParam.get("orderNumber");
        
        try {
            /*결제전 체크(paymentCheck)
            * 해당 회원의 상태가 A(정상)인지 확인(회원탈퇴, 정지 등의 상태가 아닌지) : NOT_AVAILABLE(주문 가능한 회원이 아닙니다.)
            * 결제금액의 잔액이 부족하지 않는지 : NO_CHARGE_AMOUNT(잔액이 부족합니다.)
            * 품절된 상품인지 확인 : SOLD_OUT_YN(품절된 상품입니다.)
            * 비공개 상품인지 확인 : SHOW_YN(비공개 상품입니다.)
            * 이미 결제가 실패한 경우 : ALREADY_FAILED(이미 결제가 실패한 주문입니다.)
            * 이미 결제가 성공한 경우 : 결제 완료 페이지로 리턴
            * */
            payService.paymentCheck(request, requestParam);

            /*
            * 실제 결제(payment)
            * result_code값이 0000이 아닌 경우 : PAY_FAIL(결제에 실패하였습니다.)
            * 승인 결과값 받는게 실패한 경우 : NOT_RECOGNIZE(결제 실패했습니다.)
            * */
            payService.payment(request, requestParam);

            /*
            * 결제 완료 후 처리(paymentEnd)
            * 상품 수량 차람
            * 결제 승인 처리
            * */
            payService.paymentEnd(requestParam);

        }catch (Exception e) {
            return apiError.ApiError(ApiError.ErrorCode.ERROR, apiResultVO, result_data);
        }
        return null;
    }

    public void fakeSession(HttpSession session, HttpServletResponse response){
        /*임의로 session에 값을 저장*/
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("kimgy");
        memberVO.setName("김가연");
        memberVO.setTel("01012341234");
        memberVO.setEmail("4402aaa@gmail.com");

        session.setAttribute("userLoginInfo", memberVO);

        MemberVO member = (MemberVO) session.getAttribute("userLoginInfo");
        log.info("member:"+member);
        String memberId = memberVO.getMemberId();
        log.info("member:"+member.getMemberId());

        response.setHeader("SET-COOKIE", "JSESSIONID=" + memberId + "; Path=/; Secure; SameSite=None");
    }
}
