Feature: 
Validating Place API's

@AddPlace
Scenario Outline:
Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then the API call get success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
	
		And verify place_id created maps to "<name>" using "GetPlaceAPI"
		#This one is calling GET method, with the placeID to retrieve created place details
		

Examples:
	| name				| language		| address					 	 |
	| Conor McGregor	| Irish			| 29, side layout, IO, Ireland	 |
   #| Peter Sheer		| English-EN	| 108 Peter St	, ON, Canada 	 |
	
	
	
	
@DeletePlace
Scenario:
Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When User calls "DeletePlaceAPI" with "POST" http request
	Then the API call get success with status code 200
	And "status" in response body is "OK"
	
	
	
	
	
