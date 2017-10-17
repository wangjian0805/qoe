package cn.bupt.qoe.web.controller;

import com.algorithm.QoE;
import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.model.HeatMapData;
import cn.bupt.qoe.model.MonitorData;
import cn.bupt.qoe.model.StaticData;
import cn.bupt.qoe.model.TestData;
import cn.bupt.qoe.rest.WebResult;

import cn.bupt.qoe.service.DetectResultService;
import cn.bupt.qoe.util.PaginatorResult;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ambitous on 2017/7/16.
 */
@Controller
@RequestMapping("/api/detect")
public class DetectDataController {

	private static final Logger logger = LoggerFactory.getLogger(DetectDataController.class);

	@Autowired
	DetectResultService detectResultService;

	@Autowired
	DetectDataMapper mapper;

	// 返回json格式的数据
	@RequestMapping("/data")
	@ResponseBody // 对象变json
	public WebResult getAllDetectDataAndMos() {
		WebResult result = new WebResult();
		result.setData(mapper.getAllDetectData());
		return result;
	}

	@RequestMapping(value = "hotmap", method = RequestMethod.GET)
	@ResponseBody
	public WebResult getHotMapData(@RequestParam(value = "area", defaultValue = "ALL") String area) {
		WebResult result = new WebResult();
		result.setData(mapper.getAllHotMapData());
		return result;
	}

	// 安卓测量数据持久化
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public WebResult insertDetectData(@RequestBody DetectData detectData) {
		System.out.println("进入insertDetectData方法");
		System.out.println(detectData);
		System.out.println("1.设置测量记录的唯一mark");
		// 1.设置测量记录的唯一mark
		detectData.setMark(detectData.getMac() + detectData.getVideoBufferStart());
		System.out.println("2.从detectData中分离出staticData并插入");
		// 2.从detectData中分离出staticData并插入
		StaticData staticData = detectData.getStaticData();
		WebResult result = new WebResult();
		int insertStaticDataReturn = mapper.insertStaticData(staticData);
		if (insertStaticDataReturn != 1) {
			result.setCode(-1);
			result.setMessage("插入失败");
			return result;
		}
		// 3.向monitor数据表插入
		System.out.println("3.向monitor数据表插入");
		List<MonitorData> monitorList = detectData.getMonitor();
		for (MonitorData monitorData : monitorList) {
			monitorData.setMark(staticData.getMark());
			int insertMonitorDataReturn = mapper.insertMonitorData(monitorData);
			// 只要有一个插入错误，则终止并返回 插入失败
			if (insertMonitorDataReturn != 1) {
				result.setCode(-1);
				result.setMessage("插入monitor失败");
				return result;
			}
		}
		// 4.计算QoE，向test数据表插入
		System.out.println("4.计算QoE，向test数据表插入");
		List<TestData> testList = detectData.getTest();
		long lastTestTime = 0;
		for (TestData testData : testList) {
			testData.setMark(staticData.getMark());
			double initBuffer = 1024 * 1024 * 8 / detectData.getVideoStreamBitRate();
			// 获取该次test的平均吞吐量和平均内存占比
			long currentTestTime = testData.getTestTime();
			System.out.println("lastTestTime :" + lastTestTime);
			System.out.println("currentTestTime :" + currentTestTime);
			// 根据monitorList以及视频时间范围计算该区间内平均吞吐量和平均内存占比
			double[] d = getAverageNetSpeedAndMemoryConsumption(monitorList, lastTestTime, currentTestTime);
			double averageNetSpeed = d[0];
			double averageMemoryConsumption = d[1];
			System.out.println("averageNetSpeed :" + averageNetSpeed);
			System.out.println("averageMemoryConsumption :" + averageMemoryConsumption);
			QoE qoe = new QoE(detectData.getCpu(), averageMemoryConsumption, detectData.getScreenPixels(),
					detectData.getVideoStreamBitRate(), averageNetSpeed, initBuffer, detectData.getVideoLength(),
					testData.getMessageDelay());
			double mos = qoe.getMOS();
			testData.setMos_obj(mos);
			int insertTestDataReturn = mapper.insertTestData(testData);
			// 只要有一个插入错误，则终止并返回 插入失败
			if (insertTestDataReturn != 1) {
				result.setCode(-1);
				result.setMessage("插入test失败");
				return result;
			}
			lastTestTime = currentTestTime;
		}
		// 5.插入成功
		logger.info("Insert Succuss! " + detectData.toString());
		result.setMessage("插入成功");
		result.setData(1);
		return result;
	}

