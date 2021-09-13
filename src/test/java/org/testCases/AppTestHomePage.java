package org.testCases;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.stream.Collectors;

public class AppTestHomePage {



    public static void main(String[] args) throws InterruptedException {

        ObjectsRepository repository = new ObjectsRepository();
        TestCases testCases = new TestCases();
        //testCases.registrationOnTheSite();
        testCases.login();
        testCases.buying();
//        testCases.writeLogsForTest();
//        testCases.getArrivalClick();
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.image, repository.image1List));
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.imageForPage, repository.image2List));
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.imageForThis, repository.image3List));
//        testCases.getSummerTab();
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.image, repository.image1List));
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.imageForPage, repository.image2List));
//        testCases.findBrokenLinks(testCases.getListForLinks(repository.imageForThis, repository.image3List));
//        testCases.closeDriver();
        TestCases.toChangeLocateOfSite();
    }
}
