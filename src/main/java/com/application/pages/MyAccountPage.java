package com.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ContextClickAction;
import org.openqa.selenium.support.FindBy;

import com.application.base.BasePage;

public class MyAccountPage extends BasePage {

	By signout = By.className("logout");

	By myAccountHeader = By.className("page-heading");

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {

		this.driver = driver;

	}

	public boolean verifyMyAccountPageTitle(String expectedTitle) {
		String actualTitle =driver.findElement(myAccountHeader).getText();

		return actualTitle.equalsIgnoreCase(expectedTitle);

	}

	public SignInPage clickOnSignOutLink(WebDriver driver) {
		click(driver, driver.findElement(signout));

		return new SignInPage(driver);

	}

}
