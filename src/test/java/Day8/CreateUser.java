package Day8;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;
import java.io.FileReader;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

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
	void test_createuser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		
//		String bearToken = "53fbe63fbb0762d58ec9022d56904b3ccba5370dbdd900f620c7490c63e899fe";
		
		int id =given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		System.out.println("generated id "+id);
		context.setAttribute("user_id", id);
		
		
		context.getSuite().setAttribute("user_id", id);   //use this for suit level need to change the other test also 
		
		
		
		
	}
}
