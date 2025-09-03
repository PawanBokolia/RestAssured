package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



public class WayToCreatePostRequesBody {

	
	//1) Post request body using HashMap
	
//	@Test(priority=1)
	void testPostUsingHashMap()
	{
		HashMap<String,Object>data =new HashMap<>();
		
		data.put("name", "Scott");
		data.put("location","France");
		data.put("phone", "123456789");
		
		String coursesArr[]= {"C","C++"};
		
		data.put("courses", coursesArr);
		
	given()
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
		.header("Content-Type","application/json")
		.log().all();
		
	}
	
	
	// delete request 
	
//	@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
			.delete("http://localhost:3000/Students/"+"")
		
		.then()
			.statusCode(200);
	}
	
	
	
	
	
	// 2) Post request body using org.json
	
//	@Test(priority=2)
	void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location","France");
		data.put("phone", "123456789");
		
		String coursesArr[]= {"C","C++"};
		
		data.put("courses",coursesArr);
		
	given()
		.contentType("application/json")
		.body(data.toString())   			// need to change the data into string 
		
	.when()
		.post("http://localhost:3000/Students")
		
	.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456789"))
		.body("courses[0]",equalTo("C"))		
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json")
		.log().all();
		 
	}
	

	// 3) Post request body using POJO (Plain old java object) 
	
//	@Test(priority= 3 )
	void testPostUsingPOJO()
	{
		POJO_postRequest data= new POJO_postRequest();
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456789");
		
		String coursesArr[]= {"C","C++"};
		
		data.setCourses(coursesArr);
		
	given()
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
		.header("Content-Type","application/json")
		.log().all();
		 
	}
	
	
	// 4) external json file 
	@Test(priority= 4 )
	void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		
		File f =new File(".\\body.json");
		FileReader fr = new FileReader(f); 			//use directly to instead of f directly add the address of
		
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
	given()
		.contentType("application/json")
		.body(data.toString())   		
		
	.when()
		.post("http://localhost:3000/Students")
		
	.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456789"))
		.body("courses[0]",equalTo("C"))		
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json")
		.log().all();
		 
	}
	
	
	
}
