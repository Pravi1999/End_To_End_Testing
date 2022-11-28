package com.simplelearn.mainfiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.simplelearn.mainfiles.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	// below called pagefactory
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
	List<WebElement> products;
	By productsBy = By
			.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");
	By addToCart = By.xpath("//div//div//button[2]//i");
	By toastMeassage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToApper(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prods = getProductList().stream().filter(
				product -> product.findElement(By.xpath("//div/div/h5/b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prods;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prods = getProductByName(productName);
		prods.findElement(addToCart).click();
		waitForElementToApper(toastMeassage);
		waitForElementToDissapper(spinner);
	}
}
