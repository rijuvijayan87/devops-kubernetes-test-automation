package libs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Arrays;

/**
 * @author RVPB72 on 20/03/2020.
 */
public class EventHandler implements WebDriverEventListener {


    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        //System.out.println("URL To Be navigated to : " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        System.out.println("Navigated successfully to : " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        //System.out.println("Attempting to find element : " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        //System.out.println("Element found successfully : " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        //System.out.println("Attempting to click on element : " + webElement.toString());
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Successfully clicked on element : " + webElement.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        //System.out.println("Attempting to change value of element : " + webElement.toString());
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Successfully sent value to element : " + webElement.toString() + "| Value : " + Arrays.toString(charSequences));
    }

    @Override
    public void beforeScript(String script, WebDriver webDriver) {
        //System.out.println("Attempting run a script : " + script);
    }

    @Override
    public void afterScript(String script, WebDriver webDriver) {
        System.out.println("Successfully ran the script : " + script);
    }

    @Override
    public void beforeSwitchToWindow(String window, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String window, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        System.out.println("Attempting to grab Screenshot : " + outputType.toString());
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        System.out.println("Successfully grab Screenshot : " + outputType.toString());
    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        //System.out.println("Attempting to retrieve element : " + webDriver.toString());
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String value) {
        if (value.equalsIgnoreCase("")) {
            value = null;
        }
        System.out.println("Successfully retrieved text => " + value + " from element : " + webElement.toString());
    }
}