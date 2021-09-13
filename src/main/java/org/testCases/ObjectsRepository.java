package org.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectsRepository {

    private WebDriver driver;

    public ObjectsRepository(WebDriver driver) {
        this.driver = driver;
    }

    public ObjectsRepository() {
    }

    By image = By.cssSelector("source[srcset*='https://images.stuartweitzman.com/is/image']");
    String image1List = "srcset";
    By imageForPage = By.cssSelector("img[src*='https://images.stuartweitzman.com/']");
    String image2List = "src";
    By imageForThis = By.cssSelector("picture[data-iesrc*='https://images.stuartweitzman.com'");
    String image3List = "data-iesrc";
    private By newArrival = By.id("newarrivals");
    private By allNewArrivals = By.xpath("//span[text()='ALL NEW ARRIVALS']");
    private By arrival = By.cssSelector("#newarrivals");
    private By theSummerEdit = By.xpath("//span[text()='THE SUMMER EDIT']");
    private By textSummer = By.xpath("//h1[text()='INTRODUCING THE SUMMER EDIT']");
    String text = "SUMMER EDIT";

    public WebElement getTheSummerEdit() {
        return driver.findElement(theSummerEdit);
    }

    public WebElement getTextSummer() {
        return driver.findElement(textSummer);
    }

    public WebElement getArrival() {
        return driver.findElement(arrival);
    }

    public WebElement getAllNewArrivals() {
        return driver.findElement(allNewArrivals);
    }

    public WebElement getNewArrival() {
        return driver.findElement(newArrival);
    }

    public List<String> getImage() {
        List<WebElement> images = driver.findElements(image);
        return images.stream().map(e -> e.getAttribute("srcset"))
                .distinct().collect(Collectors.toList());
    }
    public List<String> getImageForPage() {
        List<WebElement> imagesForPage = driver.findElements(imageForPage);
        return imagesForPage.stream().map(e -> e.getAttribute("src"))
                .distinct().collect(Collectors.toList());
    }
    public List<String> imageForThis() {
        List<WebElement> imagesForThis = driver.findElements(imageForThis);
        return imagesForThis.stream().map(e -> e.getAttribute("data-iesrc"))
        .distinct().collect(Collectors.toList());
    }
}
