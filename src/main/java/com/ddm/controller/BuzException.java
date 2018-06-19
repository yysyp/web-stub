package com.ddm.controller;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public class BuzException extends RuntimeException implements ResponseObj {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String reason;

    public BuzException(Integer code) {
        super(code.toString(), null);
        this.code = code;
    }

    public BuzException(Integer code, String reason) {
        super(code.toString());
        this.code = code;
        this.reason = reason;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public Object getData() {
        return reason;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
