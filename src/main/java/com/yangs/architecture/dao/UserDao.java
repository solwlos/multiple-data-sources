package com.yangs.architecture.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangs.architecture.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sol
 * @date 2023/10/23 17:31
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
