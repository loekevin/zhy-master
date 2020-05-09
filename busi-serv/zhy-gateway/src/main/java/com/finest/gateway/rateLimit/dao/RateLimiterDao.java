package com.finest.gateway.rateLimit.dao;

import com.finest.gateway.dto.RateLimitDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kezy on 2020/1/13.
 */
@Repository
public class RateLimiterDao  {

    @Resource(name = "commJdbcTemplate")
    private JdbcTemplate firstJdbcTemplate;


    public List<RateLimitDto> findListForPriority(){
        String sql = "select * from rate_limit where 1=1 ";
        List<RateLimitDto> rateLimitList = firstJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RateLimitDto.class));
        return rateLimitList;
    }
}
