package financial.calculator;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreditCalculator {
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
  public void testCreditCalculator() throws Exception {
    driver.get(baseUrl + "/finmarket/calculators.htm");
    driver.findElement(By.id("txtSumOfDep")).clear();
    driver.findElement(By.id("txtSumOfDep")).sendKeys("500000");
    driver.findElement(By.id("count_months")).clear();
    driver.findElement(By.id("count_months")).sendKeys("12");
    driver.findElement(By.id("percent_dep")).clear();
    driver.findElement(By.id("percent_dep")).sendKeys("25");
    new Select(driver.findElement(By.id("ddl2"))).selectByVisibleText("Выплата процентов в конце срока");
    driver.findElement(By.cssSelector("input.btn")).click();
    try {
      assertEquals(driver.findElement(By.cssSelector("div.resultSum")).getText(), "125000.00");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void closeDriver() {
    driver.quit();
  }

}
