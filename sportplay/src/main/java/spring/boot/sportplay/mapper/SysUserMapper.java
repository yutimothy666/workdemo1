package spring.boot.sportplay.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import spring.boot.sportplay.bean.SysUserEntity;

/**
 * @Author yutimothy
 * @Date 2021/3/3 19:52
 * @Version 1.0
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    public default SysUserEntity selectUserByUsername(String userName) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return selectOne(queryWrapper);
    }
}
