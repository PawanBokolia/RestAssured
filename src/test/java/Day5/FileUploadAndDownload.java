package Day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;



public class FileUploadAndDownload {

	@Test(priority=1)
	void singleFileUpload()
	{
		File myfile = new File("D:\\Testing\\Opencart_loginData.xlsx");
		
		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")					// now the restAssured will take care of the content type as using multipart
			
			.when()
				.post("http://localhost:8080/uploadFile")
			
			.then()
				.statusCode(200)
				.body("fileName", equalTo("Opencart_loginData.xlsx"))
				.log().all();		
	
	}
	
//	@Test
	void multipleFileUpload()
	{
		File myfile1 = new File("D:\\Testing\\Opencart_loginData.xlsx");
		File myfile2 = new File("D:\\Testing\\DataDriven.csv");
		
//		File filearr[]= {myfile1,myfile2};		//this approach wont work every time it depends on the developer 
		
		given()
			.multiPart("files",myfile1)  			 //.multipart("files",filearr)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")					// now the restAssured will take care of the content type as using multipart
			
			.when()
				.post("http://localhost:8080/uploadMultipleFiles")
			
			.then()
				.statusCode(200)
				.body("[0].fileName", equalTo("Opencart_loginData.xlsx"))
				.body("[1].fileName", equalTo("DataDriven.csv"))
				.log().all();		

	}
	
	
	@Test(priority =2)
	void fileDownload()
	{
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/Opencart_loginData.xlsx")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	
	
	
	
}
