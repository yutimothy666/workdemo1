package spring.boot.sportplay.config;

import com.timothy.base.comment.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yutimothy
 * @Date 2021/3/4 22:38
 * @Version 1.0
 */
@Configuration
public class SportPlayConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
