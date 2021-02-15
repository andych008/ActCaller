package wang.unclecat.actcaller.router.impl;

import android.util.SparseArray;

import java.util.Random;

import wang.unclecat.actcaller.ActCaller;


/**
 * 工具类：生成随机requestCode，并与callback匹配
 *
 * @author: 喵叔catuncle
 * @date: 2021-02-15 21:18
 */
class RouterHelper {

    private final SparseArray<ActCaller.Callback> callbacks = new SparseArray<>();
    private final Random codeGenerator = new Random();

    RouterHelper() {
        // Required empty public constructor
    }

    int pushCallback(ActCaller.Callback callback) {
        int requestCode = makeRequestCode();
        callbacks.put(requestCode, callback);
        return requestCode;
    }

    ActCaller.Callback popCallback(int requestCode) {
        ActCaller.Callback callback = callbacks.get(requestCode);
        callbacks.remove(requestCode);
        return callback;
    }

    private int makeRequestCode() {
        int requestCode;
        int tryCount = 0;
        do {
            requestCode = codeGenerator.nextInt(0x0000FFFF);
            tryCount++;
        } while (callbacks.indexOfKey(requestCode) >= 0 && tryCount < 10);
        return requestCode;
    }
}
