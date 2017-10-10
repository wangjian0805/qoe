package cn.bupt.qoe.web.controller;

import cn.bupt.qoe.pojo.HotMapData;
import com.algorithm.QoE;
import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.model.DetectData;
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
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/one")
    @ResponseBody // 对象变json
    public WebResult getOneDetectDataAndMos(String mark) {
        WebResult result = new WebResult();
        result.setData(mapper.getOneDetectDataByMark(mark));
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
        StaticData staticData = getStaticData(detectData);
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
            double initBuffer = 1024*1024*8 / detectData.getVideoStreamBitRate();
            //获取该次test的平均吞吐量和平均内存占比
            long currentTestTime = testData.getTestTime();
            System.out.println("lastTestTime :"+lastTestTime);
            System.out.println("currentTestTime :"+currentTestTime);
            //根据monitorList以及视频时间范围计算该区间内平均吞吐量和平均内存占比
            double[] d = getAverageNetSpeedAndMemoryConsumption(monitorList,lastTestTime,currentTestTime);
            double averageNetSpeed = d[0];
            double averageMemoryConsumption = d[1];
            System.out.println("averageNetSpeed :"+averageNetSpeed);
            System.out.println("averageMemoryConsumption :"+averageMemoryConsumption);
            QoE qoe = new QoE(
                    detectData.getCpu(),
                    averageMemoryConsumption,
                    detectData.getScreenPixels(),
                    detectData.getVideoStreamBitRate(),
                    averageNetSpeed,
                    initBuffer,
                    detectData.getVideoLength(),
                    testData.getMessageDelay()
            );
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


    @RequestMapping("/detail")
    public @ResponseBody PaginatorResult<DetectData> listPage(
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

/*
==========================  Helper  ====================================
 */
//根据monitorList以及视频时间范围计算该区间内平均吞吐量和平均内存占比
    private double[] getAverageNetSpeedAndMemoryConsumption(List<MonitorData> monitorList, long lastTestTime, long currentTestTime) {
    // TODO Auto-generated method stub
    double[] result = new double[2];
    double count = 0;
    double total_NetSpeed = 0;
    double total_MemoryConsumption = 0;
    for (MonitorData monitorData : monitorList) {
        if(monitorData.getMonitorTime()>lastTestTime && monitorData.getMonitorTime()<=currentTestTime){
            count++;
            total_NetSpeed += monitorData.getNetSpeed();
            total_MemoryConsumption += monitorData.getMemoryConsumption();
        }
    }
    System.out.println("count :"+count);
    if(count != 0){
        result[0] = total_NetSpeed/count;
        result[1] = total_MemoryConsumption/count;
    }

    return result;
}
    public StaticData getStaticData(DetectData data) {
        StaticData sd;
        sd = new StaticData(data.getMac(), data.getCpu(), data.getScreenPixels(), data.getMimeType(),
                data.getHeight(), data.getWidth(), data.getVideoStreamBitRate(), data.getVideoLength(), data.getInitTime(),
                data.getVideoBufferStart(),data.getStopNum(),data.getStopTimeAvg(),data.getMosOverall(), data.getMark());
        return sd;
    }
}
