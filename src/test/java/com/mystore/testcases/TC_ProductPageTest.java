package com.mystore.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.RegisteredUserAccount;
import com.mystore.pageobject.SearchResultPage;

public class TC_ProductPageTest extends BaseClass{

	@Test(enabled=true)
	public void VerifySearchProduct() throws IOException {
		String searchKeyword="Blouse";

		logger.info("***************TestCase search product started*****************");

		//Sign in
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

		//Enter the searchkey in the searchbox
		RegisteredUserAccount regUser=new RegisteredUserAccount(driver);
		regUser.EnterDataInSearchBox(searchKeyword);
		regUser.ClickOnSearchButton();
		
		
		//Get name of the searched product
		SearchResultPage resultPg=new SearchResultPage(driver);
		scrollToElement(resultPg.getSearchResultProductNameText());
        String searchedProductName=resultPg.getSearchResultProductName();

		//Verify that correct product details are showing.
		if(searchedProductName.contains(searchKeyword)) {
			logger.info("Searched product test case - passed");
			softAssert.assertTrue(true);
			regUser.clickOnSignOut();

		}
		else {

			logger.info("Searched product test case - Failed");
			captureScreenShot(driver,"VerifySearchProduct");

			softAssert.assertTrue(false);
		}
		
		logger.info("***************TestCase Search Product ends*****************"); 
		





	}

}
