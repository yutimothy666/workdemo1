package spring.boot.sportplay.enums;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timothy.base.comment.BaseEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yutimothy
 * @Date 2021/3/4 21:18
 * @Version 1.0
 */
public enum SexEnum implements BaseEnum {
    MALE {
        @Override
        public String getName() {
            return "男";
        }
    },
    FEMALE {
        @Override
        public String getName() {
            return "女";
        }
    };

    @Override
    public String getValue() {
        return String.valueOf(this.ordinal());
    }

    public static Map<String, String> getMap() {
        return Stream.of(SexEnum.values()).collect(Collectors.toMap(SexEnum::getValue, SexEnum::getName));
    }

    public static SexEnum getEnum(String value) {
        return Arrays.stream(SexEnum.values()).filter(k -> k.getValue().equals(value)).findFirst().orElse(SexEnum.MALE);
    }

    public abstract String getName();
}
