package com.lzh.jmeter.ui.driver;
import com.lzh.jmeter.ui.config.Constants;
import com.lzh.jmeter.ui.enums.Browser;
import com.lzh.jmeter.ui.enums.CapabilitiesType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    /**
     * 获取启动服务
     * @return
     */
    public static WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * 设置启动服务
     * @param driver
     */
    public static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    /**
     * 返回Android服务
     * @return
     */
    public static AndroidDriver<MobileElement> androidDriver () {
        try {
            return new AndroidDriver<MobileElement>(
                    new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(CapabilitiesType.ANDROID)
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException("启动Android服务异常");
        }
    }

    /**
     * 获取ios服务
     * @return
     */
    public static IOSDriver<MobileElement> iosDriver () {
        try {
            return new IOSDriver<MobileElement>(
                    new URL(""), getCapabilities(CapabilitiesType.IOS)
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException("启动Android服务异常");
        }
    }

    /**
     * 获取浏览器服务
     * @param browser
     * @return
     */
    public static WebDriver browserDriver (Browser browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
                driver = new ChromeDriver((ChromeOptions)getCapabilities(CapabilitiesType.CHROME));
                break;
            case FIREFOX:
                driver = new FirefoxDriver((FirefoxOptions) getCapabilities(CapabilitiesType.FIREFOX));
                break;
            default:
                driver = new ChromeDriver((ChromeOptions)getCapabilities(CapabilitiesType.CHROME));
                break;
        }
        return driver;
    }

    /**
     * 设置服务启动参数
     * @param capabilitiesType
     * @return
     */
    public static MutableCapabilities getCapabilities (CapabilitiesType capabilitiesType) {
        MutableCapabilities mutableCapabilities;
        switch (capabilitiesType) {
            case CHROME:
                // todo 设置chrome启动参数
                mutableCapabilities = new ChromeOptions();
                break;
            case FIREFOX:
                // todo 设置firefox启动参数
                mutableCapabilities = new FirefoxOptions();
                break;
            case ANDROID:
                // todo 设置Android启动参数
                mutableCapabilities = new DesiredCapabilities();
                break;
            case IOS:
                // todo 设置ios启动参数
                mutableCapabilities = new DesiredCapabilities();
                break;
            default:
                mutableCapabilities = new MutableCapabilities();
                break;
        }
        return mutableCapabilities;
    }
}
