package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        String username = System.getenv("USERNAME");
        System.out.println(username);
        System.out.println(TOTPGenerator.getTwoFactorCode());
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://upassbc.translink.ca/");
        driver.manage().window().maximize();
        WebElement dropDownListBox = driver.findElement(By.id("PsiId"));
        Select clickThis = new Select(dropDownListBox);
        clickThis.selectByValue("sfu");
        driver.findElement(By.id("goButton")).click();
        driver.findElement(By.id("username")).sendKeys(System.getenv("UNI_USERNAME"));
        driver.findElement(By.id("password")).sendKeys(System.getenv("UNI_PASSWORD"));
        driver.findElement(By.name("submit")).click();
        driver.switchTo().frame("duo_iframe");

        driver.findElement(By.id("code")).sendKeys(TOTPGenerator.getTwoFactorCode());
        // click the button with text "Submit"
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        System.out.println("Requesting");
        driver.switchTo().parentFrame();
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("chk_1")));
        driver.findElement(By.id("chk_1")).click();
        driver.findElement(By.xpath("//*[@id=\"requestButton\"]")).click();

        String botToken = System.getenv("BOT_TOKEN");
        String chatID = System.getenv("CHAT_ID");
        String message = "Reached end of Automatic U-Pass BC Renewer";
        String telegramURL = "https://api.telegram.org/bot" + botToken + "/sendMessage?chat_id=" + chatID + "&text="
                + message;
        driver.get(telegramURL);

        driver.close();
        driver.quit();
    }
}