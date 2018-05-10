package ss.com.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ss.com.pages.MainPage;
import ss.com.pages.SearchPage;
import ss.com.pages.SearchResultPage;

import java.util.ArrayList;

public class SSTest {
    private MainPage            mainPage;
    private SearchPage          searchPage;
    private SearchResultPage    searchResultPage;
    private WebDriver           driver;
    private WebDriverWait       wait;

    @Before
    public void start() {
        driver              = new FirefoxDriver();
        wait                = new WebDriverWait(driver, 10);

        mainPage            = new MainPage(driver);
        searchPage          = new SearchPage(driver);
        searchResultPage    = new SearchResultPage(driver);
    }

    @Test
    public void SSTest(){
        driver.manage().window().maximize(); // maximize window

        // open ss.com
        mainPage.open();
        mainPage.changeLanguageto(mainPage.languageRU); // change language to Russian

        // go to Electronics and click search
        mainPage.clickLinkToElectorincs();
        mainPage.clicklinkToSearch();

        // fill search parameters
        searchPage.searchInput("Компьютер");
        searchPage.minPriceInput("100");
        searchPage.maxPriceInput("1000");
        searchPage.selectSubHeading(searchPage.sellSubHeading);
        searchPage.selectCity(searchPage.rigaCity);
        searchPage.selectPeriod(searchPage.allPeriods);

        // press search
        searchPage.Search();

        // sort by price
        searchResultPage.sortByPrice();

        // change deal type to Buy (Покупка)
        searchResultPage.selectDealType(searchResultPage.buyDealType);
        // go to extended search
        searchResultPage.gotoExtendedSearch();
        // change min price to 0 and max price to 300
        searchPage.minPriceInput("0");
        searchPage.maxPriceInput("300");
        searchPage.Search();
        // sort by Price
        searchResultPage.sortByPrice();
        // select 3 random things and save their ID's
        ArrayList<String> selectedProducts = searchResultPage.selectRandomProduct();
        // click Add to Favourites / Добавить выбранные в закладки
        searchResultPage.addProductFavourite();

        // open Favourites / "Закладки"
        searchResultPage.gotoFavourites();

        // check if ID's saved in selectedProducts are the same as ID's in current list
        Boolean chck = searchResultPage.checkFavourites(selectedProducts);
        // System.out.println(chck);
        Assert.assertFalse(chck); // check must be negative, meaning that all of the products have matched
    }

    @After
    public void stop(){
        // close browser
        driver.quit();
        driver = null;
    }
}
