package com.lzh.jmeter.business.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class PCDoctorUIListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String className = context.getClass().getSimpleName();
    }
}
