package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.pojo.DetectDataWithMos;
import cn.bupt.qoe.pojo.HotMapData;
import cn.bupt.qoe.rest.WebResult;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {


	@Select(value = "select m.mos,d.latitude,d.longitude from qoe_id as m, detect_by_wifi as d" +
			"		where m.result_id=d.result_id")
	List<HotMapData> getAllHotMapData();

	@Select(value = "select d.*, m.mos from detect_by_wifi as d, qoe_id as m where m.result_id=d.result_id order by detect_time desc")
	List<DetectDataWithMos> getAllDetectData();

	List<DetectData> selectAllByPage(PageBounds pageBounds);

	@Select(value = "select LAST_INSERT_ID()")
	Long getLastInsertId();

	@Insert(value = "insert into qoe_id values (#{result_id,jdbcType=BIGINT}, #{mos,jdbcType=DOUBLE})")
	int insertMos(@Param(value = "result_id") Long result_id, @Param(value = "mos") Double mos);

	@Insert(value = "insert into detect_by_wifi values (#{resultId,jdbcType=BIGINT}, " +
			"                                           #{cpu,jdbcType=INTEGER}, " +
			"                                           #{memoryConsumption,jdbcType=INTEGER}, " +
			"                                           #{screenPixels,jdbcType=INTEGER}, " +
			"                                           #{videoStreamBitrate,jdbcType=INTEGER}, " +
			"                                           #{throughput,jdbcType=INTEGER}, " +
			"                                           #{initBuffer,jdbcType=INTEGER}, " +
			"                                           #{reservedBuffer,jdbcType=INTEGER}, " +
			"                                           #{videoLength,jdbcType=INTEGER}, " +
			"                                           #{messageDelay,jdbcType=INTEGER}, " +
			"                                           #{latitude,jdbcType=DOUBLE}, " +
			"                                           #{longitude,jdbcType=DOUBLE}, " +
			"                                           #{detectTime,jdbcType=TIMESTAMP})")
	int insertDetectData(DetectData data);
}
