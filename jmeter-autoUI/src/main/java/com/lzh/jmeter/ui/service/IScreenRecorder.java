package com.lzh.jmeter.ui.service;

public interface IScreenRecorder {

    /**
     * 开启录像
     */
    void startRecorder (String testName);

    /**
     * 停止录像
     */
    void stopRecorder ();

    /**
     * 删除视频文件
     */
    void delVideoFile ();

    /**
     * 获取视频路径
     */
    String getVideoPath ();
}
