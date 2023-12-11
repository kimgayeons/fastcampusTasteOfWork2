package com.fastcampus.tasteofwork2.pay.vo;

import lombok.Data;

import java.util.List;

@Data
public class AjaxResultVO<T> {
    private String success;
    private String code;
    private String message;
    private String value;
    private T contents;
    private List<T> list;
}
