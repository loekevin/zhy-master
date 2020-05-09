package com.finest.terminal.comm_layer.service;

import com.finest.terminal.domain.User;
import com.finest.zhy.comm.dto.ResultJsonUtil;

import java.util.List;

/**
 * Created by kezy on 2019/11/27.
 */
public interface ResourceDemoService {

    List<User> getUserList();

    List<User> getUserInfo(String keyWord);

    ResultJsonUtil removeUser(String id);
}
