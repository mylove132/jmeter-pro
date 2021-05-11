package com.lzh.jmeter.ui.listener;
import com.lzh.jmeter.ui.driver.DriverManager;
import com.lzh.jmeter.ui.enums.Browser;
import com.lzh.jmeter.ui.service.IScreenRecorder;
import com.lzh.jmeter.ui.utils.FileUtil;
import com.lzh.jmeter.ui.video.AndroidScreenRecorder;
import com.lzh.jmeter.ui.video.IosScreenRecorder;
import com.lzh.jmeter.ui.video.WebScreenRecorder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class UIListener implements ITestListener {

    IScreenRecorder screenRecorder;

    public void onFinish(ITestContext arg0) {
        ((WebScreenRecorder)screenRecorder).releaseRecordingResources();
    }

    public void onStart(ITestContext arg0) {
        // 启动服务
        final WebDriver driver = DriverManager.browserDriver(Browser.CHROME);
        DriverManager.setDriver(driver);
        if (DriverManager.getDriver() instanceof WebDriver){
            screenRecorder = new WebScreenRecorder();
        } else if (DriverManager.getDriver() instanceof AndroidDriver) {
            screenRecorder = new AndroidScreenRecorder();
        }else if (DriverManager.getDriver() instanceof IOSDriver) {
            screenRecorder = new IosScreenRecorder();
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        screenRecorder.stopRecorder();
    }

    public void onTestFailure(ITestResult arg0) {
        screenRecorder.stopRecorder();
        getVideo(screenRecorder.getVideoPath());
    }

    public void onTestSkipped(ITestResult arg0) {
        screenRecorder.stopRecorder();
        getVideo(screenRecorder.getVideoPath());
    }

    public void onTestStart(ITestResult arg0) {
        screenRecorder.startRecorder(arg0.getName());
    }

    public void onTestSuccess(ITestResult arg0) {
        screenRecorder.stopRecorder();
        screenRecorder.delVideoFile();
    }

    @Attachment(value = "Video on Failure", type = "video/mp4")
    private byte[] getVideo( String videoPath ) {
        return FileUtil.getBytesByFile(videoPath);
    }
}
