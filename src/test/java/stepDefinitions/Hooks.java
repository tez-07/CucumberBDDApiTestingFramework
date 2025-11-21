package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		//This method will run before executing DeletePlace
		//Why? -- Because, to run the DeletePlace, we need a placeID to delete
		//And if we directly run DeletePlace, we don't have any id
		//So by calling previously created methods we are creating the placeID here
		
		if(stepDefinitions.place_id == null) {
			stepDefinitions obj = new stepDefinitions();
			obj.add_place_payload_with("Ricardo", "Italian", "262 John Garland, Italy");
			obj.user_calls_with_http_request("AddPlaceAPI", "POST");
			obj.verify_place_id_created_maps_to_using("Ricardo", "GetPlaceAPI");
		}
		
	}
}
