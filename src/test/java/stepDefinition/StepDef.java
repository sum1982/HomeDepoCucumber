package stepDefinition;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homedepot.PageObjects.*;
import com.homedepot.Utilities.ReadConfigHomeDepot;


import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class StepDef extends BaseClassHomeDepot{
	WebDriver driver;
	 static String brow;
	    static String baseURL;
	    WebDriverWait wait;
	    DIYProjectListPageObj diypageobj;
	    private StringBuffer verificationErrors = new StringBuffer();
	    List<Map<String, String>> page_link_heading;
	
	@Before
	public void setup() 
	{
		ReadConfigHomeDepot readconfig = new ReadConfigHomeDepot();
		
		
		String URL = readconfig.getApplicationURL();
		String brow = readconfig.getBrowser();
		
		System.out.println(URL);
		System.out.println(brow);
		
		
		
		if(brow.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			ChromeOptions op = new ChromeOptions();
			op.setHeadless(true);
			op.addArguments("window-size=1920,1080");
			driver = new ChromeDriver(op);
			
		}
		else if(brow.equals("IE")) 
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();			
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	

	    //wait = new WebDriverWait(driver, 30);
		diypageobj = PageFactory.initElements(driver, DIYProjectListPageObj.class);

        System.out.println("setUp method called");
	
		driver.get(URL);
		
	}
	
	
	
	
	

	//SEARCH AN ITEM test case
	
	@When("User enters {string} in the search box")
	public void user_enters_in_the_search_box(String item) {
	   
		hspo = new HomeScreenPO(driver);
		hspo.setsearch(item);
	}

	@When("User clicks on the orange lens search icon")
	public void user_clicks_on_the_orange_lens_search_icon() throws InterruptedException {
	   
		hspo.clicksrchbtn();
		Thread.sleep(4000);
	}

	@When("User selects checkbox Less than {int} inch from the Product width filter")
	public void user_selects_checkbox_Less_than_inch_from_the_Product_width_filter(Integer int1) throws InterruptedException {
		
		System.out.println(int1);
		hspo.clickwidthCB1();
		Thread.sleep(4000);
	    
	}

	@When("User selects checkbox {int}{int} from the Product height filter")
	public void user_selects_checkbox_from_the_Product_height_filter(Integer int1, Integer int2) throws InterruptedException {
		
		System.out.println(int1);
		System.out.println(int2);
		hspo.clickheightCB1();
		Thread.sleep(4000);
	   
	}

	@When("User select checkbox Unfinished Wood from the Color Family filter")
	public void user_select_checkbox_Unfinished_Wood_from_the_Color_Family_filter() throws InterruptedException {
		
		hspo.clickcolorCB1();
		Thread.sleep(4000);
	   
	}

	@Then("Three filter buttons are shown in the final search page of wooden crates")
	public void three_filter_buttons_are_shown_in_the_final_search_page_of_wooden_crates() {
		
		Assert.assertTrue((hspo.getProdcolor_btn().isDisplayed()) && (hspo.getProdheight_btn().isDisplayed()) && (hspo.getProdwidth_btn().isDisplayed()));
		driver.quit();
	   
	}
	
	//STORE FINDER test case
	
	@When("User clicks on the Store Finder link")
	public void user_clicks_on_the_Store_Finder_link() throws InterruptedException {
		
		sfpo = new StoreFinderPO(driver);
		sfpo.clickstorefinder();
		Thread.sleep(2000);
	    
	}

	@When("User enters {int} in the zip code search box and clicks on the search icon")
	public void user_enters_in_the_zip_code_search_box_and_clicks_on_the_search_icon(Integer int1) throws InterruptedException {
		
		sfpo.storeSearchBoxsendkeys("60194");
		Thread.sleep(2000);
		sfpo.storeSearchBoxButtonclick();
		Thread.sleep(2000);
	    
	}

	@When("User clicks on Show only stores with")
	public void user_clicks_on_Show_only_stores_with() throws InterruptedException {
		
		sfpo.showonlystorewithclick();
		Thread.sleep(2000);
	   
	}

	@When("User selects Search within {int} miles from the Location within dropdown")
	public void user_selects_Search_within_miles_from_the_Location_within_dropdown(Integer int1) throws InterruptedException {
		
		sfpo.radiusselect();
		Thread.sleep(2000);
		sfpo.radius5milesclick();
		Thread.sleep(2000);
	    
	}

	@When("User select Home Depot Truck Rental check box")
	public void user_select_Home_Depot_Truck_Rental_check_box() throws InterruptedException {
	   
		sfpo.homedepottruckrentalCBclick();
		Thread.sleep(2000);
	}

	@When("User click on Apply Filters button")
	public void user_click_on_Apply_Filters_button() throws InterruptedException {
		
		sfpo.applyfiltersclick();
		Thread.sleep(2000);
		EventFiringWebDriver efw = new EventFiringWebDriver(driver);
		efw.executeScript("document.querySelector('body > section > div:nth-child(3) > div.col__4-12.col__4-12--sm.col__3-12--md.listFrame').scrollTop=500");
		
	    
	}

	@Then("User sees Schaumburg store and ElkGrove store")
	public void user_sees_Schaumburg_store_and_ElkGrove_store() {
		
		System.out.println("Finding stores...");
		String s1 = sfpo.get_store1();
		System.out.println(s1);
		String s2 = sfpo.get_store2();
		System.out.println(s2);
		
		Assert.assertTrue(s1.equalsIgnoreCase("1 - Schaumburg #1904") && s2.equalsIgnoreCase("2 - Elk Grove Village #6701"));
		
		driver.quit();
	   
	}
    
	//********************
    //GIFT CARDS test case
	//********************
	
	@When("User clicks on the Gift Cards link")
	public void user_clicks_on_the_Gift_Cards_link() throws InterruptedException {
		
		gcpo = new GiftCardsPO(driver);
		gcpo.giftcardclick();
		Thread.sleep(2000);
	    
	}

	@When("User clicks on the Shop Now button of the Purchase a Gift Card section")
	public void user_clicks_on_the_Shop_Now_button_of_the_Purchase_a_Gift_Card_section() throws InterruptedException {
		
		gcpo.shopnowclick();
		Thread.sleep(2000);
	   
	}

	@When("User selects Birthday from the Category dropdown")
	public void user_selects_Birthday_from_the_Category_dropdown() throws InterruptedException {
	    
		gcpo.categoryselect();
		Thread.sleep(2000);
	}

	@When("User selects Someone else radio button")
	public void user_selects_Someone_else_radio_button() throws InterruptedException {
		
		gcpo.whoisthisforradio();
		Thread.sleep(2000);
	    
	}

	@When("User enters a Recipient name, Sender name and Sender message")
	public void user_enters_a_Recipient_name_Sender_name_and_Sender_message() throws InterruptedException {
		
		WebElement elem;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		elem = gcpo.reciepientname();
		js.executeScript("arguments[0].scrollIntoView(true);",elem);
		elem.click();
		elem.clear();
		String recename = "Rohini";
		Thread.sleep(2000);
		js.executeScript("arguments[0].value='" + recename + "';", elem);
		elem.sendKeys(" ");
        elem.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		
		elem=gcpo.sendername();
		elem.click();
		elem.clear();
		Thread.sleep(2000);
		String sendname = "Rohit";
		js.executeScript("arguments[0].value='" + sendname + "';", elem);
		elem.sendKeys(" ");
        elem.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		
		elem=gcpo.sendermess();
		elem.click();
		elem.clear();
		Thread.sleep(2000);
		String mess = "Happy Birthday";
		js.executeScript("arguments[0].value='" + mess + "';", elem);
		elem.sendKeys(" ");
        elem.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
	    
	}

	@When("User selects amount of ${int} denomination")
	public void user_selects_amount_of_$_denomination(Integer int1) throws InterruptedException {
		
		System.out.println(int1);
		gcpo.dollar50button();
		Thread.sleep(2000);
	    
	}

	@When("User clicks on Buy Now button")
	public void user_clicks_on_Buy_Now_button() throws InterruptedException {
		
		gcpo.buynowbutton();
		Thread.sleep(2000);
	    
	}

	@Then("User is directed to a page with title {string}")
	public void user_is_directed_to_a_page_with_title(String string) {
		
		String pagetitle = driver.getTitle();
		System.out.println(pagetitle);
		
		Assert.assertEquals(driver.getTitle(),"The Home Depot Gift Cards by CashStar");
		
		driver.quit();
	    
	}
	
	@Given("^User is already on HomeDepo Page$")
	public void user_is_already_on_HomeDepo_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
        driver.get("https://www.homedepot.com/");
        driver.manage().window().maximize(); 
	}

	@When("^title of the home page is HomeDepo$")
	public void title_of_the_home_page_is_HomeDepo() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 String title = driver.getTitle();
		    System.out.println(title);
		    Assert.assertEquals("The Home Depot", title);
	}

	@Then("^user clicks on local ad$")
	public void user_clicks_on_local_ad() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[contains(text(),'Local Ad')]")).click();
	}

	@Then("^user clicks on tool rental$")
	public void user_clicks_on_tool_rental() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[@href='//www.homedepot.com/c/tool_and_truck_rental']")).click();
	}

	@Then("^user clicks on tillers$")
	public void user_clicks_on_tillers() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//img[@title='Tillers']")).click();
	}

	@Then("^user clicks on check availability$")
	public void user_clicks_on_check_availability() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//body/div[@id='container']/div[4]/div[3]/div[2]/div[1]/div[12]/div[7]/div[1]/a[1]/span[1]")).click();
	}

	@Then("^user clicks on check nearby stores$")
	public void user_clicks_on_check_nearby_stores() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Check Nearby Stores')]")).click();
	}

	@Then("^user clicks search box and enters \"([^\"]*)\"$")
	public void user_clicks_search_box_and_enters_zip_code(String zipcode) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("search-bar")).click();
	    driver.findElement(By.id("search-bar")).clear();
		driver.findElement(By.id("search-bar")).sendKeys(zipcode, Keys.ENTER);
		Assert.assertEquals("Mantis Mantis Tiller Rental 7262-00-02 - The Home Depot", driver.getTitle());
		driver.quit();
	}


