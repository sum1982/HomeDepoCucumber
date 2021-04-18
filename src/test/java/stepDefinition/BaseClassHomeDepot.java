package stepDefinition;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.homedepot.PageObjects.GiftCardsPO;
import com.homedepot.PageObjects.HomeScreenPO;
import com.homedepot.PageObjects.LoginPO;
import com.homedepot.PageObjects.StoreFinderPO;

public class BaseClassHomeDepot {
	
    
	public WebDriver driver;
	public LoginPO lpo;
	public HomeScreenPO hspo;
	public StoreFinderPO sfpo;
	public GiftCardsPO gcpo;
	
	
	

}
