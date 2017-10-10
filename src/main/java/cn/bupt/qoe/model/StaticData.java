package cn.bupt.qoe.model;

public class StaticData {
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
	private String mark;
	public StaticData(String mac, Double cpu, Integer screenPixels, String mimeType, Integer height, Integer width,
					  Double videoStreamBitRate, Integer videoLength, Long initTime, Long videoBufferStart,Integer stopNum,
					  Long stopTimeAvg,Double mosOverall, String mark) {
		super();
		this.mac = mac;
		this.cpu = cpu;
		this.screenPixels = screenPixels;
		this.mimeType = mimeType;
		this.height = height;
		this.width = width;
		this.videoStreamBitRate = videoStreamBitRate;
		this.videoLength = videoLength;
		this.initTime = initTime;
		this.videoBufferStart = videoBufferStart;
		this.stopNum = stopNum;
		this.stopTimeAvg = stopTimeAvg;
		this.mosOverall = mosOverall;
		this.mark = mark;
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
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}

}
