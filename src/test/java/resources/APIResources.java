package resources;

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIResources(String resource){
		this.resource = resource;		//refers to current class variable
	}
	
	public String getResource() {
		return resource;
	}
	
	
	
	//APIResources resourceAPI = APIResources.valueOf("AddPlaceAPI");
	//This line dynamically looks up the ENUM constant named "AddPlaceAPI" 
	//(the same text used in your feature file).
}
