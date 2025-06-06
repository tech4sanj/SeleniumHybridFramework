package com.mystore.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.pageobject.AccountCreationDetails;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.RegisteredUserAccount;
import com.mystore.utilities.ReadExcelFile;

public class TC_MyAccountPageTest2DataDrivenTesting extends BaseClass{

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
		Assert.assertEquals("Rajeev Gupta",userName);

		logger.info("***************TestCase Verify Registration and Login ends*****************"); 
	}

	@Test(dataProvider="LoginDataProvider")
	public void verifyLogin(String userEmail,String userPassword,String expectedUsername) throws IOException {

		logger.info("***************TestCase Verify Login starts*****************"); 

		IndexPage indexpage=new IndexPage(driver);
		indexpage.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myac=new MyAccount(driver);

		myac.enterEmaildAddress(userEmail);

		logger.info("Entered email address");

		myac.enterPassword(userPassword);
		logger.info("Entered password");

		myac.loginsubmit();
		logger.info("Clicked on Login in link..");

		RegisteredUserAccount regUser=new RegisteredUserAccount(driver);
		String userName=regUser.getUserName();

		if(userName.equals(expectedUsername))
		{
			logger.info("Verify login-passsed");
			softAssert.assertTrue(true);
			regUser.clickOnSignOut();

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

	//Data Provider method
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");


		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
			}

		}
		return data;
	}

}
