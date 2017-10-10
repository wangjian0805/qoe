package cn.bupt.qoe.model;

/**
 * Created by Ambitous on 2017/9/21.
 */
public class HotMapModel {
    private String mark;
    private Double lng;
    private Double lat;
    private Integer count;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
