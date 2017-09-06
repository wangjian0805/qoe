package cn.bupt.qoe.model;

import java.util.Date;

public class DetectData {
    private Long resultId;

    private Integer cpu;

    private Integer memoryConsumption;

    private Integer screenPixels;

    private Integer videoStreamBitrate;

    private Integer throughput;

    private Double initBuffer;

    private Double reservedBuffer;

    private Integer videoLength;

    private Integer messageDelay;

    private Double latitude;

    private Double longitude;

    private Double mosObj;

    private Double mosSub;

    private Date detectTime;

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemoryConsumption() {
        return memoryConsumption;
    }

    public void setMemoryConsumption(Integer memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }

    public Integer getScreenPixels() {
        return screenPixels;
    }

    public void setScreenPixels(Integer screenPixels) {
        this.screenPixels = screenPixels;
    }

    public Integer getVideoStreamBitrate() {
        return videoStreamBitrate;
    }

    public void setVideoStreamBitrate(Integer videoStreamBitrate) {
        this.videoStreamBitrate = videoStreamBitrate;
    }

    public Integer getThroughput() {
        return throughput;
    }

    public void setThroughput(Integer throughput) {
        this.throughput = throughput;
    }

    public Double getInitBuffer() {
        return initBuffer;
    }

    public void setInitBuffer(Double initBuffer) {
        this.initBuffer = initBuffer;
    }

    public Double getReservedBuffer() {
        return reservedBuffer;
    }

    public void setReservedBuffer(Double reservedBuffer) {
        this.reservedBuffer = reservedBuffer;
    }

    public Integer getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Integer videoLength) {
        this.videoLength = videoLength;
    }

    public Integer getMessageDelay() {
        return messageDelay;
    }

    public void setMessageDelay(Integer messageDelay) {
        this.messageDelay = messageDelay;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDetectTime() {
        return detectTime;
    }

    public void setDetectTime(Date detectTime) {
        this.detectTime = detectTime;
    }

    public Double getMosObj() {
        return mosObj;
    }

    public void setMosObj(Double mosObj) {
        this.mosObj = mosObj;
    }

    public Double getMosSub() {
        return mosSub;
    }

    public void setMosSub(Double mosSub) {
        this.mosSub = mosSub;
    }

    @Override
    public String toString() {
        return "DetectData{" +
                "resultId=" + resultId +
                ", cpu=" + cpu +
                ", memoryConsumption=" + memoryConsumption +
                ", screenPixels=" + screenPixels +
                ", videoStreamBitrate=" + videoStreamBitrate +
                ", throughput=" + throughput +
                ", initBuffer=" + initBuffer +
                ", reservedBuffer=" + reservedBuffer +
                ", videoLength=" + videoLength +
                ", messageDelay=" + messageDelay +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", mosObj=" + mosObj +
                ", mosSub=" + mosSub +
                ", detectTime=" + detectTime +
                '}';
    }
}