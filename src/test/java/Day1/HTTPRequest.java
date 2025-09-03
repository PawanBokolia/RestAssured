package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class HTTPRequest {
	int id;
	
		
	@Test(priority =1)
	void getUser()
	{
		
		given()

		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	
	
	
	@Test(priority =1)
	void createUser()
	{
		HashMap<String,String> data =new HashMap<>();
		data.put("name", "Pawan");
		data.put("job","trainner");
		
		
		id=given()
			.contentType("application/json")			//   ContentType.JSON  //best practise 
			.header("x-api-key", "reqres-free-v1") 			// required for authentication 
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");				//get the id from the body and store it to given 
			
		

	}	


	@Test(priority =3,dependsOnMethods = {"createUser"})
	void updateUser()
	{
		HashMap<String,String> data =new HashMap<String,String>();
		data.put("name", "John");
		data.put("job","tester");
		
		
		given()
			.contentType("application/json")
			.header("x-api-key", "reqres-free-v1") 	
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
						
		.then()
			.statusCode(200)
			.log().all();
			
	}
	
	
	@Test(priority=4,dependsOnMethods = {"createUser"})
	void deleteUser()
	{
		given()		
		.header("x-api-key", "reqres-free-v1") 	
	
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(204)
			.log().all();

	}
	
	
	
}
