package libs;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BasePage extends PropertyFiles {

    private WebDriver driver;
    private WebDriverWait wait;
    private Environment testEnvironment;

    protected BasePage(WebDriver driver) {
        if (driver != null) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 30);
        }
        ConfigFactory.setProperty("env", getPropertyValue("ENVIRONMENT"));
        testEnvironment = ConfigFactory.create(Environment.class);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected Environment getEnvironment() {
        return testEnvironment;
    }

    protected void loadUrl(String url) {
        driver.get(url);
    }

    /***
     * This method waits for an element to be displayed and returns the element. This accepts an optional timeout.
     * Timeout value would be considered only if it is passed from the calling method. Otherwiser timeout field would be ignored.
     * @param locator
     * @param timeout
     * @return WebELement
     */
    protected WebElement getElementByLocator(By locator, Integer... timeout) {
        WebElement element = null;
        try {
            if (isElementVisibleAndClickable(locator, (timeout.length > 0 ? timeout[0] : null))) {
                element = driver.findElement(locator);
            } else {
                Assert.fail("Element not found: " + locator.toString());
            }
        } catch (Exception e) {
            System.out.println("Error while locating element: " + locator.toString());
            Assert.fail("Error: " + e.getMessage());
        }
        return element;
    }

    /***
     * Invokes an expected conditions and wait till the visibility of element is true.
     * This accepts an optional timeout. Timeout value would be considered only if it is passed from the calling method. Otherwiser timeout field would be ignored.
     * @param locator
     * @param timeout
     * @return boolean
     */
    protected Boolean isElementVisibleAndClickable(By locator, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(locator), (timeout.length > 0 ? timeout[0] : null));
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Element display check : " + e.getMessage());
            return false;
        }
        return true;
    }

    /***
     * Fluent wait till the element is available. Can be used in condition where a element is displayed in the DOM after an event is performed on page.
     * @param locator
     */
    protected void waitForFrameToBeAvailable(By locator) {
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(java.util.NoSuchElementException.class);

        Boolean object = fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.findElements(locator).size() > 0;
                } catch (Exception e) {
                    System.out.println("Error while locating element: " + locator.toString());
                    Assert.fail("Error: " + e.getMessage());
                    return false;
                }
            }
        });
    }

    /***
     * Retrieve text from an element.
     * This accepts an optional timeout. Timeout value would be considered only if it is passed from the calling method. Otherwiser timeout field would be ignored.
     * @param locator
     * @param timeout
     * @return String
     */
    protected String retrieveTextFromElement(By locator, Integer... timeout) {
        String value = null;
        try {
            value = getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).getText();
        } catch (Exception e) {
            System.out.println("Error while retrieving text from element: " + locator.toString());
            Assert.fail("Error: " + e.getMessage());
        }
        return value;
    }


    /***
     * Sending text to an element
     * This accepts an optional timeout. Timeout value would be considered only if it is passed from the calling method. Otherwiser timeout field would be ignored.
     * @param locator
     * @param value
     * @param timeout
     */
    protected void sendTextIntoElement(By locator, String value, Integer... timeout) {
        try {
            if (!retrieveTextFromElement(locator, (timeout.length > 0 ? timeout[0] : null)).equalsIgnoreCase("")) {
                getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).clear();
            }
            getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).sendKeys(value);
        } catch (Exception e) {
            System.out.println("Error while sending text into element: " + locator.toString());
            Assert.fail("Error: " + e.getMessage());
        }
    }

    protected void sendTextIntoElementAndPressKey(By locator, String value, Keys keys, Integer... timeout) {
        try {
            getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).clear();
            getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).sendKeys(value, keys);
        } catch (Exception e) {
            System.out.println("Error while sending text into element: " + locator.toString());
            Assert.fail("Error: " + e.getMessage());
        }
    }


    protected void selectValueFromLookup(By locatorTextField, By locatorAutoComplete, String value, Integer... timeout) {
        try {
            sendTextIntoElementAndPressKey(locatorTextField, value, Keys.TAB);
        } catch (Exception e) {
            Assert.fail("Error: " + e.getMessage());
        }
    }

    /***
     * Click on an element
     * This accepts an optional timeout. Timeout value would be considered only if it is passed from the calling method. Otherwiser timeout field would be ignored.
     * @param locator
     * @param timeout
     */
    protected void clickOnElement(By locator, Integer... timeout) {
        try {
            getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).click();
        } catch (StaleElementReferenceException element) {
            System.out.println("Handling stale Element exception");
            getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null)).click();
        } catch (Exception e) {
            System.out.println("Error while click on element: " + locator.toString());
            Assert.fail("Error: " + e.getMessage());
        }
    }

    /***
     * Switching to a frame
     */
    protected void switchToFrame() {
        By frame = By.xpath("//iframe");
        waitForFrameToBeAvailable(frame);
        //ToDO: This is on trial basis. Currently it switches to the last parent frame and then to the first child frame.
        List<WebElement> frames = driver.findElements(frame);
        driver.switchTo().frame(frames.size() - 1);
        By childFrame = By.xpath("//iframe");
        waitForFrameToBeAvailable(childFrame);
        List<WebElement> childFrames = driver.findElements(childFrame);
        if (childFrames.size() > 0) {
            driver.switchTo().frame(childFrames.get(0));
        }
    }

    protected void switchToFrameByName(String frameName) {
        By frame = By.xpath(frameName);
        waitForFrameToBeAvailable(frame);
        driver.switchTo().frame(getElementByLocator(frame, 10));
    }

    protected void selectFromDropDownbyValue(By locator, String value, Integer... timeout) {
        Select select;
        try {
            WebElement element = getElementByLocator(locator, (timeout.length > 0 ? timeout[0] : null));
            try {
                select = new Select(element);
                select.selectByVisibleText(value);
            } catch (StaleElementReferenceException staleElement) {
                element = getUpdatedReferenceOfStaleElement(element);
                select = new Select(element);
                select.selectByVisibleText(value);
            }
        } catch (Exception e) {
            System.out.println("Unable to find the object");
            Assert.fail("Unable to find the object: " + e.getMessage());
        }
    }

    protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeOut) {
        timeOut = timeOut != null ? timeOut : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }

    protected WebElement getUpdatedReferenceOfStaleElement(WebElement staleElement) {
        String xpathStaleElement = staleElement.toString().substring(staleElement.toString().indexOf("xpath: ") + 7, staleElement.toString().length() - 1);
        try {
            return driver.findElement(By.xpath(xpathStaleElement));
        } catch (Exception e) {
            System.out.println("Cannot update reference of staleElement : " + e.getMessage());
            return null;
        }
    }
}
