package com.atguigu.springmvc.mapper;

import com.atguigu.springmvc.entities.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 持久化层接口：用户模块
 */
@Mapper
public interface SysUserMapper {
    int deleteById(Long id);

    SysUser getById(Long id);

    // 多参数必须加 @Param，否则 XML 里只能拿到 arg0/param1/param2
    int updateById(@Param("id") Long id, @Param("user") SysUser user);

    int insert(SysUser sysUser);

    default SysUser save(SysUser sysUser){
        insert(sysUser);
        return sysUser;
    }

    List<SysUser> findUserList(@Param("username") String username,
                              @Param("phone") String phone,
                              @Param("status") Integer status);

    boolean login(@Param("username") String username, @Param("password") String password);
}
