package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
        System.out.println(TOTPGenerator.getTwoFactorCode());
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\a\\Automatic-U-Pass-BC-Renewer\\Automatic-U-Pass-BC-Renewer\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://upassbc.translink.ca/");
        driver.manage().window().maximize();
        WebElement dropDownListBox = driver.findElement(By.id("PsiId"));
        Select clickThis = new Select(dropDownListBox);
        clickThis.selectByValue("sfu");
        driver.findElement(By.id("goButton")).click();
        driver.findElement(By.id("username")).sendKeys("testInput");
        driver.findElement(By.id("password")).sendKeys("testInput");
        driver.findElement(By.name("submit")).click();
        driver.switchTo().frame("duo_iframe");
        driver.findElement(By.id("totpCode")).sendKeys(TOTPGenerator.getTwoFactorCode());
        driver.findElement(By.xpath("//*[@id=\"totpLogin\"]/div[4]/button[1]")).click();
        System.out.println("Requesting");
        driver.switchTo().parentFrame();
        WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("chk_1")));
        driver.findElement(By.id("chk_1")).click();
        driver.findElement(By.xpath("//*[@id=\"requestButton\"]")).click();
    }
}