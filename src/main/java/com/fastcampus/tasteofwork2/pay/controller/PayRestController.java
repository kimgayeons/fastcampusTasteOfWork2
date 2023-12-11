package com.fastcampus.tasteofwork2.pay.controller;

import com.fastcampus.tasteofwork2.pay.vo.AjaxResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/fastcampus")
public class PayRestController {

    @PostMapping("/payment")
    public AjaxResultVO pay(@RequestParam("totalOrderPrice") Integer totalOrderPrice) {
        log.info("totalOrderPrice:"+totalOrderPrice);
        return null;
    }
}
