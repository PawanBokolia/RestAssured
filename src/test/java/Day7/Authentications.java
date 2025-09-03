package Day7;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class Authentications {
	Properties p;
	
	
	
	
	
//	@Test(priority =1)
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
//	@Test(priority =2)
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
//	@Test(priority=3)
	void testPreemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
	
//	@Test(priority =4)
	void testBarrerAuthentication() throws IOException
	{
		FileReader file = new FileReader(".//src//test//resources//Restassured_token.properties");
		p = new Properties();
		p.load(file);
		
		
		String bearerToken = p.getProperty("Barrer_token");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)  		//header
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
//	@Test
	void testOAuth1Authentication()
	{
		given()
			.auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate")
		.when()
			.get("URL")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testOAuth2Authentication() throws IOException
	{
		FileReader file = new FileReader(".//src//test//resources//Restassured_token.properties");
		p = new Properties();
		p.load(file);
		
		given()
			.auth().oauth2(p.getProperty("Oauth2"))
			
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200);
			
	}
	
//	@Test
	void testAPIKeyAuthentication()
	{
		//method 1 
/*		given()
			.queryParam("appid","151dbaf2a51dda1e7af202603e91e9bc")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
	
*/		
		//method 2 
		given()
			.queryParam("appid","151dbaf2a51dda1e7af202603e91e9bc")  
			
			.pathParam("mypath", "data/2.5/forecast/daily")    	//path parameter 
			
			.queryParam("q","Delhi")							//divide all the query parameters 
			.queryParam("units", "metric")						//divide all the query parameters 
			.queryParam("cnt", "7")								//divide all the query parameters 
			
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")

		.then()
			.statusCode(200)
			.log().all();	
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
