package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	
	WebDriver ldriver;

	public SearchResultPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	//Identify the webelements
	@FindBy(xpath="//a[@title='Blouse'][normalize-space()='Blouse']")
	WebElement searchResultProduct ;
	
	@FindBy(linkText="More")
	WebElement more;
	
	//action methods on web elements of search result page
	
	public String getSearchResultProductName()
	{
		return(searchResultProduct.getText());
	}
	
	public void ClickOnMoreLink()
	{
		more.click();

	}
	public WebElement getSearchResultProductNameText() {
        return searchResultProduct;
    }


}
