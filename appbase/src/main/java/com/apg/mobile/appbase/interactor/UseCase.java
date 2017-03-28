package com.apg.mobile.appbase.interactor;

import io.reactivex.Observable;

/**
 * Created by X-tivity on 3/27/2017 AD.
 */

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public abstract Observable<ResponseValue> executeUseCase(Q requestValues);

    /**
     * Data passed to a request.
     */
    public interface RequestValues {

    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {

    }
}
