Feature: 
Validating Place API's

Scenario Outline:
Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with Post http request
	Then the API call get success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		

Examples:
	| name				| language		| address					|
	| Frontline house	| French-IN		| 29, side layout, cohen 09	|
	| PeterLand			| English-EN	| 108 Peter St				|