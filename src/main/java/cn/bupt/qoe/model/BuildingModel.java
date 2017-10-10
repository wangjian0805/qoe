package cn.bupt.qoe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ambitous on 2017/9/27.
 */
public class BuildingModel {
    private Integer id;
    private Double latitudeLeft;
    private Double latitudeRight;
    private Double longitudeUp;
    private Double longitudeDown;
    private String building;
    private Double avg;
    private Double variance;
    private List<DetectData> dataList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitudeLeft() {
        return latitudeLeft;
    }

    public void setLatitudeLeft(Double latitudeLeft) {
        this.latitudeLeft = latitudeLeft;
    }

    public Double getLatitudeRight() {
        return latitudeRight;
    }

    public void setLatitudeRight(Double latitudeRight) {
        this.latitudeRight = latitudeRight;
    }

    public Double getLongitudeUp() {
        return longitudeUp;
    }

    public void setLongitudeUp(Double longitudeUp) {
        this.longitudeUp = longitudeUp;
    }

    public Double getLongitudeDown() {
        return longitudeDown;
    }

    public void setLongitudeDown(Double longitudeDown) {
        this.longitudeDown = longitudeDown;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<DetectData> getDataList() {
        return dataList;
    }

    public void setDataList(List<DetectData> dataList) {
        this.dataList = dataList;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }
}
