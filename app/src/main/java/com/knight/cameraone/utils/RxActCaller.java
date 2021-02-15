package com.knight.cameraone.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import io.reactivex.Observable;
import wang.unclecat.actcaller.ActCaller;

/**
 * ActCaller的RxJava接口适配
 *
 * @author: 喵叔catuncle
 * @date: 2021/1/22 18:45
 */
public class RxActCaller {
    private ActCaller actCaller;

    public RxActCaller(Fragment fragment) {
        actCaller = new ActCaller(fragment);
    }

    public RxActCaller(FragmentActivity activity) {
        actCaller = new ActCaller(activity);
    }

    public RxActCaller(Activity activity) {
        actCaller = new ActCaller(activity);
    }

    public Observable<Result> startForResult(final Intent intent) {
        return Observable.create(emitter -> actCaller.startForResult(intent, (resultCode, data) -> {
            emitter.onNext(new Result(resultCode, data));
            emitter.onComplete();
        }));
    }

    public static class Result {
        private final int resultCode;
        private final Intent data;

        public Result(int resultCode, Intent data) {
            this.resultCode = resultCode;
            this.data = data;
        }

        public int getResultCode() {
            return resultCode;
        }

        public Intent getData() {
            return data;
        }
    }

}
