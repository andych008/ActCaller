package wang.unclecat.actcaller.router;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import java.util.Random;

import wang.unclecat.actcaller.ActCaller;


/**
 * Activity#startActivityForResult()+onActivityResult()转变为Callback形式的功能抽象
 *
 * @author: 喵叔catuncle
 * @date: 2021-02-15 21:08
 */
public interface Router  {
    void startForResult(Intent intent, ActCaller.Callback callback);
}
