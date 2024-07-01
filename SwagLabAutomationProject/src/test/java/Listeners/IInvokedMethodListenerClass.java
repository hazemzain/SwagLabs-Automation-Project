package Listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import Utilites.LogsUtils;
import Utilites.Utility;

import java.io.File;
import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {


    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        //Utility.takeFullScreenshot(getDriver(), new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart());
        switch (testResult.getStatus()) {
            case ITestResult.FAILURE:
                LogsUtils.info("Test Case " + testResult.getName() + " failed");
                Utility.takeScreenShot(getDriver(), testResult.getName()); //validLoginTC-2024-03-03-8-17pm
                break;
            case ITestResult.SUCCESS:
                LogsUtils.info("Test Case " + testResult.getName() + " passed");
                break;
            case ITestResult.SKIP:
                LogsUtils.info("Test Case " + testResult.getName() + " skipped");
                break;
        }
        File logFile = Utility.getLatestFile(LogsUtils.LOGS_PATH);
        assert logFile != null;

    }
}