//	Test2
	
	@Given("^user is on ad page$")
	public void user_is_on_ad_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   driver.get("https://www.homedepot.com/c/localad");
	   driver.manage().window().maximize(); 
	}
	
	@When("^user clicks on husky link$")
	public void user_clicks_on_husky_link() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//img[@alt='Husky']")).click();
	}

	@Then("^user clicks on tool bags$")
	public void user_clicks_on_tool_bags() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//img[@alt='Tool Bags']")).click();
	}

	@Then("^user sees husky tool bags$")
	public void user_sees_husky_tool_bags() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		Assert.assertEquals("Husky - Tool Bags - Tool Storage - The Home Depot", driver.getTitle());
		Thread.sleep(2000);
		Assert.assertEquals("Husky - Tool Bags - Tool Storage - The Home Depot", driver.getTitle());
		driver.quit();
	}

//	Test3
	
	@Given("^user is on HomeDepo site$")
	public void user_is_on_HomeDepo_site() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://www.homedepot.com/");
		driver.manage().window().maximize(); 
	}
	
	@When("^user clicks on localad$")
	public void user_clicks_on_localad() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[contains(text(),'Local Ad')]")).click();
	}

	@Then("^user clicks on careers$")
	public void user_clicks_on_careers() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[contains(text(),'Careers')]")).click();
	}

	@Then("^user clicks on Search and apply$")
	public void user_clicks_on_Search_and_apply() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("thd-home-takeover")).click();

	}

	@Then("^user enters \"([^\"]*)\" and clicks on location$")
	public void user_enters_keywords_and_clicks_on_location(String keywords) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("cws_jobsearch_keywords")).sendKeys(keywords);
		driver.findElement(By.xpath("//body/div[@id='boxed-wrapper']/div[@id='wrapper']/main[@id='main']/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[2]/button[1]/i[1]")).click();
	}

	@Then("^user sees jobs near location entered$")
	public void user_sees_jobs_near_location_entered() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("Search for Jobs at The Home Depot", driver.getTitle());
		driver.quit();
	}
	//Test4
	
	@Given("^user is already on local ad$")
	public void user_is_already_on_local_ad() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 driver.get("https://www.homedepot.com/c/localad");
		 driver.manage().window().maximize(); 
	}
	
	@When("^user clicks on blinds link$")
	public void user_clicks_on_blinds_link() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 driver.findElement(By.xpath("//img[@alt='Blinds.com']")).click(); 
	}

	@Then("^user click on wood blinds$")
	public void user_click_on_wood_blinds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//div[@class='aspect-ratio aspect-ratio--17x19']")).click();
	}

	@Then("^user selects width and height and clicks on update$")
	public void user_selects_width_and_height_and_clicks_on_update() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 WebElement e=driver.findElement(By.id("SelectedWidth"));
		    Select width= new Select (e);
		    width.selectByVisibleText("60");
		    e=driver.findElement(By.id("SelectedHeight"));
		    Select height= new Select (e);
		    height.selectByVisibleText("50");
		    driver.findElement(By.id("gcc-search-btn-update-prices")).click();
	}

	@Then("^user sees results$")
	public void user_sees_results() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		Assert.assertEquals("Faux Wood Blinds | Custom Window Blinds | Blinds.com", driver.getTitle());
		driver.close();
	}

	@Given("^user is on Home Depot web page:$")
	public void user_is_on_Home_Depot_web_page(List<String> arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
        driver.get("https://www.homedepot.com/");
        //Assert user is on homedepot.com page
        assertEquals(driver.getCurrentUrl(), arg1.get(0).toString());
		System.out.println(arg1.get(0).toString() + " user_is_on_Home_Depot_web_page");
	}

	@Given("^the browser window is maximized$")
	public void the_browser_window_is_maximized() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
        driver.manage().window().maximize();
        Dimension size = driver.manage().window().getSize();
        int width1 = size.getWidth();
        int height1 = size.getHeight();
        System.out.println("Width" + width1 + "Height" + height1);  
        //assertEquals()
		System.out.println("the_browser_window_is_maximized");
		//throw new PendingException();
	}

	@When("^user points mouse over \"([^\"]*)\" link$")
	public void user_points_mouse_over_link(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		System.out.println(arg1 + " user_points_mouse_over_link called");
        
        wait = new WebDriverWait(driver, 30);
        
        WebElement links = diypageobj.getLinks();   //driver.findElement(By.className("ShoppingLinks"));
        wait.until(ExpectedConditions.visibilityOf(links));
        
        System.out.println("links " + links.toString());

        List<WebElement> shopLinksOptions = diypageobj.getShopLinksOptions();//driver.findElements(By.className("ShoppingLinks__item"));
        System.out.println("ShoppingLinks__item count" + shopLinksOptions.size());
        
        Actions actions;
        actions = new Actions(driver);

        for( WebElement op : shopLinksOptions ){
            System.out.println("ShoppingLinks__item " + op.getText());
            if(arg1.equals("DIY Projects & Ideas")) {
                if(op.getText().equals("DIY Projects & Ideas")){
                    actions = actions.moveToElement(op);
                    actions.perform();
                    wait.until(ExpectedConditions.visibilityOf(diypageobj.getDiyoptions()));
                    break;
                }
            }else if(arg1.equals("All Departments")) {
            	if(op.getText().equals("All Departments")) {
                    actions = actions.moveToElement(op);
                    actions.perform();
                    wait.until(ExpectedConditions.visibilityOf(diypageobj.getAlldept()));
                    break;
                    
            	}
            }
           
        }    
        
	}

	@Then("^a dropdown should appear showing four columns:$")
	public void a_dropdown_should_appear_showing_four_columns(List<String> arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
        //Assert List header heading text as expected in backend
        WebElement diyoptions = diypageobj.getDiyoptions(); //driver.findElement(By.xpath("//*[@id=\"diyFlyout\"]/section/div"));
        wait.until(ExpectedConditions.visibilityOfAllElements(diypageobj.getListsheading(diyoptions)));
        List<WebElement> listsheading = diypageobj.getListsheading(diyoptions); //diypageobj.getListsheading(); 
        
        int index = 1; //first row is the header in data table
        assertEquals(listsheading.size(), arg1.size()-1);
        
        for(WebElement op: listsheading){
            System.out.println("List header " + op.getText());
            assertEquals(op.getText(), arg1.get(index++));
            
        }

		System.out.println("a_dropdown_should_appear_showing_four_columns called");
		//throw new PendingException();
		
	}


	@Then("^\"([^\"]*)\" dropdown should disappear$")
	public void dropdown_should_disappear(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
        WebElement alldept = diypageobj.getAlldept(); //driver.findElement(By.xpath("//*[@id=\"allDepartmentsFlyout\"]/section[1]/div"));
        //Assert DIY Projects list and ideas link is not displayed
        assertTrue(alldept.isDisplayed());

        WebElement diyoptions = diypageobj.getDiyoptions(); //driver.findElement(By.xpath("//*[@id=\"diyFlyout\"]/section/div"));
        //isDisplayed() and isEnabled() are returning true values
        assertFalse(diyoptions.isSelected());
        //assertFalse(diyoptions.isDisplayed());
        
		//throw new PendingException();
	}

	@Then("^\"([^\"]*)\" list should have:$")
	public void list_should_have(String arg1, List<String> arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)

        Actions actions;
        actions = new Actions(driver);

        //Find list elements in individual list and assert they are equal to list elements as in backend
        //WebElement diyoptions = diypageobj.getDiyoptions(); //driver.findElement(By.xpath("//*[@id=\"diyFlyout\"]/section/div"));
        List<WebElement> diyoptionslist = diypageobj.getDiyoptionslist(); 
        System.out.println("diyoptionslist list size " + diyoptionslist.size());
        
        List<WebElement> listsheading = diypageobj.getListsheading(diypageobj.getDiyoptions()); //diypageobj.getListsheading(); 

        int index = 0;
        WebElement heading;
        for(WebElement op : diyoptionslist){
            System.out.println("diyoptionslist content " + op.toString());
            heading = listsheading.get(index);
            if(heading.getText().equals(arg1)){
                List<WebElement> list = diypageobj.getListitems(op);    //op.findElements(By.tagName("li"));
                System.out.println("list size " + list.size());
                assertEquals(list.size(), arg2.size());
                int index1 = 0;
                for(WebElement listop : list){
                    assertEquals(listop.getText(),arg2.get(index1));
                    actions = actions.moveToElement(listop);
                    actions.perform();
                    WebElement anchor = diypageobj.getAnchor(listop); //listop.findElement(By.tagName("a"));
                    String link = anchor.getText();
                    System.out.println("link " + link);
                    index1++;
                }
                break;
	        }
            index++;
            
        }
		//throw new PendingException();
	}	

	//@DataTableType
	@Given("^a list of page attributes in a table$")
	public void a_list_of_page_attributes_in_a_table(List<Map<String, String>> arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    //Map<String,String> pageattr = arg1.getTableConverter().toMap(arg1, String.class, String.class);
		page_link_heading = new ArrayList<Map<String, String>>(arg1);
		Map<String, String >pageattr = page_link_heading.get(35);
	    System.out.println("heading in page attr " + pageattr.get("link"));
	    System.out.println("heading in page attr " + pageattr.get("pageheading"));
		//throw new PendingException();
	}
	
	@When("^user points mouse over option \"([^\"]*)\" and clicks$")
	public void user_points_mouse_over_option_and_clicks(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		wait.until(ExpectedConditions.visibilityOfAllElements(diypageobj.getDiyoptionslist()));
		List<WebElement> diyoptionslist = diypageobj.getDiyoptionslist(); //diyoptions.findElements(By.tagName("ul"));
		System.out.println("diyoptionslist list size " + diyoptionslist.size());
 
        Actions actions = new Actions(driver);
        int found_link = 0;
        for(WebElement op : diyoptionslist){
            //System.out.println("diyoptionslist content " + op.toString());
            //System.out.println("diyoptionslist heading " + op.getText());

            List<WebElement> list = diypageobj.getListitems(op); //op.findElements(By.tagName("li"));
            System.out.println("list size " + list.size());

            for(WebElement listop : list){
                System.out.println("listop.getText() " + listop.getText());

            	if(listop.getText().equals(arg1)){
                    WebElement anchor = diypageobj.getAnchor(listop); //listop.findElement(By.tagName("a"));
                    String href = anchor.getAttribute("href");
                    System.out.println("Anchor href " + href);
                    if(!(href.equals(""))){
                        actions = actions.click(listop);//moveToElement(listop);
                        actions.perform();
                        //driver.findElement(By.tagName("h1")))
                    }
                    found_link = 1;
                    break;
                }
            }
            if(found_link==1) {
            	break;
            }
        }
    }

	@Then("^a page with page heading relevant to the option \"([^\"]*)\" should open$")
	public void a_page_with_page_heading_relevant_to_the_option_should_open(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
        wait.until(ExpectedConditions.visibilityOf(diypageobj.getHeading()));
        //Page loaded correctly
        //driver.findElement(By.tagName("h1")).getText();
        String heading = diypageobj.getHeading().getText();
        System.out.println("Passed link " + arg1);
        String exp_heading = null;
        for(Map<String, String> pageattr : page_link_heading) {
        	if(pageattr.get("link").equals(arg1)) {
        		exp_heading = pageattr.get("pageheading");
        	}
        }
        assertEquals(heading, exp_heading);
	}
	

}
