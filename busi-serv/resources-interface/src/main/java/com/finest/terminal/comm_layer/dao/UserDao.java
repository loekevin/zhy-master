package com.finest.terminal.comm_layer.dao;

import com.finest.terminal.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kezy on 2019/11/27.
 */
@Repository
public class UserDao {

    @Resource(name = "firstJdbcTemplate")
    private JdbcTemplate firstJdbcTemplate;

    public List<User> getUserList(){
        String sql="select * from sys_user where 1=1 ";
        List<User> userList = firstJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return userList;
    }

    public List<User> getUserInfo(String keyWord){
        String sql="select * from sys_user where 1=1 ";
        if(StringUtils.isNotBlank(keyWord)){
            sql+=" and (id='" + keyWord + "' or  username ='" + keyWord + "')";
        }
        List<User> userList = firstJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return userList;
    }

    public boolean  removeUser(String keyWord){
        boolean check = false;
        String sql="delete from sys_user where 1=1 ";
        if(StringUtils.isNotBlank(keyWord)){
            sql+=" and id='" + keyWord + "'";
        }
        int count = firstJdbcTemplate.update(sql);
        if(count>0){
            check= true;
        }
        return check;
    }
}
