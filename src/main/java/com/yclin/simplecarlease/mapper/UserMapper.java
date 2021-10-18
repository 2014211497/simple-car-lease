package com.yclin.simplecarlease.mapper;

import com.yclin.simplecarlease.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LinYuchang
 */
@Mapper
public interface UserMapper {

    User selectById(String id);
}
