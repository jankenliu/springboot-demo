package com.jankin.springboot.demo.common.result;


import lombok.ToString;

@ToString
public class Result<T> {
    private boolean state;
    private int code;
    private String message;
    private String desc;
    private T info;
    private String name;

    public Result(){}

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result(boolean state, int code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public Result<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Result<T> setId(int id) {
        return this;
    }

    public T getInfo() {
        return info;
    }

    public Result<T> setInfo(T info) {
        this.info = info;
        return this;
    }

    public String getName() {
        return name;
    }

    public Result<T> setName(String name) {
        this.name = name;
        return this;
    }

    public Result(boolean state) {
        ResultStateEnum resultState = state ? ResultStateEnum.SUCCESS : ResultStateEnum.FAILURE;
        this.state = resultState.isState();
        this.code = resultState.getCode();
        this.message = resultState.getMessage();
    }

    public Result(ResultStateEnum resultState)
    {
        this.state = resultState.isState();
        this.code = resultState.getCode();
        this.message = resultState.getMessage();
    }


    public Result(ResultStateEnum resultState, String name) {
        this.state = resultState.isState();
        this.code = resultState.getCode();
        this.message = resultState.getMessage();
        this.name = resultState.name();
    }

    public  static Result success() {
        return new Result(true);
    }

    public static  Result success(Object info) {
        return Result.success().setInfo(info);
    }

    public  static Result fail() {
        return new Result(false);
    }

    public static  Result fail(String desc) {
        return Result.fail().setDesc(desc);
    }

}