package com.lzh.jmeter.ui.video;

import com.lzh.jmeter.ui.config.Constants;
import com.lzh.jmeter.ui.service.IScreenRecorder;
import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * web视频录制
 */
public class WebScreenRecorder implements IScreenRecorder {

    private final Logger logger =  LoggerFactory.getLogger(WebScreenRecorder.class);
    private String videoPath;
    private static final String[] OPTIONS = {
            "--quiet",
            "--quiet-synchro",
            "--intf",
            "dummy"
    };

    private static final String SOUT    = ":sout=#transcode{vcodec=h264,vb=%d,scale=%f}:duplicate{dst=file{dst=%s}}";
    private static final String MRL     = "screen://";
    private static final String FPS     = ":screen-fps=%d";
    private static final String CACHING = ":screen-caching=%d";

    private static final int    fps     = 20;
    private static final int    caching = 500;
    private static final int    bits    = 1024;
    private static final float  scale   = 0.5f;

    private final MediaPlayerFactory mediaPlayerFactory;
    private final MediaPlayer mediaPlayer;

    public WebScreenRecorder () {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir")+"/lib");
        System.setProperty("VLC_PLUGIN_PATH",  System.getProperty("user.dir")+"/lib/plugins");
        mediaPlayerFactory = new MediaPlayerFactory(OPTIONS);
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
    }

    @Override
    public void startRecorder(String testName) {
        String mp4FileName = getFile(testName);
        logger.info("start recording, "+ mp4FileName);
        mediaPlayer.playMedia(MRL, getMediaOptions(mp4FileName));
    }

    @Override
    public void stopRecorder() {
        logger.info("stop recording ");
        mediaPlayer.stop();
    }

    /**
     * 释放资源
     */
    public void releaseRecordingResources() {
        mediaPlayer.release();
        mediaPlayerFactory.release();
    }

    private String getFile(String testName) {
        File dir = new File(Constants.WEB_VIDEO_PATH);
        DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        videoPath = dir.getAbsolutePath() + "/web-" + testName + "-" + df.format(new Date()) + Constants.VIDEO_SUFFIX;
        return videoPath;
    }

    /**
     * 删除视频文件
     */
    @Override
    public void delVideoFile () {
        logger.info("删除web的视频文件：{}", videoPath);
        File file = new File(videoPath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取视频文件路径
     * @return
     */
    public String getVideoPath () {
        return this.videoPath;
    }

    /**
     * 获取视频设置参数
     * @param destination
     * @return
     */
    private String[] getMediaOptions(String destination) {
        return new String[] {
                String.format(SOUT, bits, scale, destination),
                String.format(FPS, fps),
                String.format(CACHING, caching)
        };
    }
}
