package org.testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v91.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.devtools.v91.network.model.Request;
import org.openqa.selenium.devtools.v91.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TestCases extends Initialization {

    private ChromeDriver driver = init();
    private ObjectsRepository repo;

    public void getArrivalClick() {
        repo = new ObjectsRepository(driver);
        repo.getArrival().click();
        try {
            driver.findElement(By.xpath("//span[text()='ALL NEW ARRIVALS']")).click();
        } catch (ElementNotInteractableException e) {
            e.getMessage();
        }
    }

    public void findBrokenLinks(List<String> links) {
        int count = 0;
        try {
            for (String link : links) {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(link).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseStatus = connection.getResponseCode();
                if (responseStatus >= 300) {
                    System.out.println(link + " : " + responseStatus);
                } else {
                    count++;
                }
            }
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getListForLinks(By image, String name) {

        List<WebElement> images = driver.findElements(image);
        return images.stream().map(e -> e.getAttribute(name))
                .distinct().collect(Collectors.toList());
    }

    public void getSummerTab() {
        int sum = 0;
        while (sum < 3) {

            Actions actions = new Actions(driver);
            try {
                actions.moveToElement(repo.getNewArrival()).build().perform();

            } catch (ElementNotInteractableException e) {
                e.getMessage();
            }


            if (repo.getTheSummerEdit().isDisplayed()) {
                try {
                    repo.getTheSummerEdit().click();
                    break;
                } catch (ElementNotInteractableException e) {
                    e.getMessage();

                }
            } else {
                sum++;
                System.out.println(sum);
            }
        }
        if (repo.getTextSummer().getText().contains(repo.text)) {
            System.out.println("Moving is successful");
        }
    }

    public void closeDriver() {
        driver.close();
    }

    public void writeLogsForTest() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        devTools.addListener(Network.requestWillBeSent(), request -> {
//            Request req = request.getRequest();
//            System.out.println(req.getUrl());
//            System.out.println(req.getHeaders());
//
//        });
        devTools.addListener(Network.responseReceived(), response -> {
            Response resp = response.getResponse();
            if (resp.getStatus().toString().startsWith("4")) {
                System.out.println(resp.getStatus() + " is failed with status code " + resp.getStatus());
            }
        });
    }

    public static void toChangeLocateOfSite() {
        String path = System.getProperty("user.dir") + "\\src\\Resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map<String, Object> params = new HashMap<>();
//        params.put("latitude", 37);
//        params.put("longitude",103);
        params.put("latitude", 3);
        params.put("longitude", 40);
        params.put("accuracy", 1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", params);
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("stuartweitzman", Keys.ENTER);
        driver.findElements(By.cssSelector(".LC20lb.DKV0Md")).get(0).click();
        String title = driver.findElement(By.cssSelector(".country-selector__label")).getText();
        System.out.println(title);
    }

    public void registrationOnTheSite() {
        String password = "sugandrey1utest";
        byte[] arr = password.getBytes(StandardCharsets.UTF_8);
        byte[] encodearr = Base64.getEncoder().encode(arr);
        for (byte encbyte : encodearr) {
            System.out.print(encbyte);
        }
        System.out.println();
        byte[] decarr = Base64.getDecoder().decode(encodearr);
        String myStr = new String(decarr);
        char[] myChar = myStr.toCharArray();
        for (char my : myChar) {
            System.out.print(my);
        }

        driver.findElement(By.cssSelector(".icon.icon-utility-account-login.ae-exclude")).click();
        driver.findElement(By.id("registration-form-fname")).sendKeys("andrey");
        driver.findElement(By.id("registration-form-lname")).sendKeys("andrey");
        driver.findElement(By.id("registration-form-email")).sendKeys("andreqoven@gmail.com");
        driver.findElement(By.id("registration-form-password")).sendKeys("sugandrey1utest");
        WebElement monthElement = driver.findElement(By.id("editprofile-month"));
        Select select = new Select(monthElement);
        select.selectByValue("2");
        WebElement dayElement = driver.findElement(By.id("editprofile-day"));
        select = new Select(dayElement);
        select.selectByValue("31");
        WebElement yearElement = driver.findElement(By.id("editprofile-year"));
        select = new Select(yearElement);
        select.selectByValue("1999");
        driver.findElement(By.cssSelector(".register-submit-btn")).click();
    }

    public void login() {
        driver.findElement(By.cssSelector(".icon.icon-utility-account-login.ae-exclude")).click();
        driver.findElement(By.id("login-form-email")).sendKeys("andreqoven@gmail.com");
        driver.findElement(By.id("login-form-password")).sendKeys("sugandrey1utest");
        driver.findElements(By.cssSelector("button.btn.btn-primary.btn-block")).get(1).click();

    }

    public void buying() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        try {
            Thread.sleep(2000);
            actions.moveToElement(driver.findElement(By.id("collections"))).perform();
            wait.until(ExpectedConditions.
                    visibilityOf(driver.
                            findElement(By.cssSelector("a[href='https://staging-ca.stuartweitzman.com/en/shoes/nudist-collection']"))));
            driver.findElement(By.cssSelector("a[href='https://staging-ca.stuartweitzman.com/en/shoes/nudist-collection']")).click();


        } catch (StaleElementReferenceException | InterruptedException e) {
            e.printStackTrace();

        }
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("a[class='link']"))));
        driver.findElement(By.xpath("//a[text()='NUDISTJUNE SQUARE']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("size-1"))));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@id='size-1']/button"));
        for (WebElement element : buttons) {
            String attribute = element.getAttribute("class");
            String[] attbts = attribute.split("__");
            int size = attbts.length;
            if (size == 4) {
                element.click();
                break;
            }
        }

        WebElement addToCartButton = driver.findElement(By.cssSelector(".add-to-cart.btn.btn-primary.btn-block"));
        JavascriptExecutor js = driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(2000);
        try {
            addToCartButton.click();
            System.out.println("The button is clicked successfully");

        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();

        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("minicart-link")));
        Thread.sleep(10000);
        driver.findElement(By.className("minicart-link")).click();
        actions.moveToElement(driver.findElement(By.id("collections"))).perform();
        wait.until(ExpectedConditions.
                visibilityOf(driver.
                        findElement(By.cssSelector
                                ("a[href='https://staging-ca.stuartweitzman.com/en/collections/sw-weddings']"))));
        driver.findElement(By.cssSelector
                        ("a[href='https://staging-ca.stuartweitzman.com/en/collections/sw-weddings']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("a[class='link']"))));
        driver.findElement(By.xpath("//a[text()='NEARLYNUDE']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("size-1"))));
        buttons = driver.findElements(By.xpath("//div[@id='size-1']/button"));
        for (WebElement element : buttons) {
            String attribute = element.getAttribute("class");
            String[] attbts = attribute.split("__");
            int size = attbts.length;
            if (size == 4) {
                element.click();
                break;
            }
        }
        WebElement addToCartButton1 = driver.findElement(By.cssSelector(".add-to-cart.btn.btn-primary.btn-block"));
        js.executeScript("window.scrollBy(0,750)");
        Thread.sleep(5000);
        try {
            addToCartButton1.click();
            System.out.println("The button is clicked successfully");

        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();

        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("minicart-link")));
        Thread.sleep(10000);
        driver.findElement(By.className("minicart-link")).click();
        js.executeScript("window.scrollBy(0,750)");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.checkout-btn.col")).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shippingFirstNamedefault"))).clear();
//        driver.findElement(By.id("shippingFirstNamedefault")).sendKeys("Andrey");
//        Thread.sleep(5000);
//        driver.findElement(By.id("shippingLastNamedefault")).clear();
//        driver.findElement(By.id("shippingLastNamedefault")).sendKeys("Andrey");
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shippingAddressOnedefault"))).clear();
//        driver.findElement(By.id("shippingAddressOnedefault")).sendKeys("mest");
//        List<WebElement> elements = driver.findElements(By.cssSelector("div.pcaitem.pcaexpandable"));
//        Thread.sleep(3000);
//        for (WebElement element : elements) {
//            System.out.println(element.getAttribute("title"));
//        }
//
//        elements.get(3).click();
//        driver.findElement(By.id("phoneNumber")).sendKeys("4162224433");

            driver.findElement(By.cssSelector(".col-md-5.btn.btn-primary.submit-shipping.text-uppercase.btn-block")).click();
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-secondary.btn-block.js-submit-address-from-verify"))).click();
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            System.out.println("Total number " + frames.size());
            List<WebElement> iframe = driver.findElements(By.cssSelector(".js-iframe"));
            driver.switchTo().frame(iframe.get(0));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("encryptedCardNumber"))).clear();
            driver.findElement(By.id("encryptedCardNumber")).sendKeys("4111 1111 1111 1111");
            driver.switchTo().defaultContent();
            driver.switchTo().frame(iframe.get(1));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("encryptedExpiryDate"))).clear();
            driver.findElement(By.id("encryptedExpiryDate")).sendKeys("0322");
            driver.switchTo().defaultContent();
            driver.switchTo().frame(iframe.get(2));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("encryptedSecurityCode"))).clear();
            driver.findElement(By.id("encryptedSecurityCode")).sendKeys("737");
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".col-md-5.btn.btn-primary.submit-payment.text-uppercase")));
            driver.findElement(By.cssSelector(".col-md-5.btn.btn-primary.submit-payment.text-uppercase")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary.d-place-order.place-order.text-uppercase"))).click();
            js.executeScript("window.scrollBy(0,1000)");
            int i = 0;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".minicart-link")));


                while (i < 3) {
                    try {
                        driver.findElement(By.cssSelector(".minicart-link")).click();
                        break;
                    }catch (ElementClickInterceptedException e) {
                        e.printStackTrace();
                        i++;
                    }
                }

            js.executeScript("window.scrollBy(0,1000)");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".remove-product")));
            driver.findElement(By.cssSelector(".remove-product")).click();
            Thread.sleep(7000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".remove-product")));
            driver.findElement(By.cssSelector(".remove-product")).click();
            driver.quit();

    }
}

