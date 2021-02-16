## ActCaller
Activity#startActivityForResult()+onActivityResult()转变为Callback形式


## 使用

1. 添加依赖

    ```
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }

    implementation 'com.github.andych008:ActCaller:1.1'
    ```

1. 以从相册选取图片为例

    ```java
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_PICK);
    intent.setType("image/*");
    
    new ActCaller(activity).startForResult(intent, new ActCaller.Callback() {
        @Override
        public void onActivityResult(int resultCode, Intent data) {
             if (result.getResultCode() == Activity.RESULT_OK) {
                 Uri  photoPathUri = result.getData().getData();
             }
        }
    });
    ```

    










```

```