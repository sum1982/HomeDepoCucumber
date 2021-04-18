Feature: Find a store
@TC3
Scenario: Find a Homedepot store near you
	Given User launches the chrome browser and enters the URL www.homedepot.com
	When User clicks on the Store Finder link
	And User enters 60194 in the zip code search box and clicks on the search icon
	And User clicks on Show only stores with
	And User selects Search within 5 miles from the Location within dropdown
	And User select Home Depot Truck Rental check box
	And User click on Apply Filters button
	Then User sees Schaumburg store and ElkGrove store