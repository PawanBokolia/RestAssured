package Day4;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {

	
	@Test(priority=1)
	void testJsonResponse()
	{
		//approach 1 
		
/*		given()
		
		
		 .when()
			.get("http://localhost:3000/Store")
		
		.then()
			.statusCode(200)
			.header("Content-type", "application/json")
			.body("book[3].title", equalTo("The Load of the Rings"));
*/		
		
		//Approach 2 
		
		Response res= given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/Store");
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-type"), "application/json");
		String Bookname = res.jsonPath().get("book[3].title").toString();    			//get method is returning the object type 	//working without toString? 
		Assert.assertEquals(Bookname,"The Load of the Rings");
		
	}
	
	
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		Response res= given()
				.contentType(ContentType.JSON)
			.when()
				.get("http://localhost:3000/Store");
			
/*			Assert.assertEquals(res.statusCode(), 200);
			Assert.assertEquals(res.header("Content-type"), "application/json");
			String Bookname = res.jsonPath().get("book[3].title").toString();    			//get method is returning the object type 	//working without toString? 
			Assert.assertEquals(Bookname,"The Load of the Rings");
*/			
	
		// JSONObject Class  //if data is not present in the order 
		
		JSONObject jo = new JSONObject(res.asString());   			//converting response to json object type 
		
/*		for(int i=0; i<jo.getJSONArray("book").length();i++) 
		{
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(booktitle);
		}
*/			
		
		//searching for the title of the book in JSON 
		boolean status= false;
		for(int i=0; i<jo.getJSONArray("book").length();i++) 
		{
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("The Load of the Rings"))
			{
				status= true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		
		//Validate the total price of books
		double totalprice = 0;
		for(int i=0; i<jo.getJSONArray("book").length();i++) 
		{
			String price =jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			
			totalprice = totalprice + Double.parseDouble(price); 
			
		}
		
		System.out.println(totalprice);
		Assert.assertEquals(totalprice, 401.5);
	
	}


	
}
