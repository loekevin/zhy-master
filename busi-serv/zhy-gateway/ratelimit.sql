CREATE TABLE sct.rate_limit (
    rateLimitId VARCHAR(64) NOT NULL,
    limitPath VARCHAR(500) NOT NULL COMMENT '限流路径，支持通配符，示例 /user/**',
    permitsPerSecond INT(11) DEFAULT '1' COMMENT '每秒限流频率',
    permitsTimeOut INT(11) DEFAULT '1' COMMENT '限流等待超时时间，单位s',
    orderNo INT(11) DEFAULT NULL COMMENT '排序',
    maxThread INT(11) DEFAULT NULL COMMENT '最大线程数',
    CreateTime DATE DEFAULT NULL COMMENT '创建时间',
    updateTime DATE DEFAULT NULL COMMENT '修改时间',
    creator VARCHAR(32) DEFAULT NULL COMMENT '创建人',
    mediator VARCHAR(32) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (rateLimitId)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='服务限流表'

insert into rate_limit VALUES(uuid(),"/resources-interface/resource/**",1,3,1,10,CURRENT_DATE,CURRENT_DATE,'admin','admin');