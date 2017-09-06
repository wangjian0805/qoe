package cn.bupt.qoe.model;

import java.util.List;

public class AddErrorList {
	private List<QueryModel> errList;

	public List<QueryModel> getErrList() {
		return errList;
	}

	public void setErrList(List<QueryModel> errList) {
		this.errList = errList;
	}

	@Override
	public String toString() {
		return "AddErrorList [errList=" + errList + "]";
	}
	
}
