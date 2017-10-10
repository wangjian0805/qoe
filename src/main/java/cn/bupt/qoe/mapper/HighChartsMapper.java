package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.BuildingModel;
import cn.bupt.qoe.model.HotMapModel;
import cn.bupt.qoe.model.MosData;
import cn.bupt.qoe.model.OsCompareModel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Ambitous on 2017/9/18.
 */
public interface HighChartsMapper {

    @Results({
            @Result(property = "sub", column = "mos_sub"),
            @Result(property = "obj", column = "mos_obj")
    })
    @Select("select mos_sub,mos_obj from newapp_metadata_test")
    List<OsCompareModel> getOsCompareData();

    @Select("select count(*) from newapp_metadata_test group by mos_sub order by mos_sub")
    List<Double> getMosData();

    @Select("select count(*) from newapp_metadata_test")
    Integer getTestNum();

    @Select("select count(distinct mac) from newapp_metadata_static")
    Integer getMacNum();

    @Results({
            @Result(property = "lng", column = "longitude"),
            @Result(property = "lat", column = "latitude"),
            @Result(property = "count", column = "mos_sub")
    })
    @Select("select mark,longitude,latitude,mos_sub from newapp_metadata_test")
    List<HotMapModel> getHotMapData();

    @Select("select * from geo_building_map")
    List<BuildingModel> getBuildingMes();

}
