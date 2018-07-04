package com.demo.www.demo.text;

import java.util.List;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/28
 * explain:
 */
public class TextBean {
    public String status;
    public Object exceptionCode;
    public Object location;
    public String message;
    public BodyBean body;
    public boolean success;
    public List<?> errors;

    public static class BodyBean {

    }
}
