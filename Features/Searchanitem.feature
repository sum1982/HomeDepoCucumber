Feature: Search an item in HomeDepot

@TC2
Scenario: Successfully Search an item in Homedepot
	Given User launches the chrome browser and enters the URL www.homedepot.com
	When User enters "Wooden crates" in the search box 
	And User clicks on the orange lens search icon
	And User selects checkbox Less than 15 inch from the Product width filter
	And User selects checkbox 5-10 from the Product height filter
	And User select checkbox Unfinished Wood from the Color Family filter
	Then Three filter buttons are shown in the final search page of wooden crates