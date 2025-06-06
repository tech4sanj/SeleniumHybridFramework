package com.mystore.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mystore.pageobject.AccountCreationDetails;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.RegisteredUserAccount;

public class TC_MyAccountPageTest extends BaseClass{

	@Test(enabled=false)
	public void verifyregistrationAndLogin() {

		logger.info("***************TestCase Verify Registration and Login starts*****************"); 
		
		//Create the object of index page
		IndexPage indexpage=new IndexPage(driver);
		indexpage.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount pg=new MyAccount(driver);
		pg.enterCreateEmailAddress("Testing521@email.com");
		logger.info("Entered email address");

		pg.clickSubmitCreate();
		logger.info("Clicked on create an account button");

		AccountCreationDetails accCreationPg=new AccountCreationDetails(driver);
		accCreationPg.selectTitleMrs();
		accCreationPg.enterCustomerFirstName("Rajeev");
		accCreationPg.enterCustomerLastName("Gupta");
		accCreationPg.enterPassword("Ks19023");

		logger.info("entered user details on account creation page.");

		scrollToElement(accCreationPg.getRegisterButton());  // Ensure you use the getter here

		accCreationPg.clickOnRegister();
		logger.info("clicked on Register button");

		RegisteredUserAccount regUser=new RegisteredUserAccount(driver);
		String userName=regUser.getUserName();
		softAssert.assertEquals("Rajeev Gupta",userName);

		logger.info("***************TestCase Verify Registration and Login ends*****************"); 
	}

	@Test
	public void verifyLogin() throws IOException {

		logger.info("***************TestCase Verify Login starts*****************"); 

		IndexPage indexpage=new IndexPage(driver);
		indexpage.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myac=new MyAccount(driver);

		myac.enterEmaildAddress("Testing521@email.com");

		logger.info("Entered email address");

		myac.enterPassword("Ks19023");
		logger.info("Entered password");

		myac.loginsubmit();
		logger.info("Clicked on Login in link..");

		RegisteredUserAccount regUser=new RegisteredUserAccount(driver);
		String userName=regUser.getUserName();

		if(userName.equals("Rajeev Gupta"))
		{
			logger.info("Verify login-passsed");

		}

		else {
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"verifyLogin");
			softAssert.assertTrue(false);
		}
		logger.info("***************TestCase Verify Login ends*****************"); 
	}

	@Test(enabled=false)
	public void verifySignOut() {
		logger.info("***************TestCase Verify Sign out starts*****************"); 
		
		IndexPage indexpage=new IndexPage(driver);
		indexpage.clickOnSignIn();
		logger.info("Clicked on sign in link");
		
		MyAccount myac=new MyAccount(driver);

		myac.enterEmaildAddress("Testing521@email.com");

		logger.info("Entered email address");

		myac.enterPassword("Ks19023");
		logger.info("Entered password");

		myac.loginsubmit();
		logger.info("Clicked on Login in link..");
		
		RegisteredUserAccount regUser=new RegisteredUserAccount(driver);
		regUser.clickOnSignOut();
		
		
	}
}
