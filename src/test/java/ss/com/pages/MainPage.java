package ss.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("https://www.ss.com");
        return this;
    }

    public static String languageRU = "ru"; // href to russian language for span.menu_lang a.a_menu
    public static String languageLV = "lv"; // href to latvian language for span.menu_lang a.a_menu


    public void changeLanguageto( String language ) {
        WebElement chk = driver.findElement(By.cssSelector("span.menu_lang a.a_menu[href*=" + language + "]"));

        if ( chk != null ){ // if there is a link to change to the desired language
            chk.click();
        }
    }

    public void clickLinkToElectorincs() {
        wait.until(elementToBeClickable(By.linkText("Электротехника")));
        driver.findElement(By.linkText("Электротехника")).click();
    }

    public void clicklinkToSearch() {
        wait.until(elementToBeClickable(By.linkText("Поиск")));
        driver.findElement(By.linkText("Поиск")).click();
    }
}
