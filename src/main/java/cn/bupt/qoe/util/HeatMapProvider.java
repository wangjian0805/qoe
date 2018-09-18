package cn.bupt.qoe.util;

import org.apache.ibatis.jdbc.SQL;

import cn.bupt.qoe.model.HeatMapOperator;

public class HeatMapProvider {

	private final String TBL_TEST = "metadata_test";
	public String selectMapByOperater(HeatMapOperator h) {
        SQL sql = new SQL().SELECT("mark,longitude,latitude,mos_sub").FROM(TBL_TEST);
        Long timeStart = h.getTimeStart();
        Long timeStop = h.getTimeStop();
        sql.WHERE("mos_sub >= #{mosMin}");
        sql.WHERE("mos_sub <= #{mosMax}");
        if (timeStart != null) {
            sql.WHERE("testTimeStamp >= #{timeStart}");
        }
        if (timeStop != null) {
            sql.WHERE("testTimeStamp <= #{timeStop}");
        }
        return sql.toString();
    }
	
}
