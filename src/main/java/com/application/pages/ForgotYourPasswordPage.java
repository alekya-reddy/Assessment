package com.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.SendKeysAction;

import com.application.base.BasePage;

public class ForgotYourPasswordPage extends BasePage {

	By emailID = By.id("email");

	By retrievePasswordButton = By.xpath("//span[text()='Retrieve Password']/parent::button");

	By forgotPwdPageTitle = By.xpath("//h1[text()='Forgot your password?']");

	By successMessage = By.xpath("//p[contains(@class,'alert-success')]");
	
	By errorMessage = By.xpath("//div[contains(@class,'alert-danger')]");

	WebDriver driver;

	public ForgotYourPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyForgotPasswordPageTitle() {
		return driver.findElement(forgotPwdPageTitle).getText().equalsIgnoreCase("Forgot your password?");
	}

	public void enterEmailID(String email) {

		sendKeys(driver, driver.findElement(emailID), email);

	}

	public void clickOnRetrievePassword() {
		click(driver, driver.findElement(retrievePasswordButton));
	}

	public String getTheSuccessMessage() {

		return driver.findElement(successMessage).getText();

	}
	
	public String getTheErrorMessage() {
		return driver.findElement(errorMessage).getText();
		
	}
	
	public void scrollTillForgotPasswordTitle() {
		moveToElementByJS(driver, driver.findElement(forgotPwdPageTitle));
	}

}
