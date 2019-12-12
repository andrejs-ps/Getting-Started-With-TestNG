package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class UiTestWithDataProvider {

    WebDriver driver;

    @BeforeMethod
    public void startUpBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://github.com/login");
    }

    @DataProvider
    private Object[][] invalidCredentials(){
        return new Object[][] {
                // 1st param , 2nd param
                {"let_me_in", ""                             },
                {    ""     , "safest_password_in_the_world" },
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void emptyPasswordFailsLogin(String login, String password){
        //login
        driver.findElement(By.id("login_field")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
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