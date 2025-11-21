package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class stepDefinitions extends Utils{

	
	RequestSpecification request;
	ResponseSpecification resspec;
	Response response;
	TestData data = new TestData();
	static String place_id;
	
	/*
	@Given("Add Place PayLoad")
	public void add_place_payload() throws IOException {
		request = given().spec(requestSpecification()).body(data.AddPlace());
	}
	*/
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		request = given()
					.spec(requestSpecification())
					.body(data.AddPlace(name, language, address));
	}


	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);	//Automatically constructor will get called of ENUM class
		System.out.println(resourceAPI.getResource());;
		
		//Response specification Builder
		resspec = new ResponseSpecBuilder()
						.expectStatusCode(200)
						.expectContentType(ContentType.JSON).build();			

		
		if(method.equalsIgnoreCase("POST")) 
			response = request
							.when()
								.post(resourceAPI.getResource())
							.then()
								.spec(resspec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = request
							.when()
								.get(resourceAPI.getResource())
							.then()
								.spec(resspec).extract().response();
		
		
		
		//Above in .post("/maps/api/place/add/json") is replaced with resourceAPI.getResource()
	}

	
	
	@Then("the API call get success with status code {int}")
	public void the_api_call_get_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(),200);
	}

	
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(response, keyValue), expectedValue);	//will check both status code and scope
		
	}

	
	//Verifying the placeID that got created while ADDING new PLACE
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	  
		place_id = getJsonPath(response, "place_id");			//Store it in a string variable
		System.out.println(place_id);
		
		//pass the id with queryParameter
		request = given()
					.spec(requestSpecification())
					.queryParam("place_id",place_id); 
		
		
		
		//Calling the GET Method, and Validating the name inside the response
		user_calls_with_http_request(resource, "GET"); 					
		String actualName = getJsonPath(response, "name");
		System.out.println(actualName);
		assertEquals(actualName, expectedName);
		
	}
	
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    
		request = given()
					.spec(requestSpecification())
					.body(data.deletePayload(place_id));
	}



}
