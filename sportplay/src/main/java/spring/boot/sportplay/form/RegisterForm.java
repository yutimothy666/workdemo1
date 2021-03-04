package spring.boot.sportplay.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import spring.boot.sportplay.enums.SexEnum;

import java.util.Date;
import java.time.LocalDateTime;

/**
 * @Author yutimothy
 * @Date 2021/3/4 21:21
 * @Version 1.0
 */
@Data
public class RegisterForm {
    private String username;
    private String password;
    private String passwordTwo;
    private String email;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private String birthday;
    private String code;
}
