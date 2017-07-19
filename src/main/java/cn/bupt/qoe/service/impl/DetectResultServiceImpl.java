package cn.bupt.qoe.service.impl;


import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.model.DetectData;
import cn.bupt.qoe.service.DetectResultService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ambitous on 2017/6/6.
 */

@Service
public class DetectResultServiceImpl implements DetectResultService {

    @Autowired
    private DetectDataMapper mapper;

    public PageList<DetectData> selectAllDetectResultByPage(int curPageSize, int limit){
        PageBounds pageBounds = new PageBounds(curPageSize,limit);
        PageList<DetectData> pageList = (PageList<DetectData>) mapper.selectAllByPage(pageBounds);
        return pageList;
    }

}
