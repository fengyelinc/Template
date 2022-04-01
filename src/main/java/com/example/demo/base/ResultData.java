package com.example.demo.base;

import lombok.Data;

@Data
public class ResultData<T> {
    private int code;

    private String message;

    private T data;

    public ResultData() {
    }

    public ResultData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResultData<T> success() {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(ResultMsgEnum.SUCCESS.getCode());
        resultData.setMessage(ResultMsgEnum.SUCCESS.getMessage());
        return resultData;
    }

    /**
     * 成功
     */
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(ResultMsgEnum.SUCCESS.getCode());
        resultData.setMessage(ResultMsgEnum.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }


    public static <T> ResultData<T> failure() {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(ResultMsgEnum.FAIL.getCode());
        resultData.setMessage(ResultMsgEnum.FAIL.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> failure(String message) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(ResultMsgEnum.FAIL.getCode());
        resultData.setMessage(message);
        return resultData;
    }

    /**
     * 失败
     */
    public static <T> ResultData<T> failure(int code, String message) {
        return new ResultData(code, message);
    }

}
