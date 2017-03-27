package com.apg.mobile.appbase.repository;

/**
 * Created by X-tivity on 3/14/2017 AD.
 */

public class RepoResult<T> {

    private boolean isSuccess;
    private String message;
    private T data;

    public RepoResult(boolean isSuccess, String message, T data) {
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
