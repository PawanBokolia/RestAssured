package Day8;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetUser {
	
	
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
	void testGetuser(ITestContext context)
	{
//		int id = (Integer) context.getAttribute("user_id");    //this should come from createUser request 

		int id = (Integer) context.getSuite().getAttribute("user_id");  // use this for
		
//		String barrerToken= "53fbe63fbb0762d58ec9022d56904b3ccba5370dbdd900f620c7490c63e899fe";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id",id)
	
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200);
		
		
		
		
		
		
	}
}
