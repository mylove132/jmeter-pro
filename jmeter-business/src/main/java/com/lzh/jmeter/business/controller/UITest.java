package com.lzh.jmeter.business.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\soft\\chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://doctor.jd.com");
    }
}
