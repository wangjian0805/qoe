package cn.bupt.qoe.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Ambitous on 2017/9/25.
 */
public class DetailModel {
    private String mac;
    private Double cpu;
    private Integer screenPixels;
    private String mimeType;
    private Integer height;
    private Integer width;
    private Double videoStreamBitRate;
    private Integer videoLength;
    private Long initTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date videoBufferStart;
    private Integer scoreTime;
    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    private Double variance;
    private Double longitude;
    private Double latitude;
    private Integer rebuffNum;
    private Long rebuffTime;

    private String manyi;
    private String bodong;

    private List<String> xAxis;
    private List<Double> objScore;
    private List<Double> subScore;
    private List<Double> messageDelay;

    private List<Double> playPercentage;
    private List<Double> bufferPercentage;
    private List<Double> sendSpeed;
    private List<Double> netSpeed;
    private List<Double> memoryConsumption;
    private List<Double> currentCPU;

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

    public Date getVideoBufferStart() {
        return videoBufferStart;
    }

    public void setVideoBufferStart(Date videoBufferStart) {
        this.videoBufferStart = videoBufferStart;
    }

    public Integer getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Integer scoreTime) {
        this.scoreTime = scoreTime;
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

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Double> getObjScore() {
        return objScore;
    }

    public void setObjScore(List<Double> objScore) {
        this.objScore = objScore;
    }

    public List<Double> getSubScore() {
        return subScore;
    }

    public void setSubScore(List<Double> subScore) {
        this.subScore = subScore;
    }

    public List<Double> getMessageDelay() {
        return messageDelay;
    }

    public void setMessageDelay(List<Double> messageDelay) {
        this.messageDelay = messageDelay;
    }

    public List<Double> getSendSpeed() {
        return sendSpeed;
    }

    public void setSendSpeed(List<Double> sendSpeed) {
        this.sendSpeed = sendSpeed;
    }

    public List<Double> getNetSpeed() {
        return netSpeed;
    }

    public void setNetSpeed(List<Double> netSpeed) {
        this.netSpeed = netSpeed;
    }

    public List<Double> getBufferPercentage() {
        return bufferPercentage;
    }

    public void setBufferPercentage(List<Double> bufferPercentage) {
        this.bufferPercentage = bufferPercentage;
    }

    public List<Double> getMemoryConsumption() {
        return memoryConsumption;
    }

    public void setMemoryConsumption(List<Double> memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }


    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    public List<Double> getPlayPercentage() {
        return playPercentage;
    }

    public void setPlayPercentage(List<Double> playPercentage) {
        this.playPercentage = playPercentage;
    }

    public List<Double> getCurrentCPU() {
        return currentCPU;
    }

    public void setCurrentCPU(List<Double> currentCPU) {
        this.currentCPU = currentCPU;
    }


    public Integer getRebuffNum() {
        return rebuffNum;
    }

    public void setRebuffNum(Integer rebuffNum) {
        this.rebuffNum = rebuffNum;
    }

    public Long getRebuffTime() {
        return rebuffTime;
    }

    public void setRebuffTime(Long rebuffTime) {
        this.rebuffTime = rebuffTime;
    }

    public String getManyi() {
        return manyi;
    }

    public void setManyi(String manyi) {
        this.manyi = manyi;
    }

    public String getBodong() {
        return bodong;
    }

    public void setBodong(String bodong) {
        this.bodong = bodong;
    }

    @Override
    public String toString() {
        return "DetailModel{" +
                "mac='" + mac + '\'' +
                ", cpu=" + cpu +
                ", screenPixels=" + screenPixels +
                ", mimeType='" + mimeType + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", videoStreamBitRate=" + videoStreamBitRate +
                ", videoLength=" + videoLength +
                ", initTime=" + initTime +
                ", videoBufferStart=" + videoBufferStart +
                ", scoreTime=" + scoreTime +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", avgScore=" + avgScore +
                ", variance=" + variance +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", xAxis=" + xAxis +
                ", objScore=" + objScore +
                ", subScore=" + subScore +
                ", messageDelay=" + messageDelay +
                ", sendSpeed=" + sendSpeed +
                ", netSpeed=" + netSpeed +
                ", bufferPercentage=" + bufferPercentage +
                ", memoryConsumption=" + memoryConsumption +
                '}';
    }
}
