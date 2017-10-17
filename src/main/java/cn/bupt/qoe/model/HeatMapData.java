package cn.bupt.qoe.model;

public class HeatMapData {
	private Double mos_sub;
	private Double longitude;
	private Double latitude;
	
	public Double getMos_sub() {
		return mos_sub;
	}
	public void setMos_sub(Double mos_sub) {
		this.mos_sub = mos_sub;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "HeatMapData [mos_sub=" + mos_sub + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
	
}
