package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//pojo ---> serialization ----> JsonObject ---->De-serialize---->pojo object 

public class SerializationAndDeserialization {

	// Pojo ---> json
//	@Test
	void convertPojo2json() throws JsonProcessingException {
		// Created java object suing pojo class
		Student stupoja = new Student();

		stupoja.setName("Scott");
		stupoja.setLocation("France");
		stupoja.setPhone("123456789");

		String coursesArr[] = { "C", "C++" };

		stupoja.setCourses(coursesArr);

		// Conver java object =---> json object( serilization )

		ObjectMapper objMapper = new ObjectMapper();

		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupoja);
		System.out.println(jsondata);
		
	}
	
	//Json ------> pojo 
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456789\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		ObjectMapper objMapper = new ObjectMapper();
		
		Student stupojo = objMapper.readValue(jsondata, Student.class);   //convert json to pojo 
		System.out.println(stupojo.getName());
		System.out.println(stupojo.getLocation());
		System.out.println(stupojo.getPhone());
		System.out.println(stupojo.getCourses()[0]);
		System.out.println(stupojo.getCourses()[1]);
		
		
		
		
	}
}
