package steps;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.AccountListPage;
import page.BasePage;
import page.DatabasePage;
import page.LoginPage;
import page.NewAccountPage;

public class LoginStepDefination extends BasePage {

	LoginPage loginPage;
	AccountListPage accountListPage;
	NewAccountPage newAccountPage;
	DatabasePage databasePage;

	@Before
	public void launchChrome() {

		openChrome();
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		accountListPage = PageFactory.initElements(driver, AccountListPage.class);
		newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
		databasePage = new DatabasePage();
	}

	@Given("User is on the codefios login page")
	public void userIsOnTheCodefiosLoginpage() {
		driver.get("https://codefios.com/ebilling");
		String expectedValue = "Codefios";
		String actualValue = loginPage.getLoginPageTitle();
		Assert.assertEquals("Login page not found", actualValue, expectedValue);
		screenshot(driver);
	}
	

	@When("User enters {string} from mysql database")
	public void user_enters_from_mysql_database(String loginDataFromDb) {
	    switch (loginDataFromDb) {
		case "username":
			loginPage.enterUserName(databasePage.getDataFromDb("user_name"));
			break;
		case "password":
				loginPage.enterPassword(databasePage.getDataFromDb("user_password"));
			break;
		default:
			System.out.println("Unable to retreive login data");
		}
	    
	}



	@When("User enters the {string} in the {string} field")
	public void userEnterUserNameAndPassword(String loginData, String field) {

		if (field.equalsIgnoreCase("userName")) {
			loginPage.enterUserName(loginData);
		} else if (field.equalsIgnoreCase("Password")) {
			loginPage.enterPassword(loginData);
		} else {
			System.out.println("Enter valid login data:" + loginData + "Field:" + field);
		}
	}

	@And("User clicks on {string}")
	public void userClicksOnButton(String field) throws InterruptedException {

		switch (field) {
		case "listAccounts":
			Thread.sleep(5000);
			accountListPage.userClicksOnListAccount();
			break;
		case "login":
			loginPage.clickSignInButton();
			break;
		case "addAccount":
			accountListPage.clickOnAddAccount();
			break;
		case "Save":
			newAccountPage.clickOnSave();
		}
	}

	@Then("User should land on Dashboard page")
	public void validateDashboardPage() {

		String expectedTitle = "Dashboard";
		String actualTitle = loginPage.getDashboradPageTitle();
		Assert.assertEquals("DashBoard page not found", actualTitle, expectedTitle);
		screenshot(driver);

	}

}
