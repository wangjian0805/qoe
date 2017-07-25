package cn.bupt.qoe.pojo;

import java.util.Date;

public class DetectDataWithMos {
    private Long resultId;

    private Integer cpu;

    private Integer memoryConsumption;

    private Integer screenPixels;

    private Integer videoStreamBitrate;

    private Integer throughput;

    private Integer initBuffer;

    private Integer reservedBuffer;

    private Integer videoLength;

    private Integer messageDelay;

    private Double latitude;

    private Double longitude;

    private Date detectTime;

    private Double mos;

    public Double getMos() {
        return mos;
    }

    public void setMos(Double mos) {
        this.mos = mos;
    }

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

    public Integer getInitBuffer() {
        return initBuffer;
    }

    public void setInitBuffer(Integer initBuffer) {
        this.initBuffer = initBuffer;
    }

    public Integer getReservedBuffer() {
        return reservedBuffer;
    }

    public void setReservedBuffer(Integer reservedBuffer) {
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

    @Override
    public String toString() {
        return "DetectResult{" +
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
                ", detectTime=" + detectTime +
                '}';
    }
}