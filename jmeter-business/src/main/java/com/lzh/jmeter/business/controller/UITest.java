package com.lzh.jmeter.business.controller;

import com.lzh.jmeter.ui.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class UITest {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{LoginTest.class});
        testNG.run();
    }
}

@Listeners({com.lzh.jmeter.ui.listener.UIListener.class})
class LoginTest {
    @Test
    public void testLogin () {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://doctor.jd.com");
    }
}
