-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE wilysis;
-- 使用数据库
use wilysis;
-- set explicit_defaults_for_timestamp = 1;
CREATE TABLE detect_by_wifi(
  `result_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '测试ID',
  `cpu` INT NOT NULL COMMENT '测试cpu 单位:kHz',
  `memory_consumption` INT NOT NULL COMMENT '库存数量 单位：%',
  `screen_pixels` INT NOT NULL COMMENT '屏幕像素密度 单位：ppi',
  `video_stream_bitRate` INT NOT NULL COMMENT '视频流平均比特率 单位：kbps',
  `throughput` INT NOT NULL COMMENT '吞吐量 单位：kbps',
  `init_buffer` INT NOT NULL COMMENT '初始化设置的缓冲区大小 单位：s',
  `reserved_buffer` INT NOT NULL COMMENT '预留的缓冲区大小 单位：s',
  `video_length` INT NOT NULL COMMENT '视频长度 单位：s',
  `message_delay` INT NOT NULL COMMENT '消息时延 单位：s',
  `latitude` DOUBLE(11,6) NOT NULL COMMENT '探测纬度',
  `longitude` DOUBLE(11,6) NOT NULL COMMENT '探测经度',
  `detect_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '探测时间',
  PRIMARY KEY (result_id),
  key idx_latitude_longitude(latitude,longitude),
  key idx_detect_time(detect_time)
)ENGINE=INNODB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='KPI的Wifi探测数据集';

-- Mos值表
CREATE TABLE qoe_id(
  `result_id` BIGINT NOT NUll COMMENT '测试ID',
  `mos` DOUBLE NOT NULL COMMENT '计算Mos值',
  PRIMARY KEY (result_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='Qoe mos值';

-- 初始化数据
INSERT into detect_by_wifi(cpu,memory_consumption,screen_pixels,video_stream_bitRate,
  throughput,init_buffer,reserved_buffer,video_length,message_delay,latitude,longitude)
VALUES
(2100,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2200,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2300,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2400,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2500,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2600,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2700,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2800,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(2900,50,330,800,600,5,3,1800,2,39.123456,119.654321),
(3000,50,330,800,600,5,3,1800,2,39.123456,119.654321);

-- SHOW CREATE TABLE detect_by_wifi;#显示表的创建信息