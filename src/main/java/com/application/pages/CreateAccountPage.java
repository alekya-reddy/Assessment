package com.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.application.base.BasePage;

public class CreateAccountPage extends BasePage {

	By titleMr = By.id("id_gender1");

	By titleMrs = By.id("id_gender2");

	By firstName = By.id("customer_firstname");

	By lastName = By.id("customer_lastname");

	By password = By.name("passwd");

	By daysDropdown = By.xpath("//select[@id='days']");

	By monthsDropdown = By.id("months");

	By yearsDropdown = By.id("years");

	By newsletterCheckBox = By.xpath("//input[@id='newsletter']");

	By optinCheckBox = By.xpath("//input[@id='optin']");

	By company = By.id("company");

	By addressLine1 = By.id("address1");

	By addressLine2 = By.id("address2");

	By city = By.id("city");

	By stateDropdown = By.id("id_state");

	By postalCode = By.id("postcode");

	By countryDropdown = By.id("id_country");

	By additionalInformation = By.id("other");

	By homePhone = By.id("phone");

	By mobilePhone = By.id("phone_mobile");

	By aliasAddress = By.id("alias");

	By registerButton = By.id("submitAccount");

	@FindBy(id = "customer_firstname")
	WebElement firName;

	WebDriver driver;

	public CreateAccountPage(WebDriver driver) {

		this.driver = driver;

	}

	public void enterUserPersonalInformation(WebDriver driver, String fName, String lName, String pwd, String daysValue,
			String monthsValue, String yearsValue) {

		click(driver, driver.findElement(titleMr));

		sendKeys(driver, driver.findElement(firstName), fName);

		sendKeys(driver, driver.findElement(lastName), lName);

		sendKeys(driver, driver.findElement(password), pwd);

		// new Actions(driver).sendKeys(Keys.TAB).build().perform();

		selectValueByVisibleText(driver, driver.findElement(daysDropdown), daysValue);

		selectValueByVisibleText(driver, driver.findElement(monthsDropdown), monthsValue);

		selectValueByVisibleText(driver, driver.findElement(yearsDropdown), yearsValue);

		driver.findElement(newsletterCheckBox).click();
		
		driver.findElement(optinCheckBox).click();

	}

	public MyAccountPage enterUserAddress(WebDriver driver, String companyName, String add1, String add2,
			String cityName, String state, String zipCode, String country, String other, String homePhoneNumber,
			String mobilePhoneNumber, String alias) {

		sendKeys(driver, driver.findElement(company), companyName);

		sendKeys(driver, driver.findElement(addressLine1), add1);

		sendKeys(driver, driver.findElement(addressLine2), add2);

		sendKeys(driver, driver.findElement(city), cityName);

		selectValueByVisibleText(driver, driver.findElement(stateDropdown), state);

		sendKeys(driver, driver.findElement(postalCode), zipCode);

		selectValueByVisibleText(driver, driver.findElement(countryDropdown), country);

		sendKeys(driver, driver.findElement(additionalInformation), other);

		sendKeys(driver, driver.findElement(homePhone), homePhoneNumber);

		sendKeys(driver, driver.findElement(mobilePhone), mobilePhoneNumber);

		sendKeys(driver, driver.findElement(aliasAddress), alias);

		click(driver, driver.findElement(registerButton));
		return new MyAccountPage(driver);

	}

}
