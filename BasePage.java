package com.application.base;

import java.sql.DriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.contants.Constants;
import com.relevantcodes.extentreports.LogStatus;


public class BasePage {

	//WebDriver driver;
	// explicit wait, click, sendkeys, page scroll,highlight, dropdowns

	public void moveToElementByActions(WebDriver driver,WebElement element) {

		Actions actions = new Actions(driver);

		actions.moveToElement(element);

	}

	public void moveToElementByJS(WebDriver driver,WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public void explicitlyWaitForElementToBeVisible(WebDriver driver,WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Constants.explicitWaitTime);

		wait.until(ExpectedConditions.visibilityOf(element));
		
		highlightElement(driver, element);

	}

	public  void explicitlyWaitForElementToBeClickable(WebDriver driver,WebElement element) {

		explicitlyWaitForElementToBeVisible(driver, element);

		WebDriverWait wait = new WebDriverWait(driver, Constants.explicitWaitTime);

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public  void sendKeys(WebDriver driver,WebElement element, String value) {

		explicitlyWaitForElementToBeVisible(driver, element);

		element.sendKeys(value);
		
		

	}

	public  void click(WebDriver driver,WebElement element) {

		explicitlyWaitForElementToBeClickable(driver, element);

		element.click();

	}

	public  void selectValueByIndex(WebDriver driver,WebElement element, int indexvalue) {
		
		explicitlyWaitForElementToBeVisible(driver, element);

		Select select = new Select(element);

		select.selectByIndex(indexvalue);

	}

	public  void selectValueByVisibleText(WebDriver driver,WebElement element, String visibleText) {
		
	
		Select select = new Select(element);
		
		highlightElement(driver, element);

		select.selectByVisibleText(visibleText);

	}

	public  void selectByValue(WebDriver driver,WebElement element, String value) {
		
		explicitlyWaitForElementToBeVisible(driver, element);

		Select select = new Select(element);

		select.selectByValue(value);

	}

	public  void highlightElement(WebDriver driver,WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px blue;');", element);
	}

	public  void highlightFailedElement(WebDriver driver,WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px red;');", element);
	}

}
