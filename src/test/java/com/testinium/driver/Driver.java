package com.testinium.driver;

import com.thoughtworks.gauge.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Driver {
    public static WebDriver driver;
    public static AppiumDriver<MobileElement> appiumDriver;

    protected boolean localAndroid = true;

    @BeforeScenario
    public void setUp(ExecutionContext executionContext) throws MalformedURLException {

      if(Objects.equals(executionContext.getCurrentScenario().getName(), "A101 Web")){
            System.out.println("Chrome web tarayicisi ayaga kalkti");
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
      }else if(Objects.equals(executionContext.getCurrentScenario().getName(), "A101 Mobil")){
          if (localAndroid){
              System.out.println("Lokalde Android test ayaga kalkti ");
              DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
              desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
              desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
              desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"org.studionord.a101.MainActivity");
              desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"org.studionord.a101");
              URL url = new URL("http://127.0.0.1:4723/wd/hub");
              appiumDriver = new AndroidDriver(url, desiredCapabilities);

          }else{
              System.out.println("Lokalde ios  test ayağa kalktı ");
              DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
              desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
              desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCuiTest");
              desiredCapabilities.setCapability(MobileCapabilityType.UDID, "00008030-00157936018B802E");
              desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.lcwaikiki.iphone");
              desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
              desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.2.1");
              URL url = new URL("http://127.0.0.1:4723/wd/hub");
              appiumDriver = new IOSDriver(url, desiredCapabilities);
          }
      }
    }

    @AfterScenario
    public void tearDown(){
        if(driver != null) {
            driver.close();
            driver.quit();
        }

        if(appiumDriver != null){
            appiumDriver.close();
            appiumDriver.quit();
        }
    }
}
