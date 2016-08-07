package financial.calculator;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DepositCalculator {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://finance.liga.net";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDepositCalculator() throws Exception {
    driver.get(baseUrl + "/finmarket/calculators.htm");
    driver.findElement(By.id("txtSumOfCredit")).clear();
    driver.findElement(By.id("txtSumOfCredit")).sendKeys("1000000");
    driver.findElement(By.id("credit_count_months")).clear();
    driver.findElement(By.id("credit_count_months")).sendKeys("36");
    driver.findElement(By.id("percent_credit")).clear();
    driver.findElement(By.id("percent_credit")).sendKeys("12");
    new Select(driver.findElement(By.id("type"))).selectByVisibleText("Классический (% на остаток задолженности)");
    driver.findElement(By.cssSelector("#credit_form > input.btn")).click();
    try {
      assertEquals(driver.findElement(By.cssSelector("#credit_result2 > div.resultSum")).getText(), "37777.78");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void closeDriver() {
    driver.quit();
  }

 }
