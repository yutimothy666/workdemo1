package com.timothy.base.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yutimothy
 * @Date 2021/3/3 19:13
 * @Version 1.0
 */
@RestController
@RequestMapping("ping")
public class PingConfig {

    @RequestMapping
    public AjaxResult ping(@RequestParam(value = "ping", required = false, defaultValue = "ok") String ping) {
        return AjaxResult.toSuccess(ping);
    }
}
