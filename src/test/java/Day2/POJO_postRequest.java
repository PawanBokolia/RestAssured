package Day2;

public class POJO_postRequest {

	String name;
	String location;
	String phone;
	String courses[];
	
/*
	public POJO_postRequest(String name,String location,String phone,String courses[])   if use this we dont need to use set data and we all add default/ non perametalized constructor
	{
		this.name=name;
		this.location=location;
		this.phone=phone;
		this.courses=courses;
	}
*/	
	


	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getLocation() 
	{
		return location;
	}
	
	public void setLocation(String location) 
	{
		this.location = location;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	public String[] getCourses() 
	{
		return courses;
	}
	
	public void setCourses(String[] courses) 
	{
		this.courses = courses;
	}

	
	
	
	
	

}
