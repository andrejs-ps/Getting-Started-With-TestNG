package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UiTestWithoutDataProvider {

    private WebDriver driver;

    @BeforeMethod
    public void startUpBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://github.com/login");
    }


    @Test
    public void emptyPasswordFailsLogin(){
        //login
        driver.findElement(By.id("login_field")).sendKeys("let_me_in"); // "valid" username
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Sign in']")).click();
        // verify error appeared
        Assert.assertTrue(driver.findElement(By.className("flash-error"))
                .getText()
                .equalsIgnoreCase("Incorrect username or password."));
    }

    @Test
    public void emptyLoginFailsLogin(){
        //login
        driver.findElement(By.id("login_field")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("safest_password_in_the_world");
        driver.findElement(By.xpath("//input[@value='Sign in']")).click();
        // verify error appeared
        Assert.assertTrue(driver.findElement(By.className("flash-error"))
                .getText()
                .equalsIgnoreCase("Incorrect username or password."));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        System.out.println("Closing down the browser");
        driver.close();
    }
}
