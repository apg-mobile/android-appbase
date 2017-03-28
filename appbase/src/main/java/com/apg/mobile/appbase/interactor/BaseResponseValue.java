package com.apg.mobile.appbase.interactor;

/**
 * Created by X-tivity on 3/28/2017 AD.
 */

public class BaseResponseValue<T> implements UseCase.ResponseValue {

    private boolean isSuccess;
    private String message;
    private T data;

    public BaseResponseValue(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
