package com.apg.mobile.appbase.repository;

/**
 * Created by X-tivity on 2/9/2017 AD.
 */

public interface OnRepoResult<T> {
    void onResponse(RepoResult<T> response);
}
