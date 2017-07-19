package cn.bupt.qoe.service;

import cn.bupt.qoe.model.DetectData;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.List;

/**
 * Created by Ambitous on 2017/6/6.
 */
public interface DetectResultService {


    PageList<DetectData> selectAllDetectResultByPage(int curPageSize, int limit);

}
