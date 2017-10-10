package cn.bupt.qoe.model;

import java.util.List;

/**
 * Created by Ambitous on 2017/9/18.
 */
public class AreaModel {
    private List<String> categories;
    private List<Double> avgList;
    private List<Double> varianceList;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Double> getAvgList() {
        return avgList;
    }

    public void setAvgList(List<Double> avgList) {
        this.avgList = avgList;
    }

    public List<Double> getVarianceList() {
        return varianceList;
    }

    public void setVarianceList(List<Double> varianceList) {
        this.varianceList = varianceList;
    }
}
