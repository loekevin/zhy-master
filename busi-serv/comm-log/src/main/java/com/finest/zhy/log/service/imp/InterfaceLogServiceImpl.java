package com.finest.zhy.log.service.imp;

import com.finest.zhy.log.dao.InterfaceDao;
import com.finest.zhy.log.domain.InterfaceLog;
import com.finest.zhy.log.service.InterfaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/6/26 0026.
 */
@Service
@Transactional
public class InterfaceLogServiceImpl implements InterfaceLogService{

    @Autowired
    InterfaceDao interfaceDao;

    @Override
    public void addLog(InterfaceLog log) {
        interfaceDao.save(log);
    }
}
