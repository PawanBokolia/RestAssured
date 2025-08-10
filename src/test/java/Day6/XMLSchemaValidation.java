package Day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.matcher.RestAssuredMatchers;
public class XMLSchemaValidation {

	
	void XMLSchemavalidation()
	{
		given()
		
		.when()
			.get("")		// API is no longer valid 
			
		.then()
		 .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath(""));   //convert a xml response into xsd fromat and put the file in resource 
		 
	}
}
