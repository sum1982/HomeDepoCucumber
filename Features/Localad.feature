Feature: HomeDepo Rental Feature
@TC5
Scenario Outline: HomeDepo Rental Test Scenario

Given User is already on HomeDepo Page
When title of the home page is HomeDepo
Then user clicks on local ad
Then user clicks on tool rental
Then user clicks on tillers
Then user clicks on check availability
Then user clicks on check nearby stores
Then user clicks search box and enters "<zipcode>"

Examples:
	| zipcode |
	| 60133	|


@TC6
Scenario: HomeDepo Husky Test Scenario

Given user is on ad page
When user clicks on husky link
Then user clicks on tool bags
Then user sees husky tool bags


@TC7
Scenario Outline: HomeDepo Career Test Scenario

Given user is on HomeDepo site
When user clicks on localad
Then user clicks on careers
Then user clicks on Search and apply
Then user enters "<keywords>" and clicks on location
Then user sees jobs near location entered

Examples:
	| keywords |
	| cashier |


@TC8
Scenario: HomeDepo Blinds Test Scenario

Given user is already on local ad
When user clicks on blinds link
Then user click on wood blinds
Then user selects width and height and clicks on update
Then user sees results
