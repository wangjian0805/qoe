package cn.bupt.qoe.model;

public class MonitorData {
	private Long id;
	private Long monitorTime;
	private Long monitorTimeStamp;
	private Integer sendSpeed;
	private Integer netSpeed;
	private Integer bufferPercentage;
	private Double memoryConsumption;
	private Double currentCpu;
	private String mark;
	
	
	public Double getCurrentCpu() {
		return currentCpu;
	}
	public void setCurrentCpu(Double currentCpu) {
		this.currentCpu = currentCpu;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(Long monitorTime) {
		this.monitorTime = monitorTime;
	}
	public Long getMonitorTimeStamp() {
		return monitorTimeStamp;
	}
	public void setMonitorTimeStamp(Long monitorTimeStamp) {
		this.monitorTimeStamp = monitorTimeStamp;
	}
	public Integer getSendSpeed() {
		return sendSpeed;
	}
	public void setSendSpeed(Integer sendSpeed) {
		this.sendSpeed = sendSpeed;
	}
	public Integer getNetSpeed() {
		return netSpeed;
	}
	public void setNetSpeed(Integer netSpeed) {
		this.netSpeed = netSpeed;
	}
	public Integer getBufferPercentage() {
		return bufferPercentage;
	}
	public void setBufferPercentage(Integer bufferPercentage) {
		this.bufferPercentage = bufferPercentage;
	}
	public Double getMemoryConsumption() {
		return memoryConsumption;
	}
	public void setMemoryConsumption(Double memoryConsumption) {
		this.memoryConsumption = memoryConsumption;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}
