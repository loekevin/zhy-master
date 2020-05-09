package com.finest.zhy.log.dao;

import com.finest.zhy.log.domain.InterfaceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2018/6/26 0026.
 */
public interface InterfaceDao extends JpaSpecificationExecutor<InterfaceLog>,
        JpaRepository<InterfaceLog, String>{

}
