package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.DetectData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {

    @Select(value = "select * from detect_by_wifi order by detect_time desc")
    List<DetectData> getAllDetectData();

}
