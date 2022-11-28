package com.simplelearn.mavenproject;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplelearn.mainfiles.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standaloneapp {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		//LandingPage landingPage=new LandingPage(driver);
		// login
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("email123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("DriverManager1!");
		driver.findElement(By.id("login")).click();
		// dashboard page
		String productName = "ZARA COAT 3";
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By
				.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"))));
		List<WebElement> products = driver.findElements(
				By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
		WebElement prods = products.stream().filter(
				product -> product.findElement(By.xpath("//div//div//h5//b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		// add to cart
		prods.findElement(By.xpath("//div//div//button[2]//i")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		// .ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// cart page
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		cartproducts.stream().filter(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

		// purchasing page
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(@class,'ta-item')]")));
	
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//div[2]//a")));
		
		driver.findElement(By.xpath("//div//div[2]//a")).click();
		String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		assertTrue(confirmmessage.equalsIgnoreCase(" Thankyou for the order."));
		driver.close();

	}

}
