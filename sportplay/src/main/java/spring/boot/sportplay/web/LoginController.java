package spring.boot.sportplay.web;

import com.timothy.base.comment.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.sportplay.bean.SysUserEntity;
import spring.boot.sportplay.enums.SexEnum;
import spring.boot.sportplay.form.RegisterForm;
import spring.boot.sportplay.mapper.SysUserMapper;
import spring.boot.sportplay.util.JwtUtils;
import spring.boot.sportplay.util.RedisService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @Author yutimothy
 * @Date 2021/3/4 19:31
 * @Version 1.0
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    RedisService redisService;
    @Resource
    SysUserMapper mapper;

    @PostMapping("getCode")
    public AjaxResult getCodeByPhone(String info, String type) {
        if (Objects.nonNull(info)) {
            Object o = redisService.get(info);
            if (Objects.isNull(o)) {
                String code = Stream.generate(() -> (int) (1 + Math.random() * (10 - 1 + 1))).limit(6).map(String::valueOf).collect(joining());
                redisService.set(info.trim(), code, 6);
                return AjaxResult.toSuccess(code);
            }
        }
        return AjaxResult.toSuccess("error");
    }

    @PostMapping("registerByPhone")
    public AjaxResult loginByPhone(String phone, String code) {
        Object o = redisService.get(phone.trim());
        if (Objects.equals(String.valueOf(o), code)) {
            return AjaxResult.toSuccess("ok");
        }
        return AjaxResult.toSuccess("error");
    }

    @PostMapping("registerByEmail")
    public AjaxResult registerByEmail(RegisterForm form) {
        System.out.println(form);
        SysUserEntity sysUserEntity = new SysUserEntity().setUserName(form.getUsername()).setPassword(form.getPassword()).setNickName(form.getUsername());
        mapper.insert(sysUserEntity);
        String user = JwtUtils.sign("user", String.valueOf(sysUserEntity.getUserId()));
        return AjaxResult.toSuccess(user);
    }

    @PostMapping("getToken")
    public AjaxResult getToken(String username, String password) {
        return AjaxResult.toSuccess("ok");
    }

    @PostMapping("initInfo")
    public AjaxResult initInfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sexEnum", SexEnum.getMap());
        return AjaxResult.toSuccess(map);
    }

    @PostMapping
    public AjaxResult login(String username, String password) {
        SysUserEntity sysUserEntity = mapper.selectUserByUsername(username);
        if (Objects.equals(sysUserEntity.getPassword(), password)) {
            return AjaxResult.toSuccess(JwtUtils.sign("user", String.valueOf(sysUserEntity.getUserId())));
        } else {
            return AjaxResult.toSuccess("error");
        }
    }
}
