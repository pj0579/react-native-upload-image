package com.reactlibrary.upload.listener;

import android.util.Log;

import com.tencent.upload.task.ITask;
import com.tencent.upload.task.IUploadTaskListener;
import com.tencent.upload.task.data.FileInfo;

import java.util.ArrayList;
import java.util.List;

public class ImageUploadTaskListener implements IUploadTaskListener {
    ArrayList<String> recived = new ArrayList<>();
    int finishCount, maxCount;
    MultiUploadListener listener;

    public ImageUploadTaskListener(int num, MultiUploadListener listener) {
        maxCount = num;
        this.listener = listener;
    }

    @Override
    public void onUploadSucceed(FileInfo fileInfo) {
        Log.v("image_path","success");
        recived.add(fileInfo.url);
        finishCount++;
        if (finishCount == maxCount) {
            listener.onUploadFinish(recived);
        }
    }

    @Override
    public void onUploadFailed(int i, String s) {
        finishCount++;
        if (finishCount == maxCount) {
            listener.onUploadFinish(recived);
        }
    }

    @Override
    public void onUploadProgress(long total, long recived) {
        Log.v("image_path","change");
        int percent = (int) (finishCount * 100 / maxCount + recived * maxCount / total);
        this.listener.onUploadProgress(percent);
    }

    @Override
    public void onUploadStateChange(ITask.TaskState taskState) {

    }

    public interface MultiUploadListener {
        void onUploadFinish(List<String> data);

        void onUploadProgress(int i);
    }
}
