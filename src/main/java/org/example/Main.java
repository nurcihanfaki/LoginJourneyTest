package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main {

    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void startTest() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //2.Navigate to url
        driver.get("https://www.automationexercise.com");

        //3.Verify that home page is visible successfully
        WebElement checkLogo = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[1]/div/a/img"));
        Assert.assertTrue(checkLogo.isDisplayed());

        //4.Click on 'Signup / Login' button
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"));
        signUpButton.click();

        //5.Verify 'Login to your account' is visible
        WebElement loginStr = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2"));
        Assert.assertTrue(loginStr.isDisplayed());

        //6.Enter correct email address and password
        WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"));
        emailBox.sendKeys("ahmet@nehaber.com");
        WebElement passwordBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]"));
        passwordBox.sendKeys("12345");

        //7.Click 'Login' button
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();

        //8.Verify that 'Logged in as username' is visible
        WebElement loggedInStr = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a"));
        Assert.assertTrue(loggedInStr.isDisplayed());

        //9.Click 'Logout' button
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();

        //10.Verify that user is navigated to login page
        String expectedUrl = "https://www.automationexercise.com/login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @After
    public void endTest() {
        driver.quit();
    }
}