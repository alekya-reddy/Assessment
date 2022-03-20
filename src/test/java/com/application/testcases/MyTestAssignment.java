package com.application.testcases;

import org.testng.annotations.Test;

import com.application.contants.Constants;
import com.application.pages.CreateAccountPage;
import com.application.pages.ForgotYourPasswordPage;
import com.application.pages.MyAccountPage;
import com.application.pages.SignInPage;
import com.application.utils.TestUtils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class MyTestAssignment {

	WebDriver driver;

	SignInPage signinPage;

	CreateAccountPage createAccountPage;

	MyAccountPage myAccountPage;

	ForgotYourPasswordPage forgotPwdPage;

	String randomText = TestUtils.generateRandomText();

	String email = "tester.user" + randomText + "@gmail.com";

	String pwd = "Password-1";

	@Test(priority = -1, description = "User account creation", enabled = true)

	public void createAccount() {

		signinPage.clickOnSignInLink(driver);

		signinPage.enterEmailID(email);

		createAccountPage = signinPage.clickOnCreateAccountButton();

		createAccountPage.enterUserPersonalInformation(driver, "my test", "user", pwd, "20  ", "April ", "1995  ");

		myAccountPage = createAccountPage.enterUserAddress(driver, "Test Company", "123,Line 2", "lane 2,XY",
				"Selenium", "Texas", "12345", "United States", "Test", "8471200000", "0124578963", "Home");

		Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("MY Account"));

		signinPage = myAccountPage.clickOnSignOutLink(driver);

	}

	@Test(description = "Valid test case", enabled = true,priority=0)
	public void loginWithValidCredentials() {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin(email, pwd);

		Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));

	}

	@Test(description = "Invalid test case with invalid email id", enabled = true,priority=1)
	public void loginWithInValidEmailID() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin("testdemo.userqa@gmail.com", pwd);
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "InvalidEmail");
		}

	}

	@Test(description = "Invalid test case with invalid password", enabled = true,priority=2)
	public void loginWithInValidPassword() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin(email, "123DemoUser");
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "InvalidPassword");
		}

	}

	@Test(description = "Invalid test case with invalid email and password", enabled = true,priority=3)
	public void loginWithInValidCredentials() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin("teqademo.user12@uhoo.com", "Test1234");
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "InvalidCredentials");
		}

	}

	@Test(description = "Blank email with valid password", enabled = true,priority=4)
	public void loginWithBlankEmailAndValidPassword() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin("", "Test1234");
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "BlankEmail");
		}

	}

	@Test(description = "Valid email with blank password", enabled = true,priority=5)
	public void loginWithValidEmailWithBlankPassword() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin(email, "");
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "BlankPassword");
		}

	}

	@Test(description = "Blank email and password", enabled = true,priority=6)
	public void loginWithBlankEmailAndPassword() throws IOException {

		signinPage.clickOnSignInLink(driver);

		myAccountPage = signinPage.enterUserCredentialsAndSignin("", "");
		try {
			Assert.assertTrue(myAccountPage.verifyMyAccountPageTitle("my account"));
		} catch (AssertionError e) {
			System.out.println(signinPage.getAuthenticationErrorForInvalidEmailID());
			signinPage.scrollPageTillSignInButton();
			TestUtils.pullScreenshot(driver, "src\\test\\resources\\Screenshots", "BlankCredentials");
		}

	}

	@Test(description = "Forgot pasword with valid email ID", enabled = true,priority=7)
	public void forgotPasswordCheckwWithValidEmail() throws IOException {

		signinPage.clickOnSignInLink(driver);

		forgotPwdPage = signinPage.clickOnForgotYourPasswordLink();

		Assert.assertTrue(forgotPwdPage.verifyForgotPasswordPageTitle());

		forgotPwdPage.enterEmailID(email);
		
		forgotPwdPage.clickOnRetrievePassword();

		Assert.assertTrue(forgotPwdPage.getTheSuccessMessage()
				.equalsIgnoreCase("A confirmation email has been sent to your address: " + email));
		
		TestUtils.pullScreenshot(driver,"src\\test\\resources\\Screenshots", "ForgotPasswordLinkRetrieveSuccessMessage");

	}
	
	@Test(description = "Forgot pasword with invalid email ID", enabled = true,priority=8)
	public void forgotPasswordCheckWithInvalidEmail() throws IOException {

		signinPage.clickOnSignInLink(driver);

		forgotPwdPage = signinPage.clickOnForgotYourPasswordLink();

		Assert.assertTrue(forgotPwdPage.verifyForgotPasswordPageTitle());

		forgotPwdPage.enterEmailID("156245tes@4.gmail.com");
		
		forgotPwdPage.clickOnRetrievePassword();
		
		System.out.println(forgotPwdPage.getTheErrorMessage());

		Assert.assertTrue(forgotPwdPage.getTheErrorMessage()
				.contains("There is no account registered for this email address."));
		
		forgotPwdPage.scrollTillForgotPasswordTitle();
		
		TestUtils.pullScreenshot(driver,"src\\test\\resources\\Screenshots", "ForgotPasswordLinkRetrieveErrorMessage");

	}

	@BeforeMethod
	public void beforeMethod() {

		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Constants.implicitWaitTime, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php");

		signinPage = new SignInPage(driver);

		createAccountPage = new CreateAccountPage(driver);

		myAccountPage = new MyAccountPage(driver);

		forgotPwdPage = new ForgotYourPasswordPage(driver);

	}

	@AfterMethod
	public void afterMethod() {

		driver.close();
	}

}
