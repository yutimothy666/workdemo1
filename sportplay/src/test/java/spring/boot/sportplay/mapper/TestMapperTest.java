package spring.boot.sportplay.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.sportplay.bean.SysUserEntity;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yutimothy
 * @Date 2021/3/2 23:37
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapperTest {
    @Resource
    SysUserMapper sysUserMapper;

    @Test
    public void test1() {
        IntStream.range(0, 100).forEach(k -> {
            SysUserEntity sysUserEntity = new SysUserEntity().setDeptId(1L).
                    setUserName("" + k + 100).setNickName("" + k + 22);
            sysUserMapper.insert(sysUserEntity);
        });
    }

    @Test
    public void query() {
        SysUserEntity sysUserEntity = new SysUserEntity().setUserId(101L);
        List<SysUserEntity> sysUserEntities = sysUserMapper.selectList(new QueryWrapper<>(sysUserEntity));
        sysUserEntities.forEach(System.out::println);
    }
}