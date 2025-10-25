package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	RequestSpecification reqspec;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick")
				.addFilter(RequestLoggingFilter.logRequestTo(log))		//Log request
				.addFilter(ResponseLoggingFilter.logResponseTo(log))	//Log response
				.setContentType(ContentType.JSON)
				.build();
		
		return reqspec;
	}
}
