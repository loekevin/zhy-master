package com.finest.terminal.comm_layer.service.impl;

import com.finest.terminal.comm_layer.dao.UserDao;
import com.finest.terminal.comm_layer.service.ResourceDemoService;
import com.finest.terminal.domain.User;
import com.finest.zhy.comm.dto.ResultJsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kezy on 2019/11/27.
 */
@Service
public class ResourceDemoServiceImpl implements ResourceDemoService{

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(cacheNames = "user")
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    @Cacheable(cacheNames = "user--id")
    public List<User> getUserInfo(String keyWord) {
        return userDao.getUserInfo(keyWord);
    }

    @Override
    @CachePut(cacheNames = "user--id")
    public ResultJsonUtil removeUser(String keyWord) {
        ResultJsonUtil response = null;
        if(StringUtils.isNotBlank(keyWord)){
            boolean check= userDao.removeUser(keyWord);
            if(check){
                response = new ResultJsonUtil(200,"删除成功！");
            }else{
                response = new ResultJsonUtil(500,"删除失败！");
            }
        }else{
            response = new ResultJsonUtil(500,"请输入你要删除的主键,否则不允许删除！");
        }
        return response;

    }
}
