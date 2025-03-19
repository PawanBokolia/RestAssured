package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;



public class WayToCreatePostRequesBody {

	static String id;
	
	//1) Post request body using HashMap
	
	//@Test(priority=1)
	void testPostUsingHashMap()
	{
		HashMap<String,Object>data =new HashMap<>();
		
		data.put("name", "Scott");
		data.put("location","France");
		data.put("phone", "123456789");
		
		String coursesArr[]= {"C","C++"};
		
		data.put("courses", coursesArr);
		
	id = given()
		.contentType("application/json")
		.body(data)
		
	.when()
		.post("http://localhost:3000/Students")
		
	.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456789"))
		.body("courses[0]",equalTo("C"))		
		.body("courses[1]",equalTo("C++"))
//		.header("Content-Type","application/json.X-Powered-By=tinyhttp")
		.log().all()
		.extract().jsonPath().getString("id");
		
	}
	
	
	//
	
	@Test(priority=2)
	void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "12336544788");
		
		String coursesArr[]= {"C","C++"};
		data.put("courses",coursesArr);
		
		
	id = given()
		.contentType("application/json")
		.body(data)
		
	.when()
		.post("http://localhost:3000/Students")
		
	.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456789"))
		.body("courses[0]",equalTo("C"))		
		.body("courses[1]",equalTo("C++"))
//		.header("Content-Type","application/json.X-Powered-By=tinyhttp")
		.log().all()
		.extract().jsonPath().getString("id");
		
		
		
	}
	
	
	
	
}
