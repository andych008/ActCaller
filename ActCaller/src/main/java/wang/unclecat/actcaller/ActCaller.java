package wang.unclecat.actcaller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import wang.unclecat.actcaller.router.Router;
import wang.unclecat.actcaller.router.RouterFactory;


/**
 * Activity#startActivityForResult()+onActivityResult()转变为Callback形式
 *
 * @author: 喵叔catuncle
 * @date: 2021-02-15 17:33
 */
public class ActCaller {

    private Context context;

    private Router router;

    public ActCaller(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        context = activity;
        router = RouterFactory.getRouterFragmentV4(activity);
    }

    public ActCaller(FragmentActivity activity) {
        context = activity;
        router = RouterFactory.getRouterFragmentV4(activity);
    }

    public ActCaller(Activity activity) {
        context = activity;
        router = RouterFactory.getRouterFragment(activity);
    }

    public void startForResult(Class<?> clazz, Callback callback) {
        Intent intent = new Intent(context, clazz);
        router.startForResult(intent, callback);
    }

    public void startForResult(Intent intent, Callback callback) {
        if (router != null) {
            router.startForResult(intent, callback);
        } else {
            throw new RuntimeException("please do init first!");
        }
    }

    public interface Callback {
        void onActivityResult(int resultCode, Intent data);
    }
}
