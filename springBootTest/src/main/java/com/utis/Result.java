package com.utis;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 状态，对应请求的返回结果（success | fail）
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据，若status = success，返回给前端json数据
     * 否则，返回错误信息
     */
    private T data;

    public static <T> Result success(T data) {
        return success(data, EmError.SUCCESS.getErrMsg());
    }

    public static Result success() {
        return new Result(EmError.SUCCESS.getErrCode(), EmError.SUCCESS.getErrMsg());
    }

    public static Result success(String msg) {
        return new Result(EmError.SUCCESS.getErrCode(), msg);
    }

    public static <T> Result success(T data, String msg) {
        return new Result(EmError.SUCCESS.getErrCode(), msg, data);
    }

    public static Result getResult(int res) {
        return res > 0 ? success() : fail();
    }

    public static Result getResult(int res, String msg) {
        return res > 0 ? success(msg) : fail();
    }

    public static Result fail() {
        return fail(EmError.FAIL.getErrMsg());
    }

    public static Result fail(int code, String msg) {
        return new Result(code, msg,"");
    }

    public static Result fail(String msg) {
        return fail(EmError.FAIL.getErrCode(), msg);
    }

    public Result() {
        super();
    }

    public Result(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Result(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

}
