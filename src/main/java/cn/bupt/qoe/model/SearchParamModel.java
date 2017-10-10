package cn.bupt.qoe.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Ambitous on 2017/9/19.
 */
public class SearchParamModel {
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date pick_start_time;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date pick_end_time;
    private String pick_mac;

    public Date getPick_start_time() {
        return pick_start_time;
    }

    public void setPick_start_time(Date pick_start_time) {
        this.pick_start_time = pick_start_time;
    }

    public Date getPick_end_time() {
        return pick_end_time;
    }

    public void setPick_end_time(Date pick_end_time) {
        this.pick_end_time = pick_end_time;
    }

    public String getPick_mac() {
        return pick_mac;
    }

    public void setPick_mac(String pick_mac) {
        this.pick_mac = pick_mac;
    }

    @Override
    public String toString() {
        return "SearchParamModel{" +
                "pick_start_time=" + pick_start_time +
                ", pick_end_time=" + pick_end_time +
                ", pick_mac='" + pick_mac + '\'' +
                '}';
    }
}
