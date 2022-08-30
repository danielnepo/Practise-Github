Feature: Validating place API 

Scenario Outline:: Verify if place is successfully added using add place API 
	Given Add place payload with "<address>" "<language>"
	When user calls "AddPlaceAPI" with "POST" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	
Examples:
			|address         |language     |
			|PCV             |Tamil        |
			|Gold            |Thang        |