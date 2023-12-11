package com.fastcampus.tasteofwork2.pay.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberVO {
    private String memberId;/*사용자 아이디*/
    private String name;/*이름*/
    private String tel;/*핸드폰 번호*/
    private String email;/*이메일*/
    private String chargeAmount; /*충전 금액*/
}
