package com.lzh.jmeter.ui.video;

import com.lzh.jmeter.ui.config.Constants;
import com.lzh.jmeter.ui.driver.DriverManager;
import com.lzh.jmeter.ui.service.IScreenRecorder;
import com.lzh.jmeter.ui.utils.FileUtil;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AndroidScreenRecorder implements IScreenRecorder {

    private String videoPath;
    private final Logger logger =  LoggerFactory.getLogger(AndroidScreenRecorder.class);

    @Override
    public void startRecorder(String testName) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        videoPath = Constants.ANDROID_VIDEO_PATH + "/android-" + testName + "-" + df.format(new Date()) + Constants.VIDEO_SUFFIX;
        ((AndroidDriver) DriverManager.getDriver()).startRecordingScreen();
    }

    @Override
    public void stopRecorder() {
        String base64 = ((AndroidDriver) DriverManager.getDriver()).stopRecordingScreen();
        FileUtil.saveVideo(base64, videoPath);
    }

    @Override
    public void delVideoFile() {
        logger.info("删除Android的视频文件：{}", videoPath);
        File file = new File(videoPath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }
}
