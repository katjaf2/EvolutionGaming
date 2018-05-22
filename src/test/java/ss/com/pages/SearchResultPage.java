package ss.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.*;

public class SearchResultPage extends Page {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    // sort by price
    public void sortByPrice() {
        wait.until(elementToBeClickable(By.xpath("//tr[@id='head_line']/td[2]//a")));
        driver.findElement(By.xpath("//tr[@id='head_line']/td[2]//a")).click();
        
        /*
        for (Iterator<WebElement> i = driver.findElements(By.cssSelector("tr#head_line a")).iterator(); i.hasNext();) {
            WebElement item = i.next();
            if( item.getText() == "Цена" ){
                item.click();
                break;
            }
        }
        */
    }

    // deal types for selectDealType
    public static String sellDealType = "/sell/";
    public static String buyDealType  = "/buy/";

    public void selectDealType( String deal ) {
        wait.until(elementToBeClickable(By.cssSelector("select[name='sid']")));
        driver.findElement(By.cssSelector("select[name='sid'] option[value*='" + deal + "']")).click();
    }

    // go to Extended Search
    public void gotoExtendedSearch() {
        wait.until(elementToBeClickable(By.cssSelector("a[href='/ru/electronics/search/']")));
        driver.findElement(By.cssSelector("a[href='/ru/electronics/search/']")).click();
    }

    public ArrayList<String> selectRandomProduct() {
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("input[name='mid[]']")));
        List<WebElement> wholelist = driver.findElements(By.cssSelector("input[name='mid[]']"));

        ArrayList<String> randomList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            WebElement item = getRandomProduct(wholelist);
            item.click();
            randomList.add(item.getAttribute("id"));
        }
        return randomList;
    }

    public static WebElement getRandomProduct(List<WebElement> wholelist) {
        WebElement product = wholelist.get( new Random().nextInt(wholelist.size()));
        return product;
    }

    public void addProductFavourite() {
        wait.until(elementToBeClickable(By.id("a_fav_sel")));
        driver.findElement(By.id("a_fav_sel")).click();
    }

    public void gotoFavourites() {

        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("scroll(250, 0)");

        wait.until(elementToBeClickable(By.cssSelector("a[href='/ru/favorites/']")));
        driver.findElement(By.cssSelector("a[href='/ru/favorites/']")).click();
    }


    // check if saved ID's of the favourites are in current list of favourites (basically what we have favourited is favourited)
    public boolean checkFavourites(ArrayList<String> savedlist) {

        wait.until(presenceOfElementLocated(By.cssSelector("input[name='mid[]']")));
        List<WebElement> favlist = driver.findElements(By.cssSelector("input[name='mid[]']"));

        Boolean no_go   = true; // by default assume that favourite was not saved

        for (Iterator<String> i = savedlist.iterator(); i.hasNext();) {

            String savedid = i.next();

            for ( WebElement element : favlist ){
                String favid = element.getAttribute("id");

                // check if favourites ID's are the same
                if( savedid.equals(favid) ){
                    no_go = false;
                }
            }

            // if atleast one favourite ID was not found break and return true, meaning that favourite was not found
            if(no_go == true){
                break;
            }
        }

        return no_go;
    }
}
