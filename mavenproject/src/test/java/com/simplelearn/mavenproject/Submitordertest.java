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

import com.simplelearn.mainfiles.CheckoutPage;
import com.simplelearn.mainfiles.ConfirmationPage;
import com.simplelearn.mainfiles.LandingPage;
import com.simplelearn.mainfiles.ProductCatalogue;
import com.simplelearn.mainfiles.AbstractComponents.CartPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Submitordertest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		String productName = "ZARA COAT 3";
		// login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		// Product catalogue page
		ProductCatalogue productCatalogue = landingPage.loginApplication("email123@gmail.com", "DriverManager1!");
		List<WebElement> products = productCatalogue.getProductList();
		// add to cart// cart page
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		assertTrue(match);
		CheckoutPage checkoutPage = cartPage.gotoCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		cartPage.gotoCheckout();
		String conformMessage = confirmationPage.getConfirmationMessage();
		assertTrue(conformMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
