Feature: Gift cards from Home depot

@TC4
Scenario: Buy Gift cards form Home depot
    Given User launches the chrome browser and enters the URL www.homedepot.com
	When User clicks on the Gift Cards link 
	And User clicks on the Shop Now button of the Purchase a Gift Card section
	And User selects Birthday from the Category dropdown
	And User selects Someone else radio button
	And User enters a Recipient name, Sender name and Sender message
	And User selects amount of $50 denomination  
	And User clicks on Buy Now button
	Then User is directed to a page with title "The Home Depot Gift Cards by CashStar"