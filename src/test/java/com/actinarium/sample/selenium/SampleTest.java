package com.actinarium.sample.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author Paul Danyliuk
 * @version $Id$
 */
@Test
public class SampleTest {

    // Set this accordingly
    private static final String CHROMEDRIVER_PATH = "D:/chromedriver.exe";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("http://todomvc.com/examples/react");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test_MakeTwoTasks_Inline() throws InterruptedException {
        final WebElement input = driver.findElement(By.cssSelector("input.new-todo"));
        input.sendKeys("Make a simple test", Keys.ENTER);
        input.sendKeys("Deliver an awesome preso on Selenium", Keys.ENTER);

        List<WebElement> tasks = driver.findElements(By.cssSelector("ul.todo-list > li label"));
        Assert.assertEquals(tasks.get(0).getText(), "Make a simple test");
        Assert.assertEquals(tasks.get(1).getText(), "Deliver an awesome preso on Selenium");
    }

    @Test
    public void test_MakeTwoTasks_WithPageObjects() throws InterruptedException {
        ToDoPage toDoPage = new ToDoPage(driver);

        toDoPage.addItem("Make a simple test");
        toDoPage.addItem("Deliver an awesome preso on Selenium");

        List<ToDoRow> rows = toDoPage.getRows();
        Assert.assertEquals(rows.get(0).getLabel(), "Make a simple test");
        Assert.assertEquals(rows.get(1).getLabel(), "Deliver an awesome preso on Selenium");

        rows.get(0).clickDestroy();
        rows.get(0).clickDestroy();
        // This will wait for 5 seconds (implicit wait) before it's sure there are 0 items
        Assert.assertEquals(rows.size(), 0);
    }
}
