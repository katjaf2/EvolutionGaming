package ss.com.pages;

import org.openqa.selenium.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SearchPage extends Page {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchInput(String search) {
        wait.until((WebDriver d) -> d.findElement(By.id("ptxt")));
        driver.findElement(By.id("ptxt")).sendKeys(search);

        // the div block with options will appear, select first option in it
        // otherwise the div block will cover all of the other fields in the form
        wait.until((WebDriver d) -> d.findElement(By.id("cmp_1")));
        driver.findElement(By.id("cmp_1")).click();
    }

    public void minPriceInput(String minprice) {
        wait.until((WebDriver d) -> d.findElement(By.name("topt[8][min]")));

        WebElement field = driver.findElement(By.name("topt[8][min]"));
        if( field.getText() != null ) field.clear(); // empty out if not empty
        field.sendKeys(minprice);
    }

    public void maxPriceInput(String maxprice) {
        wait.until((WebDriver d) -> d.findElement(By.name("topt[8][max]")));

        WebElement field = driver.findElement(By.name("topt[8][max]"));
        if( field.getText() != null ) field.clear();
        field.sendKeys(maxprice);
    }

    // constants for selectSubHeading
    public static String sellSubHeading  = "1"; // Sell /Продают
    public static String buySubHeading   = "2"; // Buy  /Покупают
    public static String otherSubHeading = "5"; // Other/Разное

    public void selectSubHeading(String subvalue) {
        driver.findElement(By.cssSelector("select[name='sid'] option[value='" + subvalue + "']")).click();
    }

    // constants for selectSubHeading
    public static String rigaCity = "riga_f"; // city Riga
    public static String allCity  = "0";      // all cities and regions

    public void selectCity( String city ) {
        driver.findElement(By.cssSelector("select#s_region_select option[value='" + city + "']")).click();
    }

    // constants for selectPeriod
    public static String allPeriods = "0";

    public void selectPeriod( String period ) {
        driver.findElement(By.cssSelector("select[name='pr'] option[value='" + period + "']")).click();
    }

    public void sortBy( String sort ) {
        driver.findElement(By.cssSelector("select[name='sort'] option[value='" + sort + "']")).click();
    }

    public void Search() {
        wait.until(elementToBeClickable(By.id("sbtn")));
        driver.findElement(By.id("sbtn")).click();
    }

}
