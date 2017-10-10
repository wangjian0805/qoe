package cn.bupt.qoe.model;

import java.util.List;

/**
 * Created by Ambitous on 2017/9/18.
 */
public class HengModel {
    private Integer today;
    private List<Integer> prepreMonth;
    private List<Integer> preMonth;
    private List<Integer> nowMonth;

    public List<Integer> getPrepreMonth() {
        return prepreMonth;
    }

    public void setPrepreMonth(List<Integer> prepreMonth) {
        this.prepreMonth = prepreMonth;
    }

    public List<Integer> getPreMonth() {
        return preMonth;
    }

    public void setPreMonth(List<Integer> preMonth) {
        this.preMonth = preMonth;
    }

    public List<Integer> getNowMonth() {
        return nowMonth;
    }

    public void setNowMonth(List<Integer> nowMonth) {
        this.nowMonth = nowMonth;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }
}
