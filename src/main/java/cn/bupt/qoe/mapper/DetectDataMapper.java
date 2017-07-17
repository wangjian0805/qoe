package cn.bupt.qoe.mapper;

import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.rest.WebResult;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Ambitous on 2017/7/16.
 */
public interface DetectDataMapper {

	@Select(value = "select * from detect_by_wifi order by detect_time desc")
	List<DetectData> getAllDetectData();

	@Insert("insert into detect_by_wifi (cpu,memory_consumption,screen_pixels,video_stream_bitRate,"
			+ "throughput,init_buffer,reserved_buffer,video_length,message_delay,latitude,longitude,) "
			+ "values (#{cpu},#{memoryConsumption},#{screenPixels},#{videoStreamBitrate},#{throughput},#{initBuffer}"
			+ ",#{reservedBuffer},#{videoLength},#{messageDelay}，#{latitude}，#{longitude})")
	void addDetectData(DetectData data);
}
