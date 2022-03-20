package com.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.application.base.BasePage;

public class SignInPage extends BasePage {

	By createAccount = By.name("SubmitCreate");

	By emailID = By.id ("email_create");

	By signInLink = By.xpath("//a[@class='login']/parent::div");
	
	By registeredEmail = By.id("email");
	
	By registeredPassword = By.id("passwd");
	
	By signInButton = By.id("SubmitLogin");
	
	By authError = By.xpath("//div[@id='center_column']/div[@class='alert alert-danger']");
	
	By authenticationHeader = By.xpath("//h1[text()='Authentication']");
	
	By forgotPasswordLink = By.xpath("//a[text()='Forgot your password?']");

	WebDriver driver;

	public SignInPage(WebDriver driver) {

		this.driver = driver;

	}

	public void clickOnSignInLink(WebDriver driver) {
		click(driver, driver.findElement(signInLink));
	}

	public void enterEmailID(String email) {

		sendKeys(driver,driver.findElement(emailID), email);
	}

	public CreateAccountPage clickOnCreateAccountButton() {

		click(driver, driver.findElement(createAccount));
		return new CreateAccountPage(driver);
	}

	public MyAccountPage enterUserCredentialsAndSignin(String userName,String pwd) {
		sendKeys(driver, driver.findElement(registeredEmail), userName);
		
		sendKeys(driver, driver.findElement(registeredPassword), pwd);
		
		click(driver, driver.findElement(signInButton));
		
		return new MyAccountPage(driver);
	
	}
	
	public String getAuthenticationErrorForInvalidEmailID() {
		
		return driver.findElement(authError).getText();
		
	}
	
	public void scrollPageTillSignInButton() {
		moveToElementByJS(driver, driver.findElement(authenticationHeader));
	}
	
	public ForgotYourPasswordPage clickOnForgotYourPasswordLink() {
		click(driver,driver.findElement(forgotPasswordLink));
		return new ForgotYourPasswordPage(driver);
	}
}
