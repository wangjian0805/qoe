package cn.bupt.qoe.rest;

import cn.bupt.qoe.exception.ProcedureException;

import java.io.Serializable;


/**
 * 
 * @author wangjian1
 * @date 日期：2017年7月5日
*  @version 1.0
 */
public class WebResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 结果代码 */
    private Integer code = null;
    /** 错误信息 */
    private String message = "";
    /** 结果数据 */
    private Object data = null;

    public WebResult() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getDesc();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    public void setException(Exception e) {
        if (e != null) {
            if (e instanceof ProcedureException) {
            	ProcedureException pe = (ProcedureException) e;
                this.code = pe.getCode();
                this.message = pe.getMessage();
            } else {
                this.code = ResultCode.POSTDATA_ERROR.getCode();
                this.message = e.getMessage();
            }
        }
        this.data = null;
    }

    public void setException(Exception e, ResultCode result) {
        if (e != null) {
            if (e instanceof ProcedureException) {
            	ProcedureException pe = (ProcedureException) e;
                this.code = pe.getCode();
                this.message = pe.getMessage();
            } else {
                setResultCode(result);
            }
        }
        this.data = null;
    }

}

