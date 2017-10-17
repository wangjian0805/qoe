package cn.bupt.qoe.model;

import java.util.List;

public class DetectData {
	private Long id;
	private String mac;
	private Double cpu;
	private Integer screenPixels;
	private String mimeType;
	private Integer height;
	private Integer width;
	private Double videoStreamBitRate;
	private Integer videoLength;
	private Long initTime;
	private Long videoBufferStart;
	private int stopNum;
	private long stopTimeAvg;
	private double mosOverall;
	private List<TestData> test;
	private List<MonitorData> monitor;
	private String mark;

	public StaticData getStaticData() {
		StaticData sd;
		sd = new StaticData(mac, cpu, screenPixels, mimeType, height, width, videoStreamBitRate, videoLength, initTime,
				videoBufferStart, stopNum,stopTimeAvg,mosOverall,mark);
		return sd;
	}

	
	public int getStopNum() {
		return stopNum;
	}


	public void setStopNum(int stopNum) {
		this.stopNum = stopNum;
	}


	public long getStopTimeAvg() {
		return stopTimeAvg;
	}


	public void setStopTimeAvg(long stopTimeAvg) {
		this.stopTimeAvg = stopTimeAvg;
	}


	public double getMosOverall() {
		return mosOverall;
	}


	public void setMosOverall(double mosOverall) {
		this.mosOverall = mosOverall;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Double getCpu() {
		return cpu;
	}

	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}

	public Integer getScreenPixels() {
		return screenPixels;
	}

	public void setScreenPixels(Integer screenPixels) {
		this.screenPixels = screenPixels;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Double getVideoStreamBitRate() {
		return videoStreamBitRate;
	}

	public void setVideoStreamBitRate(Double videoStreamBitRate) {
		this.videoStreamBitRate = videoStreamBitRate;
	}

	public Integer getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(Integer videoLength) {
		this.videoLength = videoLength;
	}

	public Long getInitTime() {
		return initTime;
	}

	public void setInitTime(Long initTime) {
		this.initTime = initTime;
	}

	public Long getVideoBufferStart() {
		return videoBufferStart;
	}

	public void setVideoBufferStart(Long videoBufferStart) {
		this.videoBufferStart = videoBufferStart;
	}

	public List<TestData> getTest() {
		return test;
	}

	public void setTest(List<TestData> testList) {
		this.test = testList;
	}

	public List<MonitorData> getMonitor() {
		return monitor;
	}

	public void setMonitor(List<MonitorData> monitorList) {
		this.monitor = monitorList;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}


	@Override
	public String toString() {
		return "DetectData [id=" + id + ", mac=" + mac + ", cpu=" + cpu + ", screenPixels=" + screenPixels
				+ ", mimeType=" + mimeType + ", height=" + height + ", width=" + width + ", videoStreamBitRate="
				+ videoStreamBitRate + ", videoLength=" + videoLength + ", initTime=" + initTime + ", videoBufferStart="
				+ videoBufferStart + ", stopNum=" + stopNum + ", stopTimeAvg=" + stopTimeAvg + ", mosOverall="
				+ mosOverall + ", test=" + test + ", monitor=" + monitor + ", mark=" + mark + "]";
	}

	

}