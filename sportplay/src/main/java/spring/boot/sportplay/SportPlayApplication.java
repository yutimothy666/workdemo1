package spring.boot.sportplay;

import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author yutimothy
 * @Date 2021/3/2 19:23
 * @Version 1.0
 */
@Data
@MapperScan("spring.boot.sportplay.mapper")
@SpringBootApplication(scanBasePackages = {"com.timothy.base.comment", "spring.boot.sportplay"})
public class SportPlayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportPlayApplication.class, args);
    }
}
