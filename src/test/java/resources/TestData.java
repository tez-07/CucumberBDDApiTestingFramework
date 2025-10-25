package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public pojo.AddPlace AddPlace() {
		AddPlace place = new AddPlace();
		place.setAccuracy(60);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setName("Frontline house");
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
}
