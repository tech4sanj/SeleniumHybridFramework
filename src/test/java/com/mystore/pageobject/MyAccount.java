package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {
	WebDriver ldriver;

	public MyAccount(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}

	//Identify the webelements
	//New User Id
	@FindBy(id="email_create")
	WebElement createEmailId;

	@FindBy(id="SubmitCreate")
	WebElement SubmitCreate;

	//Already user account
	@FindBy(id="email")
	WebElement registeredUserEmail;

	@FindBy(id="passwd")
	WebElement registeredUserPwd;

	@FindBy(id="SubmitLogin")
	WebElement submitLogin;


	//identify the action on webelement
	public void enterCreateEmailAddress(String emailAdd) {
		createEmailId.sendKeys(emailAdd);		
	}
	
	
	public void clickSubmitCreate() {
		SubmitCreate.click();		
	}

	//Already registered user
	public void enterEmaildAddress(String email) {

		registeredUserEmail.sendKeys(email);			
	}
	public void enterPassword(String pwd) {

		registeredUserPwd.sendKeys(pwd);			
	}

	public void loginsubmit() {
		submitLogin.click();
	}



}
