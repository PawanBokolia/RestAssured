package Day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class parsingXMLResponse {

	@Test
	void testXMLResponse()
	{
		given()
			
		
		
		.when()
			.get("")
			
		
		.then()
			.statusCode(200)
			.body("s", null);
			
	}
	
	
	
	
	
	
}
