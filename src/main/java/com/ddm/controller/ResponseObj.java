package com.ddm.controller;

/**
 * Created by yunpeng.song on 6/19/2018.
 */
public interface ResponseObj extends java.io.Serializable {

    Integer getCode();
    Object getData();

}
