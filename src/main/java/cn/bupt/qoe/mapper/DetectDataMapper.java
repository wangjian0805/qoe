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
import java.util.Map;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {


	@Select(value = "select mos_sub,mos_obj,latitude,longitude from qoe_mos")
	List<HotMapData> getAllHotMapData();

	@Select(value = "select * from qoe_mos order by detect_time desc")
	List<DetectDataWithMos> getAllDetectData();

	List<DetectData> selectAllByPage(PageBounds pageBounds);

	@Select(value = "select LAST_INSERT_ID()")
	Long getLastInsertId();

	@Insert(value = "insert into qoe_id values (#{result_id,jdbcType=BIGINT}, #{mos,jdbcType=DOUBLE})")
	int insertMos(@Param(value = "result_id") Long result_id, @Param(value = "mos") Double mos);

	@Insert(value = "insert into qoe_mos values (#{resultId,jdbcType=BIGINT}, " +
			"                                           #{cpu,jdbcType=INTEGER}, " +
			"                                           #{memoryConsumption,jdbcType=INTEGER}, " +
			"                                           #{screenPixels,jdbcType=INTEGER}, " +
			"                                           #{videoStreamBitrate,jdbcType=INTEGER}, " +
			"                                           #{throughput,jdbcType=INTEGER}, " +
			"                                           #{initBuffer,jdbcType=DOUBLE}, " +
			"                                           #{reservedBuffer,jdbcType=DOUBLE}, " +
			"                                           #{videoLength,jdbcType=INTEGER}, " +
			"                                           #{messageDelay,jdbcType=INTEGER}, " +
			"                                           #{latitude,jdbcType=DOUBLE}, " +
			"                                           #{longitude,jdbcType=DOUBLE}, " +
			"											#{mosObj,jdbcType=DOUBLE}, " +
			"											#{mosSub,jdbcType=DOUBLE}," +
			"                                           #{detectTime,jdbcType=TIMESTAMP}) ")
	int insertDetectData(DetectData data);

	@Select({"<script>",
			"select * from qoe_mos where 1=1",
			"<when test='result_id!=null'>","and result_id=#{result_id}","</when>",
			"</script>"
	})
	List<DetectData> getTest(Map map);
}
