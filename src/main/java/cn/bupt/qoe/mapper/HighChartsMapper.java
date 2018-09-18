package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.*;
import cn.bupt.qoe.util.HeatMapProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Ambitous on 2017/9/18.
 */
public interface HighChartsMapper {

    @Results({
            @Result(property = "sub", column = "mos_sub"),
            @Result(property = "obj", column = "mos_obj")
    })
    @Select("select mos_sub,mos_obj from metadata_test limit 100")
    List<OsCompareModel> getOsCompareData();

    @Select("select count(*) from metadata_test group by mos_sub order by mos_sub")
    List<Double> getMosData();

    @Select("select count(ROUND(mos_obj)) from metadata_test group by ROUND(mos_obj) order by ROUND(mos_obj)")
    List<Double> getObjMosData();

    @Select("select count(*) from metadata_test")
    Integer getTestNum();

    @Select("select count(distinct mac) from metadata_static")
    Integer getMacNum();

    @Select("select count(*) from metadata_static")
    Integer getPlayNum();

    @Select("select count(*) from metadata_monitor")
    Integer getMonitorNum();

    @Results({
            @Result(property = "lng", column = "longitude"),
            @Result(property = "lat", column = "latitude"),
            @Result(property = "count", column = "mos_sub")
    })
    @Select("select mark,longitude,latitude,mos_sub from metadata_test")
    List<HotMapModel> getHotMapData();

    @Select("select * from geo_building_map")
    List<BuildingModel> getBuildingMes();

    @Results({
            @Result(property = "lng", column = "longitude"),
            @Result(property = "lat", column = "latitude"),
            @Result(property = "count", column = "mos_sub")
    })
    @SelectProvider(type=HeatMapProvider.class,method="selectMapByOperater")
    List<HotMapModel> getHotMapDataByOptions(HeatMapOperator h);
}
