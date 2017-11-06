package cn.bupt.qoe.model;

/*
 * 建筑物
 * buildingNo：建筑编号，对应于jpg名称的后缀
 * buildingName：建筑物名
 */
public class Building {
	private Integer buildingNo;
	private String buildingName;
	public Integer getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
