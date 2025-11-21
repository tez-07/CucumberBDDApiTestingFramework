package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public pojo.AddPlace AddPlace(String name, String language, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(60);
		place.setAddress(address);
		place.setLanguage(language);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);
		
		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shoe");
		place.setTypes(list);
		
		return place;
	}
	
	public String deletePayload(String place_id) {
		
		//needs to be converted to escape formatting
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	}
	
	
}
