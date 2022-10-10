import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Vezba_SwagLabs_Maven {

    private WebDriver browser;

    @BeforeMethod
    public void createBworserInstance() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void closeBrowser() {
        browser.quit();
    }

    /*
    This method Login and set username&password for 4 different users and wehere is one invalid user
     */
    @Test(priority = 1)
    public void login() throws InterruptedException {
        // Username 1
        WebElement userName = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));
        browser.findElement(By.cssSelector("input[name=\"user-name\"]")).sendKeys("standard_user");

        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(1000);

        browser.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        Thread.sleep(2000);
        browser.findElement(By.cssSelector("#inventory_sidebar_link")).isDisplayed();
        browser.findElement(By.cssSelector("#about_sidebar_link")).isDisplayed();
        browser.findElement(By.cssSelector("#logout_sidebar_link")).isDisplayed();
        browser.findElement(By.cssSelector("#reset_sidebar_link")).isDisplayed();

        // Username2
        browser.findElement(By.cssSelector("#logout_sidebar_link")).click();
        browser.findElement(By.cssSelector("input[name=\"user-name\"]")).clear();
        WebElement userName2 = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));

        userName2.sendKeys("locked_out_user");
        browser.findElement(By.cssSelector("#password")).clear();
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        browser.findElement(By.id("login-button")).click();
        String errorTextActual = browser.findElement(By.cssSelector(".error-message-container.error")).getText();
        String errorTextExpected = "Epic sadface: Sorry, this user has been locked out.";

        browser.findElement(By.cssSelector("#password")).clear();
        browser.findElement(By.cssSelector("input[name=\"user-name\"]")).clear();
        Thread.sleep(1000);

        //Username3
        WebElement userName3 = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));
        userName3.sendKeys("problem_user");
        browser.findElement(By.cssSelector("#password")).clear();
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        browser.findElement(By.id("login-button")).click();
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(1000);

        //UserName4
        browser.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("#logout_sidebar_link")).click();
        WebElement userName4 = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));
        userName4.sendKeys("performance_glitch_user");
        browser.findElement(By.cssSelector("#password")).clear();
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(1000);
    }
    /*
    This test firstly login, than approaches first of 3 products,check each title of them and state of button
     */
    @Test(priority = 2)
    public void checkAllProducts() throws InterruptedException {
        WebElement userName = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));
        userName.sendKeys("standard_user");
        ;
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();

        // Accesses the first product and check the title,  default TEXT value of button and when is pressed
        Thread.sleep(2000);
        browser.findElement(By.cssSelector("#item_4_img_link")).click();
        String actualTitle1 = browser.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        String expectedTitle1 = "Sauce Labs Backpack";
        Assert.assertEquals(actualTitle1, expectedTitle1);

        String buttonDefaultName1 = browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).getText();
        String buttonExpectedName1 = "ADD TO CART";
        Assert.assertEquals(buttonDefaultName1,buttonExpectedName1);
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        String buttonNotDefaultName1 = browser.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).getText();
        String buttonNotExpectedName1 = "REMOVE";
        Assert.assertEquals(buttonNotDefaultName1,buttonNotExpectedName1);


        Thread.sleep(1000);
        browser.navigate().back();
        Thread.sleep(1000);

        // Accesses the second product and check the title
        browser.findElement(By.cssSelector("#item_1_img_link")).click();
        String actualTitle2 = browser.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        String expectedTitle2 = "Sauce Labs Bolt T-Shirt";
        Assert.assertEquals(actualTitle2, expectedTitle2);

        String buttonDefaultName2 = browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).getText();
        String buttonExpectedName2 = "ADD TO CART";
        Assert.assertEquals(buttonDefaultName1,buttonExpectedName1);
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        String buttonNotDefaultName2 = browser.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).getText();
        String buttonNotExpectedName2 = "REMOVE";
        Assert.assertEquals(buttonNotDefaultName1,buttonNotExpectedName1);

        Thread.sleep(1000);
        browser.navigate().back();
        Thread.sleep(1000);

        // Accesses the third product and check the title // Scrooling does not neccessary
        JavascriptExecutor j = (JavascriptExecutor) browser;
        j.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
        browser.findElement(By.cssSelector("#item_2_img_link")).click();
        String actualTitle3 = browser.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        String expectedTitle3 = "Sauce Labs Onesie";
        Assert.assertEquals(actualTitle3, expectedTitle3);

        String buttonDefaultName3 = browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).getText();
        String buttonExpectedName3 = "ADD TO CART";
        Assert.assertEquals(buttonDefaultName3,buttonExpectedName3);
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        String buttonNotDefaultName3 = browser.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).getText();
        String buttonNotExpectedName3 = "REMOVE";
        Assert.assertEquals(buttonNotDefaultName3,buttonNotExpectedName3);

        Thread.sleep(1000);
        browser.navigate().back();
        Thread.sleep(1000);
    }
    /*
    This test checking prodcusts of basket
     */
    @Test(priority = 3)
    public void checkBasket(){
        WebElement userName = browser.findElement(By.cssSelector("input[name=\"user-name\"]"));
        userName.sendKeys("standard_user");
        browser.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();

        browser.findElement(By.cssSelector("#item_4_img_link")).click();
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        browser.navigate().back();
        browser.findElement(By.cssSelector("#item_1_img_link")).click();
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        browser.navigate().back();
        browser.findElement(By.cssSelector("#item_2_img_link")).click();
        browser.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        browser.navigate().back();

        browser.findElement(By.cssSelector(".shopping_cart_container>a")).click();
        String basketNumberActual = browser.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        String basketNumberExpexted = "3";
        Assert.assertEquals(basketNumberActual,basketNumberExpexted);

    }

}
