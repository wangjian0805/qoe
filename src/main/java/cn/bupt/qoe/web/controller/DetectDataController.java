package cn.bupt.qoe.web.controller;

import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.rest.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ambitous on 2017/7/16.
 */
@Controller
@RequestMapping("/api/detect")
public class DetectDataController {

    @Autowired
    DetectDataMapper mapper;

    @RequestMapping("/data")
    @ResponseBody//对象变json
    public WebResult getAllDetectData(){
        WebResult result = new WebResult();
        result.setData(mapper.getAllDetectData());
        return result;
    }

}