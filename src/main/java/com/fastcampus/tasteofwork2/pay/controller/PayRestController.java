package com.fastcampus.tasteofwork2.pay.controller;

import com.fastcampus.tasteofwork2.pay.vo.AjaxResultVO;
import com.fastcampus.tasteofwork2.pay.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/api/fastcampus")
public class PayRestController {

    @PostMapping("/payment")
    public AjaxResultVO pay(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("totalOrderPrice") Integer totalOrderPrice) {
        log.info("totalOrderPrice:"+totalOrderPrice);

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


        return null;
    }
}
