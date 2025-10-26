package stepDefinations;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.TestData;
import resources.Utils;

public class stepDefination extends Utils{

	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestData data = new TestData();
	
	
	/*
	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.AddPlace());
	}
	*/
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.AddPlace(name, language, address));
	}


	
	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		//Response specification Builder
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();			
		response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
		
	}

	
	
	@Then("the API call get success with status code {int}")
	public void the_api_call_get_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(),200);
	}

	
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);	//will check both status code and scope
		
		
	}



}
