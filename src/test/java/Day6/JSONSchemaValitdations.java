package Day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;


public class JSONSchemaValitdations {
	
	@Test
	void jsonSchemaValidation()
	{

		given()
			
		.when()
			.get("http://localhost:3000/Store")
			
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema.json"));
		
		
		
		
	}
}
