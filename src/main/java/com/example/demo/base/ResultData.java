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

    /**
     * 失败
     */
    public static <T> ResultData<T> error(int code, String message) {
        return new ResultData(code, message);
    }

}
