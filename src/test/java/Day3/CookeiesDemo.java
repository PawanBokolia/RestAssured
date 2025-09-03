package Day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookeiesDemo {

//	@Test
	void testCookies() {

		given()
				.when().get("https://www.google.com/").then()
				.cookie("AEC", "AVh_V2gNImjNVC6y788Gq5ChoVGDRIFLZIyMk2t8AXk9oOM9-Fa65igO7w").log().all();

	}

	@Test
	void getCookiesinfo() {

		Response res = given()

				.when().get("https://www.google.com/");

		// Single cookies
		String cookies_value = res.getCookie("AEC");
		System.out.println("Value of the cookies===> " + cookies_value);

		// Get all the cookies
		Map<String, String> Cookies_value = res.getCookies();

		for (String a : Cookies_value.keySet()) 
		{
			String b = Cookies_value.get(a);    
			System.out.println(a+ "    "+b);
			
		}

	}
}