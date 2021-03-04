package com.timothy.base.comment;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author yutimothy
 * @Date 2021/3/3 19:14
 * @Version 1.0
 */
public class AjaxResult extends HashMap<String, Object> {
    public AjaxResult(String code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }

    public static AjaxResult toSuccess(Object data) {
        return new AjaxResult("200", "ok", data);
    }
}
