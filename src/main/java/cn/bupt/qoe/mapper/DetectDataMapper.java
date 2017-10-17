package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.model.MonitorData;
import cn.bupt.qoe.model.StaticData;
import cn.bupt.qoe.model.TestData;
import cn.bupt.qoe.pojo.DetectDataWithMos;
import cn.bupt.qoe.pojo.HotMapData;
import cn.bupt.qoe.rest.WebResult;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {

	@Select(value = "select mos_sub,mos_obj,latitude,longitude from qoe_mos")
	List<HotMapData> getAllHotMapData();

	@Select(value = "select * from qoe_mos order by detect_time desc")
	List<DetectData> getAllDetectData();

	List<DetectData> selectAllByPage(PageBounds pageBounds);

	@Select(value = "select LAST_INSERT_ID()")
	Long getLastInsertId();

	@Insert(value = "insert into qoe_id values (#{result_id,jdbcType=BIGINT}, #{mos,jdbcType=DOUBLE})")
	int insertMos(@Param(value = "result_id") Long result_id, @Param(value = "mos") Double mos);

	@Insert(value = "insert into newapp_metadata_static values (#{id,jdbcType=BIGINT}, "
			+ "												#{mac,jdbcType=VARCHAR},"
			+ "                                           	#{cpu,jdbcType=DOUBLE}, "
			+ "                                           	#{screenPixels,jdbcType=INTEGER}, "
			+ "                                           	#{mimeType,jdbcType=VARCHAR}, "
			+ "                                           	#{height,jdbcType=INTEGER}, "
			+ "                                          	#{width,jdbcType=INTEGER}, "
			+ "                                           	#{videoStreamBitRate,jdbcType=DOUBLE}, "
			+ "                                           	#{videoLength,jdbcType=INTEGER}, "
			+ "                                           	#{initTime,jdbcType=BIGINT}, "
			+ "												#{videoBufferStart,jdbcType=BIGINT}, "
			+ "												#{stopNum,jdbcType=INTEGER}, "
			+ "												#{stopTimeAvg,jdbcType=BIGINT}, "
			+ "												#{mosOverall,jdbcType=DOUBLE}, "
			+ "                                           	#{mark,jdbcType=VARCHAR})")
	int insertStaticData(StaticData data);

	@Insert(value = "insert into newapp_metadata_monitor values (#{id,jdbcType=BIGINT}, "
			+ "												#{monitorTime,jdbcType=BIGINT},"
			+ "												#{monitorTimeStamp,jdbcType=BIGINT},"
			+ "                                           	#{sendSpeed,jdbcType=INTEGER}, "
			+ "                                           	#{netSpeed,jdbcType=INTEGER}, "
			+ "                                           	#{bufferPercentage,jdbcType=INTEGER}, "
			+ "                                           	#{memoryConsumption,jdbcType=DOUBLE}, "
			+ "                                           	#{currentCpu,jdbcType=DOUBLE}, "
			+ "                                           	#{mark,jdbcType=VARCHAR})")
	int insertMonitorData(MonitorData data);

	@Insert(value = "insert into newapp_metadata_test values (#{id,jdbcType=BIGINT}, "
			+ "												#{mos_sub,jdbcType=DOUBLE},"
			+ "												#{mos_obj,jdbcType=DOUBLE},"
			+ "                                           	#{testTime,jdbcType=BIGINT}, "
			+ "                                           	#{testTimeStamp,jdbcType=BIGINT}, "
			+ "                                           	#{messageDelay,jdbcType=DOUBLE}, "
			+ "                                           	#{longitude,jdbcType=DOUBLE}, "
			+ "                                           	#{latitude,jdbcType=DOUBLE}, "
			+ "                                           	#{mark,jdbcType=VARCHAR})")

	int insertTestData(TestData data);

	// 为了展示热图，获取test表中的元数据，按照testTimeStamp降序排列
	@Results({ @Result(property = "mos_sub", column = "mos_sub"), @Result(property = "mos_obj", column = "mos_obj")})
	@Select(value = "SELECT " + "id,mos_sub,mos_obj,testTime,testTimeStamp,messageDelay,longitude,latitude,mark"
			+ " FROM newapp_metadata_test order by testTimeStamp desc")
	List<TestData> getHeatMapMetaData();

	@Select({ "<script>", "select * from qoe_mos where 1=1", "<when test='result_id!=null'>",
			"and result_id=#{result_id}", "</when>", "</script>" })
	List<DetectData> getTest(Map map);
}
