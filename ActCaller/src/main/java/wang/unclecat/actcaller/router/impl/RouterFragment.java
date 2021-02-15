package wang.unclecat.actcaller.router.impl;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import wang.unclecat.actcaller.ActCaller;
import wang.unclecat.actcaller.router.Router;


/**
 * 通过Fragment实现Router
 *
 * @author: 喵叔catuncle
 * @date: 2021-02-16 8:30
 */
public class RouterFragment extends Fragment implements Router {

    private RouterHelper helper = new RouterHelper();

    public RouterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void startForResult(Intent intent, ActCaller.Callback callback) {
        int requestCode = helper.pushCallback(callback);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActCaller.Callback callback = helper.popCallback(requestCode);
        if (callback != null) {
            callback.onActivityResult(resultCode, data);
        }
    }
}
