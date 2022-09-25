package com.testinium.methods;

import com.testinium.driver.Driver;
import com.testinium.helper.StoreHelper;
import com.testinium.helper.ElementHelper;
import com.testinium.model.ElementInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Methods {

    WebDriver driver;

    AppiumDriver mobiledriver;
    FluentWait<WebDriver> wait;
    FluentWait<AppiumDriver> mobilwait;
    JavascriptExecutor javascriptdriver;

    public Methods(){
        driver = Driver.driver;
        mobiledriver = Driver.appiumDriver;
        if(driver!=null) {
            wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(NoSuchElementException.class);
        }

        if(mobiledriver!=null){
            mobilwait = new FluentWait<>(mobiledriver);
            mobilwait.withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(NoSuchElementException.class);
        }
        javascriptdriver = (JavascriptExecutor) driver;
    }

    public WebElement elementiBul(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        return wait.until(ExpectedConditions.presenceOfElementLocated(info));
    }

    public WebElement elementiBulMobil(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        return mobilwait.until(ExpectedConditions.presenceOfElementLocated(info));
    }


    public List<WebElement> elementleriBul(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(info));
    }

    public void tikla(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        wait.until(ExpectedConditions.presenceOfElementLocated(info)).click();
    }

    public void tiklaMobil(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        mobilwait.until(ExpectedConditions.presenceOfElementLocated(info)).click();
    }


    public void clickByElement(WebElement webElement){

        javascriptdriver.executeScript("arguments[0].click();", webElement);
    }



    public void saniyeKadarBekle(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }

    public void milisaniyeKadarBekle(long miliseconds){
        try {
            Thread.sleep(miliseconds);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void metinYolla(String key, String metin){
        elementiBul(key).sendKeys(metin);
    }

    public void metinYollaMobil(String key, String metin){
        elementiBulMobil(key).sendKeys(metin);
    }


    public void elementiTemizle(String key){
        elementiBul(key).clear();
    }

    public void gorunurlukKontrol(String key) {
        assertTrue(elementGorunurmu(key), "element goruntulenemedi.");
    }

    public void gorunurlukKontrolMobil(String key) {
        assertTrue(elementGorunurmuMobil(key), "Mobil element goruntulenemedi.");
    }
    public boolean elementGorunurmu(String key){

        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            wait.until(ExpectedConditions.visibilityOfElementLocated(infoParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean elementGorunurmuMobil(String key){

        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            mobilwait.until(ExpectedConditions.visibilityOfElementLocated(infoParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void tiklanabilirlikKontrol(String key) {
        assertTrue(elementTiklanabilirmi(key), "element tıklanamadı.");
    }

    public void tiklanabilirlikKontrolMobil(String key) {
        assertTrue(elementTiklanabilirmiMobil(key), "element tıklanamadı.");
    }

    public boolean elementTiklanabilirmi(String key){

        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            wait.until(ExpectedConditions.elementToBeClickable(infoParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean elementTiklanabilirmiMobil(String key){

        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            mobilwait.until(ExpectedConditions.elementToBeClickable(infoParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void enterTikla(String key){
        elementiBul(key).sendKeys(Keys.ENTER);
    }

    public String textDegeriniAl(String key){
        return elementiBul(key).getText();
    }

    public String textDegeriniAlMobil(String key){
        return elementiBulMobil(key).getText();
    }

    public String attributeDegeriniAl(String key, String attribute){
        return elementiBul(key).getAttribute(attribute);
    }

    public String suankiURL(){
        return driver.getCurrentUrl();
    }

    public void urlGit(String url){
        driver.navigate().to(url);
    }

    public void jsIleScroll(String key){
        javascriptdriver.executeScript("arguments[0].scrollIntoView();",elementiBul(key));
    }

    public void elementeOdak(String key){
        javascriptdriver.executeScript("arguments[0].scrollIntoView();",elementiBul(key));
        javascriptdriver.executeScript("arguments[0].focus();",elementiBul(key));
    }

    public void mouseHover(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By info = ElementHelper.getElementInfoToBy(elementInfo);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(info));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementiBul(key)).perform();
    }

    public void randomElementFromList(String key){
        List<WebElement> elementList = elementleriBul(key);
        int maxElement = elementList.size();
        Random random = new Random();
        int randomElement = random.nextInt(maxElement);
        elementList.get(randomElement).click();
    }

    public WebElement getElementWithIndex(String key, int index){
        List<WebElement> elementList = elementleriBul(key);
        return elementList.get(index);
    }

    public Select getSelect(String key){
        return new Select(elementiBul(key));
    }
    public Select getSelectMobil(String key){
        return new Select(elementiBulMobil(key));
    }

    public void selectByText(String key, String text){
        getSelect(key).selectByVisibleText(text);
    }

    public void selectByTextMobil(String key, String text){
        getSelectMobil(key).selectByVisibleText(text);
    }

    public int randomNumber(int number){
        Random random = new Random();
        return random.nextInt(number);
    }

    public void scrollMobil(){
        TouchAction action = new TouchAction(mobiledriver);
        action.press(PointOption.point(115, 915)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(115, 150)).release().perform();
    }
}
