
package com.reactlibrary.upload;

import android.telecom.Call;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.reactlibrary.upload.listener.ImageUploadTaskListener;
import com.tencent.upload.Const;
import com.tencent.upload.UploadManager;
import com.tencent.upload.task.impl.PhotoUploadTask;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class RNReactNativeUploadImageModule extends ReactContextBaseJavaModule {
    static private Callback callback;
    private final ReactApplicationContext reactContext;
    private ImageUploadTaskListener.MultiUploadListener listener = new ImageUploadTaskListener.MultiUploadListener() {
        @Override
        public void onUploadFinish(List<String> recieved) {
            WritableArray writableArray = Arguments.createArray();
            for (String obj : recieved) {
                writableArray.pushString(obj);
            }
            RNReactNativeUploadImageModule.callback.invoke(writableArray);
        }

        @Override
        public void onUploadProgress(final int percent) {

        }
    };

    public RNReactNativeUploadImageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNReactNativeUploadImage";
    }

    @ReactMethod
    public void uploadImages(ReadableArray imgs, ReadableMap params, Callback call) {
        RNReactNativeUploadImageModule.callback = call;
        if (imgs.size() == 0) {
            WritableArray writableArray = Arguments.createArray();
            RNReactNativeUploadImageModule.callback.invoke(writableArray);
        } else {
            ArrayList<Object> images = imgs.toArrayList();
            ImageUploadTaskListener imageUploadTaskListener = new ImageUploadTaskListener(images.size(), listener);
            UploadManager photoUploadMgr = new UploadManager(getReactApplicationContext(), params.getString("appid"),
                    Const.FileType.Photo, null);
            for (int i = 0; i < images.size(); i++) {
                PhotoUploadTask task = new PhotoUploadTask(images.get(i).toString(), imageUploadTaskListener);
                task.setBucket(params.getString("bucket"));
                task.setAuth(params.getString("auth_token"));
                photoUploadMgr.upload(task);
            }
        }
    }
}