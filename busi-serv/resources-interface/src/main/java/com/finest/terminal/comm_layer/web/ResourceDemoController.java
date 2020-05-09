package com.finest.terminal.comm_layer.web;

import com.finest.terminal.comm_layer.service.ResourceDemoService;
import com.finest.terminal.domain.User;
import com.finest.zhy.comm.dto.ResultJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by kezy on 2019/11/27.
 */
@Slf4j
@RestController
@RequestMapping(value ="/resource")
@Api(value = "/SysUserController", description = "用户信息管理接口",tags = "用户信息管理接口")
public class ResourceDemoController {

    @Autowired
    private ResourceDemoService resourceDemoService;

    /**
     * 查询用户列表数据
     * @paramd
     * **/
    @RequestMapping("/userList")
    @ApiOperation(value = "根据用户名查询用户列表数据", notes = "根据用户名查询用户列表数据")
    @ApiImplicitParams({
    })
    public List<User> getUserList(HttpServletRequest request){
        String userId = request.getParameter("user");
        List<User> list = resourceDemoService.getUserList();
        if(list.size()>0){
            return list;
        }
        User user = new User();
        user.setId(0);
        user.setPassword(userId);
        user.setUsername(userId);
        user.setAvatar("当前登录人是："+userId);
        list.add(user);
        return list;
    }


    /**
     * 根据Id查询用户列表数据
     * @param keyWord
     * **/
    @RequestMapping("/userList/{keyWord}")
    @ApiOperation(value = "根据用户名查询用户列表数据", notes = "根据用户名查询用户列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "keyWord", paramType = "query", required = true, dataType = "String")
    })
    public List<User> getUserList(@PathVariable("keyWord") String keyWord){
        return resourceDemoService.getUserInfo(keyWord);
    }


    /**
     * 删除用户数据
     * @param id
     * **/
    @RequestMapping("/removeUser/{id}")
    @ApiOperation(value = "删除用户数据", notes = "根据用户名查询用户列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "String")
    })
    public ResultJsonUtil removeUser(@PathVariable("id") String id){
        return resourceDemoService.removeUser(id);
    }

    @RequestMapping("/testFeignServer/{keyWord}")
    @ApiOperation(value = "测试Feign调用", notes = "请输入文本内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "keyWord", paramType = "query", required = true, dataType = "String")
    })
    public String testFeignServer(@PathVariable("keyWord") String keyWord){
        return resourceDemoService.getUserInfo(keyWord).get(0).toString();
    }
}
