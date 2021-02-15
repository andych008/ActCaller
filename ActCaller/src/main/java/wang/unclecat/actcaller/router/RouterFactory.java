package wang.unclecat.actcaller.router;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import wang.unclecat.actcaller.router.impl.RouterFragment;
import wang.unclecat.actcaller.router.impl.RouterFragmentV4;


/**
 * Router工厂
 *
 * @author: 喵叔catuncle
 * @date: 2021-02-15 21:19
 */
public class RouterFactory {
    private static final String TAG = "ActCaller";

    public static RouterFragmentV4 getRouterFragmentV4(FragmentActivity activity) {
        RouterFragmentV4 routerFragment = findRouterFragmentV4(activity);
        if (routerFragment == null) {
            routerFragment = new RouterFragmentV4();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(routerFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return routerFragment;
    }

    private static RouterFragmentV4 findRouterFragmentV4(FragmentActivity activity) {
        return (RouterFragmentV4) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public static RouterFragment getRouterFragment(Activity activity) {
        RouterFragment routerFragment = findRouterFragment(activity);
        if (routerFragment == null) {
            routerFragment = new RouterFragment();
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(routerFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return routerFragment;
    }

    private static RouterFragment findRouterFragment(Activity activity) {
        return (RouterFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }
}
