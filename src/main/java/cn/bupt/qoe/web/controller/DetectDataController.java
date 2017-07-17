package cn.bupt.qoe.web.controller;

import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.rest.WebResult;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ambitous on 2017/7/16.
 */
@Controller
@RequestMapping("/api/detect")
public class DetectDataController {

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

}
