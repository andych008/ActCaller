package com.knight.cameraone.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.knight.cameraone.R;
import com.knight.cameraone.utils.PermissionUtil;
import com.knight.cameraone.utils.PhotoUtil;
import com.knight.cameraone.utils.RxActCaller;

import io.reactivex.disposables.Disposable;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    //展示照片的View
    private ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_photo = findViewById(R.id.iv_photo);
        checkNeedPermissions();
    }

    /**
     * 检测需要申请的权限
     */
    private void checkNeedPermissions(){
        //6.0以上需要动态申请权限 动态权限校验 Android 6.0 的 oppo & vivo 手机时，始终返回 权限已被允许 但是当真正用到该权限时，却又弹出权限申请框。
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //多个权限一起申请
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //获取权限一一验证
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //全部授于可以进行往下操作
                } else {
                    //拒绝就要强行跳转设置界面
                    PermissionUtil.showPermissionsSettingDialog(this, permissions[0]);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        goSystemPhoto();
    }

    /**
     * 跳转系统相册
     */
    private void goSystemPhoto() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");

        Disposable subscribe = new RxActCaller(this)
                .startForResult(intent)
                .subscribe(result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String photoPath = PhotoUtil.getRealPathFromUri(MainActivity.this, result.getData().getData());
                        handlePic(photoPath);
                    } else {
                        Toast.makeText(MainActivity.this, "ResultCode = " + result.getResultCode(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void handlePic(String photoPath) {

        Glide.with(this).load(photoPath)
                .into(iv_photo);

    }
}
