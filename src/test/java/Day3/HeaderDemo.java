package Day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



public class HeaderDemo {

//	@Test
	void testHeader() {

		given()

				.when().get("https://google.com")

				.then().header("Content-Type", "text/html; charset=ISO-8859-1").and() // no need to but if you want to
																						// seprate them you can use this
				.header("Content-Encoding", "gzip").and() // no need to but if you want to seprate them you can use this
				.header("Server", "gws");

	}

	@Test
	void getHeaders() {

		Response res = given()

				.when().get("https://google.com");

		// get single header info
		String headerValue = res.getHeader("Content-Type");
		System.out.println("content type header value ===> " + headerValue);

		// get all the headers info
		Headers myHeaders = res.getHeaders(); // headers type this is not a hashMap

		for (Header hd : myHeaders)
		{
			System.out.println(hd.getName() + "=======>     " + hd.getValue());
		}

	}

}
