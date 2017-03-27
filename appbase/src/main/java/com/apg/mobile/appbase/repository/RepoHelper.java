package com.apg.mobile.appbase.repository;

import com.apg.mobile.appbase.restful.RestfulError;

import retrofit2.HttpException;


/**
 * Created by X-tivity on 3/16/2017 AD.
 */

public class RepoHelper {

    public static String getErrorMessage(Throwable throwable) {
        if (throwable instanceof RestfulError) {
            return throwable.getMessage();
        } else if (throwable instanceof HttpException) {
            return "Request error";
        } else {
            return "Unexpected error occurred";
        }
    }
}
