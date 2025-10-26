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
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	RequestSpecification reqspec;
	public RequestSpecification requestSpecification() throws IOException {
		
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqspec = new RequestSpecBuilder().setBaseUri(getGlobalVariable("baseUrl")) //calling the method instead of hard_coding URL directly
				.addQueryParam("key", "qaclick")
				.addFilter(RequestLoggingFilter.logRequestTo(log))		//Log request
				.addFilter(ResponseLoggingFilter.logResponseTo(log))	//Log response
				.setContentType(ContentType.JSON)
				.build();
		
		return reqspec;
	}
	
	public static String getGlobalVariable(String key) throws IOException {
		Properties prp = new Properties();
		FileInputStream fs = new FileInputStream("/Users/tezborgohain/eclipse-workspace/APIBDD/src/test/java/resources/global.properties");
		prp.load(fs);		//property class finds .properties file
		return prp.getProperty(key);
		
	}
}
