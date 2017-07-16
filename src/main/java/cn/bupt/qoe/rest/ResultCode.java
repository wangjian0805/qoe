package cn.bupt.qoe.rest;

/**
 * 
 * @author wangjian1
 * @date 日期：2017年7月5日
*  @version 1.0
 */
public enum ResultCode {
    SUCCESS(1, "操作成功"),
    FAIL(-1, "操作失败"),

    PARA_NULL(-1001, "参数为空"),
    POSTDATA_ERROR(-1002, "请求数据错误或非法请求"),

    NEWS_PUSH_RT_SERVICE_ERROR(-2001, "新闻实时推送服务异常"),
    NEWS_PUSHED_ERROR(-2002,"该新闻已经推送过"),
    NEWS_PUSH_INVALID_ERROR(-2003, "该新闻已经失效"),

    NEWS_LABEL_SERVICE_ERROR(-3001, "新闻标签提取服务异常"),

    UNTRUSTED_SOURCE(-4001, "不受信任来源"),

    USER_NO_LOGIN(-5001, "用户未登录");

    /** 代码值*/
    private int code = 0;
    /** 代码值描述*/
    private String desc = null;

    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}