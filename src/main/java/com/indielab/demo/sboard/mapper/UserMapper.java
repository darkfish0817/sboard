package com.indielab.demo.sboard.mapper;

import com.indielab.demo.sboard.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers(@Param("text") String text);
}
