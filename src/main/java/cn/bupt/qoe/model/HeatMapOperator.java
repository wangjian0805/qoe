package cn.bupt.qoe.model;

public class HeatMapOperator {
	private int mosMax ;
	private int mosMin ;
	private Long timeStart ;
	private Long timeStop;
	private boolean againstHeaviness ;
	public int getMosMax() {
		return mosMax;
	}
	public void setMosMax(int mosMax) {
		this.mosMax = mosMax;
	}
	public int getMosMin() {
		return mosMin;
	}
	public void setMosMin(int mosMin) {
		this.mosMin = mosMin;
	}
	public Long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Long timeStart) {
		this.timeStart = timeStart;
	}
	public Long getTimeStop() {
		return timeStop;
	}
	public void setTimeStop(Long timeStop) {
		this.timeStop = timeStop;
	}
	public boolean isAgainstHeaviness() {
		return againstHeaviness;
	}
	public void setAgainstHeaviness(boolean againstHeaviness) {
		this.againstHeaviness = againstHeaviness;
	}
	
	
}
