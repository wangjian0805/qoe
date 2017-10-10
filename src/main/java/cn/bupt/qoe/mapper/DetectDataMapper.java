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
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {

	List<DetectData> getAllDetectData();

	DetectData getOneDetectDataByMark(String mark);

	List<DetectData> selectAllByPage(PageBounds pageBounds);

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

}
