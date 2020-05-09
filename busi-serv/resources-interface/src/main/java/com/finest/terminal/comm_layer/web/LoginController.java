package com.finest.terminal.comm_layer.web;

import com.alibaba.fastjson.JSONArray;
import com.finest.zhy.comm.constant.CommonConstants;
import com.finest.zhy.comm.dto.ResultJsonUtil;
import com.finest.zhy.comm.util.RedisClientUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by kezy on 2020/5/1.
 */
@Slf4j
@RestController
@RequestMapping(value ="/")
public class LoginController {

    @Autowired
    private RedisClientUtil redisUtil;

    /**
     * 用户登录接口，只需要用户名和密码相同即可
     *
     * @param username
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "输入用户和密码进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username", paramType = "query", required = true, dataType = "String")
    })
    public String login(String username,String password) {
        log.info("用户登录：username:" + username);
        String result = "";
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            JSONArray jsonArray = new JSONArray();
            String token = UUID.randomUUID().toString().replaceAll("-","");
            redisUtil.set(CommonConstants.ACCESS_TOKEN +"_"+token,username,Long.valueOf(300));
            String json ="{\"token\":"+token+"}";
            jsonArray.add(json);
            result = ResultJsonUtil.build(200,"登录成功",jsonArray);
        } else {
            result = ResultJsonUtil.build(500,"用户名或密码错误");
        }
        return result;
    }
}
