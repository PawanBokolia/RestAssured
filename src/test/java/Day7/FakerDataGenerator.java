package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
	
		String fullname = faker.name().fullName();
		String firstname = faker.name().firstName();
		String lastname= faker.name().lastName();
		
		String fulname = faker.name().fullName();
		String username = faker.name().username();
		
		String password = faker.internet().password();
		String phoneNo = faker.phoneNumber().cellPhone();
		
		String email = faker.internet().safeEmailAddress();
		
	
		System.out.println(phoneNo);
		
	}
	
	
	
	
	
	
}
