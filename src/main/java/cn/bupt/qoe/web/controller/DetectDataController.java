package cn.bupt.qoe.web.controller;

import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.rest.WebResult;

import javax.servlet.http.HttpServletRequest;

import cn.bupt.qoe.service.DetectResultService;
import cn.bupt.qoe.service.impl.DetectResultServiceImpl;
import cn.bupt.qoe.util.PaginatorResult;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ambitous on 2017/7/16.
 */
@Controller
@RequestMapping("/api/detect")
public class DetectDataController {

    @Autowired
    DetectResultService detectResultService;

    @Autowired
    DetectDataMapper mapper;
    //返回json格式的数据
    @RequestMapping("/data")
    @ResponseBody//对象变json
    public WebResult getAllDetectData(){
        WebResult result = new WebResult();
        result.setData(mapper.getAllDetectData());
        return result;
    }

    //安卓测量数据持久化
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public WebResult insertDetectData(@RequestBody DetectData detectData) {

        WebResult result = new WebResult();
        int insertReturn = mapper.insertDetectData(detectData);
        if (insertReturn == 1) {
            result.setMessage("插入成功");
            result.setData(1);
        } else {
            result.setCode(-1);
            result.setMessage("插入失败");
        }
        return result;
    }

    @RequestMapping("/detail")
    public @ResponseBody PaginatorResult<DetectData> listPage(
            @RequestParam(required = false, value = "pageSize", defaultValue = "1") int curPageSize,
            @RequestParam(required = false, value = "pageNumber", defaultValue = "5") int limit){
        PaginatorResult<DetectData> result = new PaginatorResult<DetectData>();
        PageList<DetectData> detectResultPageList = detectResultService.selectAllDetectResultByPage(curPageSize,limit);
        if (!CollectionUtils.isEmpty(detectResultPageList)) {
            result.setRows(detectResultPageList);
            result.setTotal(detectResultPageList.getPaginator().getTotalCount());
        }
        return result;
    }

    @RequestMapping("/test")
    public String forward(){
        return "test";
    }
    //@RequestMapping(value = "/highcharts", method = RequestMethod.POST)

}
