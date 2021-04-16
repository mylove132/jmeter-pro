package com.lzh.jmeter.system.mapper;
import com.lzh.jmeter.system.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    User findByUsername (@Param("username") String username);

    List<User> userList ();

    User findByToken (@Param("token") String token);
}
