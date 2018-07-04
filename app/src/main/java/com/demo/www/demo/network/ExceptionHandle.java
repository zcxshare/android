package com.demo.www.demo.network;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain: 异常帮助类
 */

public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int BAD_REQUEST = 400;

    public static ERROR handleException(Throwable e) {
        if (e instanceof HttpException) {//一般网络异常
            return defaultNetError((HttpException) e);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            return ERROR.PARSE_ERROR;
        } else if (e instanceof ConnectException) {
            return ERROR.NETWORK_ERROR;
        } else if (e instanceof SSLHandshakeException) {
            return ERROR.SSL_ERROR;
        } else if (e instanceof SocketTimeoutException) {
            return ERROR.TIMEOUT_ERROR;
        } else if (e instanceof UnknownHostException) {
            return ERROR.UNKNOWN_HOST_ERROR;
        } else if (e instanceof SSLException) {
            return ERROR.UNKNOWN_HOST_ERROR;
        } else if (e instanceof java.io.EOFException) {
            return ERROR.PARSE_EmptyERROR;
        } else if (e instanceof NullPointerException) {
            return ERROR.NULL_ERROR;
        } else if (e instanceof SocketException) {
            ERROR socketError = ERROR.SOCKET_ERROR;
            socketError.setMessage(socketError.getMessage() + e.getMessage());
            return socketError;
        } else {
            ERROR unknown = ERROR.UNKNOWN;
            unknown.setMessage(e.toString());
            return unknown;
        }
    }

    private static ERROR defaultNetError(HttpException httpException) {
        switch (httpException.code()) {
            case FORBIDDEN:
                return ERROR.FORBIDDEN;
            case NOT_FOUND:
                return ERROR.NOT_FOUND;
            case REQUEST_TIMEOUT:
                return ERROR.REQUEST_TIMEOUT;
            case GATEWAY_TIMEOUT:
            case INTERNAL_SERVER_ERROR:
                return ERROR.INTERNAL_SERVER_ERROR;
            case BAD_REQUEST:
            case UNAUTHORIZED:
            default:
                return ERROR.NETWORK_ERROR;
        }
    }

    /**
     * 约定异常
     */
    public enum ERROR {
        BAD_REQUEST(400, ""),
        UNAUTHORIZED(401, ""),
        FORBIDDEN(403, "服务器已经理解请求，但是拒绝执行它"),
        NOT_FOUND(404, "服务器异常，请稍后再试"),
        FAIL_QUEST(406, ""),//无法使用请求的内容特性来响应请求的网页
        REQUEST_TIMEOUT(408, "请求超时"),
        INTERNAL_SERVER_ERROR(500, "服务器遇到了一个未曾预料的状况，无法完成对请求的处理"),
        BAD_GATEWAY(502, ""),
        SERVICE_UNAVAILABLE(503, ""),
        GATEWAY_TIMEOUT(504, ""),
        /*自定义异常*/
        UNKNOWN(1000, "未知错误"),
        PARSE_ERROR(1001, "解析错误"),
        NETWORK_ERROR(1002, "网络错误"),
        HTTP_ERROR(1003, "协议出错"),
        SSL_ERROR(1004, "证书出错"),
        UNKNOWN_HOST_ERROR(1005, "网络中断，请检查网络状态！"),
        TIMEOUT_ERROR(1006, "当前网络连接不顺畅，请稍后再试！"),
        PARSE_EmptyERROR(1007, "解析no content错误"),
        NULL_ERROR(1008, "数据为空，显示失败"),
        SOCKET_ERROR(1009, "连接失败");
        private int code;
        private String message;

        ERROR(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
