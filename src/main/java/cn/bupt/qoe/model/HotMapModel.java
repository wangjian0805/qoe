package cn.bupt.qoe.model;

import java.io.Serializable;

/**
 * Created by Ambitous on 2017/9/21.
 */
public class HotMapModel implements Serializable{
    private String mark;
    private Double lng;
    private Double lat;
    private Integer count;
    private String buildingName;
    private Integer buildingNo;

    
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}

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
