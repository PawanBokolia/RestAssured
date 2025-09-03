package Day8;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class ChainingInSingleClass {
	int id ;
	String bearerToken;
	Properties p;
	
    @BeforeClass
    void setup() throws IOException {
       FileReader file = new FileReader(".//src//test//resources//Restassured_token.properties");
        p = new Properties();
        p.load(file);

        bearerToken = p.getProperty("Barrer_tokenDay8");
    }
	
	
	@Test(priority=1)
	void testPractiseCreateUser() throws IOException {
		
		
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		id = given()
					.headers("Authorization", "Bearer " +bearerToken)
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("https://gorest.co.in/public/v2/users")
					.jsonPath().getInt("id");
	}

	@Test(priority=2)
	void testPractiseGetUser()
	{
		given()
			.headers("Authorization", "Bearer " +bearerToken)
			.pathParam("id",id)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(200);
	}
	
	
	@Test(priority =3)
	void updatePractiseUser()
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		
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
	
	@Test(priority=4)
	void DeletePractiseUser()
	{
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(204)
			.log().all();
			
			
	}
	
	
	
	
	
}
