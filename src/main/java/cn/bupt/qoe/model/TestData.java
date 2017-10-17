package cn.bupt.qoe.model;

public class TestData {
	private Long id;
	private Double mos_sub;
	private Double mos_obj;
	private Long testTime;
	private Long testTimeStamp;
	private Double messageDelay;
	private Double longitude;
	private Double latitude;
	private String mark;

	public Long getTestTimeStamp() {
		return testTimeStamp;
	}

	public void setTestTimeStamp(Long testTimeStamp) {
		this.testTimeStamp = testTimeStamp;
	}

	public Double getMos_sub() {
		return mos_sub;
	}

	public void setMos_sub(Double mos_sub) {
		this.mos_sub = mos_sub;
	}

	public Double getMos_obj() {
		return mos_obj;
	}

	public void setMos_obj(Double mos_obj) {
		this.mos_obj = mos_obj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestTime() {
		return testTime;
	}

	public void setTestTime(Long testTime) {
		this.testTime = testTime;
	}

	public Double getMessageDelay() {
		return Math.abs(messageDelay);
	}

	public void setMessageDelay(Double messageDelay) {
		this.messageDelay = messageDelay;
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "TestData [id=" + id + ", mos_sub=" + mos_sub + ", mos_obj=" + mos_obj + ", testTime=" + testTime
				+ ", testTimeStamp=" + testTimeStamp + ", messageDelay=" + messageDelay + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", mark=" + mark + "]";
	}

}
