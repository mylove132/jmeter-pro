package com.lzh.jmeter.ui.cases;
import com.lzh.jmeter.ui.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void initDriver () {
        driver = DriverManager.getDriver();
    }
}
