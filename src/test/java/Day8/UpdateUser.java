package Day8;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;
public class UpdateUser {

	String bearerToken;
	Properties p;
	
    @BeforeClass
    void setup() throws IOException {
       
    	FileReader file = new FileReader(".//src//test//resources//Restassured_token.properties");
        p = new Properties();
        p.load(file);

        bearerToken = p.getProperty("Barrer_tokenDay8");
    }
	
	
	@Test
	void testUpdateUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		
//		String bearToken = "53fbe63fbb0762d58ec9022d56904b3ccba5370dbdd900f620c7490c63e899fe";
		
		int id = (Integer) context.getAttribute("user_id"); 
		
	    given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
			
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	
	
}