	// 根据monitorList以及视频时间范围计算该区间内平均吞吐量和平均内存占比
	private double[] getAverageNetSpeedAndMemoryConsumption(List<MonitorData> monitorList, long lastTestTime,
			long currentTestTime) {
		// TODO Auto-generated method stub
		double[] result = new double[2];
		double count = 0;
		double total_NetSpeed = 0;
		double total_MemoryConsumption = 0;
		for (MonitorData monitorData : monitorList) {
			if (monitorData.getMonitorTime() > lastTestTime && monitorData.getMonitorTime() <= currentTestTime) {
				count++;
				total_NetSpeed += monitorData.getNetSpeed();
				total_MemoryConsumption += monitorData.getMemoryConsumption();
			}
		}
		System.out.println("count :" + count);
		if (count != 0) {
			result[0] = total_NetSpeed / count;
			result[1] = total_MemoryConsumption / count;
		}
		return result;
	}

	// 获取用于热图展示的数据集
	@RequestMapping(value = "/heatmapdata")
	@ResponseBody
	public WebResult getHeatMapDataSet() {
		List<TestData> testMetaData = mapper.getHeatMapMetaData();
		System.out.println("得到testMetaData，数组长度： " + testMetaData.size());
//		for (TestData testData : testMetaData) {
//			System.out.println(testData.toString());
//		}
		List<HeatMapData> heatMapData = new ArrayList<HeatMapData>();
		if (testMetaData.size() > 0) {
			// 将第一个元素保存到heatMapData
			HeatMapData h = new HeatMapData();
			h.setLatitude(testMetaData.get(0).getLatitude());
			h.setLongitude(testMetaData.get(0).getLongitude());
			h.setMos_sub(testMetaData.get(0).getMos_sub());
			heatMapData.add(h);
			// 遍历testMetaData，将每个元素和heatMapData中的元素对比，当经纬度之差都大于某个值时，才将该testMetaData存入heatMapData
			for (int i = 0; i < testMetaData.size(); i++) {
				Double longitude = testMetaData.get(i).getLongitude();
				Double latitude = testMetaData.get(i).getLatitude();
				for (int j = 0; j < heatMapData.size(); j++) {
					Double Baselongitude = heatMapData.get(j).getLongitude();
					Double Baselatitude = heatMapData.get(j).getLatitude();
					if (Math.abs(longitude - Baselongitude) < 0.00005 || Math.abs(latitude - Baselatitude) <0.00005 ) {
//						System.out.println("testMetaData中第 " + (i + 1) + "条记录不符合条件，差值分别为： "
//								+ Math.abs(longitude - Baselongitude) + ", " + Math.abs(latitude - Baselatitude));
						break;
					} else if (j == heatMapData.size() - 1) {
						HeatMapData nh = new HeatMapData();
						nh.setLatitude(testMetaData.get(i).getLatitude());
						nh.setLongitude(testMetaData.get(i).getLongitude());
						nh.setMos_sub(testMetaData.get(i).getMos_sub());
						heatMapData.add(nh);
//						System.out.println("testMetaData中第 " + (i + 1) + "条记录符合条件");
					}
				}
			}
		}
		WebResult result = new WebResult();
		result.setData(heatMapData);
		return result;
	}

	@RequestMapping("/detail")
	public PaginatorResult<DetectData> listPage(
			@RequestParam(required = false, value = "pageSize", defaultValue = "1") int curPageSize,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "5") int limit) {
		PaginatorResult<DetectData> result = new PaginatorResult<DetectData>();
		PageList<DetectData> detectResultPageList = detectResultService.selectAllDetectResultByPage(curPageSize, limit);
		if (!CollectionUtils.isEmpty(detectResultPageList)) {
			result.setRows(detectResultPageList);
			result.setTotal(detectResultPageList.getPaginator().getTotalCount());
		}
		return result;
	}

	@RequestMapping("/test")
	public String forward() {
		return "test";
	}
	// @RequestMapping(value = "/highcharts", method = RequestMethod.POST)

	@RequestMapping("/testw")
	@ResponseBody
	public WebResult getTest() {
		WebResult result = new WebResult();
		Map map = new HashMap();
		map.put("result_id", 9999l);
		result.setData(mapper.getTest(map));
		return result;
	}
}
