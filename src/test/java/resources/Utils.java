package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalVariable("baseUrl")) //calling the method instead of hard_coding URL directly
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))		//Log request
				.addFilter(ResponseLoggingFilter.logResponseTo(log))	//Log response
				.setContentType(ContentType.JSON)
				.build();
		
		return req;
		}
		return req;
	}
	
	public static String getGlobalVariable(String key) throws IOException {
		Properties prp = new Properties();
		FileInputStream fs = new FileInputStream("/Users/tezborgohain/eclipse-workspace/APIBDD/src/test/java/resources/global.properties");
		prp.load(fs);		//property class finds .properties file
		return prp.getProperty(key);
		
	}
	
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
